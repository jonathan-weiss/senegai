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
import {AuthorFormService} from "@app/author/author-form/author-form.service";
import {AuthorFormFieldName, AuthorFormLibraryAwardListFormGroup} from "@app/author/author-form/author-form-field-name";

@Component({
    selector: 'app-author-library-award-table',
    templateUrl: './author-library-award-table.component.html',
    styleUrls: ['./author-library-award-table.component.scss'],
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
export class AuthorLibraryAwardTableComponent implements OnInit {
    @Input({ required: true }) authorLibraryAwardFormArray!: FormArray<FormGroup<AuthorFormLibraryAwardListFormGroup>>;
    @Output() editAuthorLibraryAwardFormGroup = new EventEmitter<FormGroup<AuthorFormLibraryAwardListFormGroup>>();
    @Output() deleteAuthorLibraryAwardFormGroup = new EventEmitter<FormGroup<AuthorFormLibraryAwardListFormGroup>>();

    displayedColumns: string[] = ['description', 'year', 'actions'];
    dataSource: MatTableDataSource<FormGroup<AuthorFormLibraryAwardListFormGroup>> = new MatTableDataSource<FormGroup<AuthorFormLibraryAwardListFormGroup>>();

    selectedFormGroup: FormGroup | undefined = undefined;

    constructor(private readonly authorFormService: AuthorFormService) {
    }

    ngOnInit(): void {
        this.dataSource.data = this.authorLibraryAwardFormArray.controls
        this.authorLibraryAwardFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private updateFormData(): void {
        this.dataSource.data = this.authorLibraryAwardFormArray.controls
    }

    onAdd(): void {
        const newEntry = this.authorFormService.createInitialLibraryAwardListForm()
        const indexOfSelected = this.selectedFormGroup ? this.authorLibraryAwardFormArray.controls.indexOf(this.selectedFormGroup) : -1
        if(indexOfSelected !== -1) {
            this.authorLibraryAwardFormArray.insert(indexOfSelected + 1, newEntry)
        } else {
            this.authorLibraryAwardFormArray.push(newEntry)
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
        return FormUtil.requiredFormControl(formControl, AuthorFormFieldName.libraryAwardListDescription);
    }

    private yearControl(formControl: AbstractControl): FormControl<number> {
        return FormUtil.requiredFormControl(formControl, AuthorFormFieldName.libraryAwardListYear);
    }

    descriptionFromControl(formControl: AbstractControl): string | undefined {
        return this.descriptionControl(formControl).value as string | undefined;
    }

    yearFromControl(formControl: AbstractControl): number | undefined {
        return this.yearControl(formControl).value as number | undefined;
    }
}
