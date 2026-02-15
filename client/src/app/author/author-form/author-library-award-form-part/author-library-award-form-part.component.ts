
import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatDialogModule} from "@angular/material/dialog";
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    AuthorLibraryAwardTableComponent
} from "@app/author/author-form/author-library-award-table/author-library-award-table.component";
import {
    AuthorLibraryAwardJuryTableComponent
} from "@app/author/author-form/author-library-award-jury-table/author-library-award-jury-table.component";
import {AuthorFormFieldName, AuthorFormLibraryAwardListFormGroup} from "@app/author/author-form/author-form-field-name";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {AuthorFormValidationService} from "@app/author/author-form/author-form-validation.service";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {NumberInputComponent} from "@app/shared/form-controls/number-input/number-input.component";

@Component({
    selector: 'app-author-library-award-form-part',
    templateUrl: './author-library-award-form-part.component.html',
    styleUrls: ['./author-library-award-form-part.component.scss'],
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
        FieldWrapperComponent,
        AuthorLibraryAwardJuryTableComponent,
        TextInputComponent,
        NumberInputComponent,
    ]
})
export class AuthorLibraryAwardFormPartComponent implements OnInit {
    @Input({ required: true}) authorLibraryAwardListForm!: FormGroup<AuthorFormLibraryAwardListFormGroup>;

    constructor(private readonly authorFormValidationService: AuthorFormValidationService,) {
    }

    protected authorLibraryAwardListDescriptionControl!: FormControl<string>
    protected authorLibraryAwardListDescriptionValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected authorLibraryAwardListYearControl!: FormControl<number>
    protected authorLibraryAwardListYearValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected authorLibraryAwardListJuryListFormArray!: FormArray<FormControl<string>>
    protected authorLibraryAwardListJuryListValidatorNames!: ReadonlyArray<ValidatorTranslation>

    ngOnInit() {
        this.authorLibraryAwardListDescriptionControl = FormUtil.requiredFormControl(this.authorLibraryAwardListForm, AuthorFormFieldName.libraryAwardListDescription)
        this.authorLibraryAwardListDescriptionValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.libraryAwardListDescription)
        this.authorLibraryAwardListYearControl = FormUtil.requiredFormControl(this.authorLibraryAwardListForm, AuthorFormFieldName.libraryAwardListYear)
        this.authorLibraryAwardListYearValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.libraryAwardListYear)
        this.authorLibraryAwardListJuryListFormArray = FormUtil.requiredFormArray(this.authorLibraryAwardListForm, AuthorFormFieldName.libraryAwardListJuryList)
        this.authorLibraryAwardListJuryListValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.libraryAwardListJuryList)
    }

    hasError(controlName: string, errorName: string): boolean {
        return FormUtil.hasError(this.authorLibraryAwardListForm, controlName, errorName)
    }
}
