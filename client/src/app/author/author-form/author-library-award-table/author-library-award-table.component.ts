/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemResultComponentTypescript" templateRendererPackageName="senegai.codegen.renderer.angular" ]

    @template-model [
        modelClassName="ItemModel"
        modelPackageName="senegai.codegen.renderer.model"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @slac

}}}@ */
import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
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
import {MatSlideToggle, MatSlideToggleChange} from "@angular/material/slide-toggle";
import {AuthorEditFormService} from "@app/author/author-form/author-edit-form.service";
import {
    AuthorLibraryAwardEditFormService
} from "@app/author/author-form/author-library-award-form-part/author-library-award-edit-form.service";

@Component({
    selector: 'app-author-library-award-table',
    templateUrl: './author-library-award-table.component.html',
    styleUrls: ['./author-library-award-table.component.scss'],
    standalone: true,
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
        MatSlideToggle,
    ],

})
export class AuthorLibraryAwardTableComponent implements OnInit {
    @Input({ required: true }) authorLibraryAwardFormArray!: FormArray;
    @Output() selectAuthorLibraryAwardFormGroup = new EventEmitter<FormGroup>();
    @Output() deleteAuthorLibraryAwardFormGroup = new EventEmitter<FormGroup>();

    displayedColumns: string[] = ['description', 'year', 'actions'];
    dataSource: MatTableDataSource<AbstractControl> = new MatTableDataSource<AbstractControl>();

    isInlineEditingMode: boolean = false;

    constructor(private readonly authorLibraryAwardEditFormService: AuthorLibraryAwardEditFormService) {
    }

    ngOnInit(): void {
        this.dataSource.data = this.authorLibraryAwardFormArray.controls
        this.authorLibraryAwardFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private updateFormData(): void {
        this.dataSource.data = this.authorLibraryAwardFormArray.controls
    }

    onInlineEditModelChanged(change: MatSlideToggleChange): void {
        this.isInlineEditingMode = change.checked
    }

    onAdd(): void {
        const newEntry = this.authorLibraryAwardEditFormService.createEmptyForm()
        this.authorLibraryAwardFormArray.push(newEntry)
    }


    onEdit(authorLibraryAwardFormGroup: FormGroup): void {
        this.selectAuthorLibraryAwardFormGroup.emit(authorLibraryAwardFormGroup);
    }

    onDelete(authorLibraryAwardFormGroup: FormGroup): void {
        this.deleteAuthorLibraryAwardFormGroup.emit(authorLibraryAwardFormGroup);
    }

    descriptionControl(formControl: AbstractControl): FormControl {
        return FormUtil.requiredFormControl(formControl, "description");
    }

    yearControl(formControl: AbstractControl): FormControl {
        return FormUtil.requiredFormControl(formControl, "year");
    }

    descriptionFromControl(formControl: AbstractControl): string | undefined {
        return this.descriptionControl(formControl).value as string | undefined;
    }

    yearFromControl(formControl: AbstractControl): number | undefined {
        return this.yearControl(formControl).value as number | undefined;
    }

    hasError(formControl: AbstractControl | undefined, errorName: string): boolean {
        return formControl ? formControl.hasError(errorName) && formControl.touched : false;
    }
}
