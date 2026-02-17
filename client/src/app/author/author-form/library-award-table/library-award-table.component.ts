import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {AbstractControl, FormArray, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatDialogModule} from "@angular/material/dialog";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    LibraryAwardTableRow
} from "@app/author/author-form/library-award-table/library-award-table-row.model";
import {
    LibraryAwardFormPartFieldName
} from "@app/author/author-form/author-library-award-form-part/library-award-form-part-field-name";
import {
    LibraryAwardFormPartService
} from "@app/author/author-form/author-library-award-form-part/library-award-form-part.service";
import {
    LibraryAwardFormPartGroup
} from "@app/author/author-form/author-library-award-form-part/library-award-form-part-group";

@Component({
    selector: 'app-author-library-award-table',
    templateUrl: './library-award-table.component.html',
    styleUrls: ['./library-award-table.component.scss'],
    imports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatToolbarModule,
        MatTableModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatExpansionModule,
        MatSidenavModule,
        MatListModule,
        MatDialogModule,
    ]
})
export class LibraryAwardTableComponent implements OnInit {
    @Input({ required: true }) libraryAwardFormArray!: FormArray<FormGroup<LibraryAwardFormPartGroup>>;
    @Output() editAuthorLibraryAwardFormGroup = new EventEmitter<FormGroup<LibraryAwardFormPartGroup>>();
    @Output() deleteAuthorLibraryAwardFormGroup = new EventEmitter<FormGroup<LibraryAwardFormPartGroup>>();

    displayedColumns: string[] = [
        'description',
        'year',
        'actions',
    ];
    dataSource: MatTableDataSource<LibraryAwardTableRow> = new MatTableDataSource<LibraryAwardTableRow>();

    selectedFormGroup: FormGroup | undefined = undefined;

    constructor(private readonly libraryAwardFormService: LibraryAwardFormPartService) {
    }

    ngOnInit(): void {
        this.updateFormData()
        this.libraryAwardFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private toTableRow(formGroup: FormGroup<LibraryAwardFormPartGroup>): LibraryAwardTableRow {
        return {
            description: this.descriptionFromControl(formGroup),
            year: this.yearFromControl(formGroup),
            formGroup: formGroup,
        }
    }

    private updateFormData(): void {
        this.dataSource.data = this.libraryAwardFormArray.controls.map((control) => this.toTableRow(control))
    }

    onAdd(): void {
        const newEntry = this.libraryAwardFormService.createInitialLibraryAwardForm()
        const indexOfSelected = this.selectedFormGroup ? this.libraryAwardFormArray.controls.indexOf(this.selectedFormGroup) : -1
        if(indexOfSelected !== -1) {
            this.libraryAwardFormArray.insert(indexOfSelected + 1, newEntry)
        } else {
            this.libraryAwardFormArray.push(newEntry)
        }
        this.editAuthorLibraryAwardFormGroup.emit(newEntry);
    }

    onSelect(authorLibraryAwardFormGroup: FormGroup): void {
        this.selectedFormGroup = authorLibraryAwardFormGroup
    }

    isSelected(authorLibraryAwardFormGroup: FormGroup): boolean {
        return this.selectedFormGroup == authorLibraryAwardFormGroup
    }

    onEdit(authorLibraryAwardFormGroup: FormGroup): void {
        this.editAuthorLibraryAwardFormGroup.emit(authorLibraryAwardFormGroup);
    }

    onDelete(authorLibraryAwardFormGroup: FormGroup): void {
        this.deleteAuthorLibraryAwardFormGroup.emit(authorLibraryAwardFormGroup);
    }

    private descriptionControl(formControl: AbstractControl): FormControl<string> {
        return FormUtil.requiredFormControl(formControl, LibraryAwardFormPartFieldName.libraryAwardListDescription);
    }

    private yearControl(formControl: AbstractControl): FormControl<number> {
        return FormUtil.requiredFormControl(formControl, LibraryAwardFormPartFieldName.libraryAwardListYear);
    }

    private descriptionFromControl(formControl: AbstractControl): string {
        return this.descriptionControl(formControl).value as string;
    }

    private yearFromControl(formControl: AbstractControl): number {
        return this.yearControl(formControl).value as number;
    }
}
