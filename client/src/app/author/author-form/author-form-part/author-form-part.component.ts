/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemFormPartComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="ItemModel"
        modelPackageName="senegai.codegen.renderer.model"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */
import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {FormUtil} from "@app/shared/form-controls/form.util";
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {AuthorFormValidationService} from "@app/author/author-form/author-form-validation.service";
import {
    AuthorFormFieldName,
    AuthorFormGroup,
    AuthorFormLibraryAwardListFormGroup
} from "@app/author/author-form/author-form-field-name";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {
    AuthorLibraryAwardTableComponent
} from "@app/author/author-form/author-library-award-table/author-library-award-table.component";
import {
    AuthorLibraryAwardFormPartComponent
} from "@app/author/author-form/author-library-award-form-part/author-library-award-form-part.component";
import {GenderEnum, GenderEnumValues} from "@app/author/gender.enum";
import {GenderI18nComponent} from "@app/author/gender-i18n/gender-i18n.component";
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

@Component({
    selector: 'app-author-form-part',
    templateUrl: './author-form-part.component.html',
    styleUrls: ['./author-form-part.component.scss'],
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
        TextInputComponent,
        MatTabGroup,
        MatTab,
        /* @tt{{{ @slbc  @ignore-text }}}@ */
        DatepickerInputComponent,
        MatSelect,
        MatOption,
        AuthorLibraryAwardTableComponent,
        AuthorLibraryAwardFormPartComponent,
        GenderI18nComponent,
        BooleanInputComponent,
        /* @tt{{{ @slbc  @end-ignore-text }}}@ */
    ]
})
export class AuthorFormPartComponent implements OnInit {
    @Input({ required: true }) authorForm!: FormGroup<AuthorFormGroup>;

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    authorLibraryAwardUnderEdit: FormGroup<AuthorFormLibraryAwardListFormGroup> | undefined = undefined;
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

    protected idControl!: FormControl<number>
    /* @tt{{{
    @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

    @replace-value-by-expression
        [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

    @slac
    }}}@  */
    protected firstnameControl!: FormControl<string>
    protected firstnameValidatorNames!: ReadonlyArray<ValidatorTranslation>

    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */

    protected nicknameIsNotNullControl!: FormControl<boolean>
    protected nicknameIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected nicknameControl!: FormControl<string | null>
    protected nicknameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected lastnameControl!: FormControl<string>
    protected lastnameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected libraryAwardListFormArray!: FormArray<FormGroup<AuthorFormLibraryAwardListFormGroup>>
    protected libraryAwardListValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayIsNotNullControl!: FormControl<boolean>
    protected birthdayIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayControl!: FormControl<Date | null>
    protected birthdayValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected vegetarianControl!: FormControl<boolean>
    protected vegetarianValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected genderControl!: FormControl<GenderEnum>
    protected genderValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

    constructor(private readonly authorFormValidationService: AuthorFormValidationService,) {
    }

    ngOnInit() {
        this.idControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.id);
        /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

        }}}@  */
        this.firstnameControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.firstname)
        this.firstnameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.firstname)

        /* @tt{{{ @slbc @end-foreach @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        this.nicknameIsNotNullControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.nicknameIsNotNull)
        this.nicknameIsNotNullValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.nicknameIsNotNull)
        this.nicknameControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.nickname)
        this.nicknameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.nickname)
        this.lastnameControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.lastname)
        this.lastnameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.lastname)
        this.libraryAwardListFormArray = FormUtil.requiredFormArray(this.authorForm, AuthorFormFieldName.libraryAwardList)
        this.libraryAwardListValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.libraryAwardList)
        this.birthdayIsNotNullControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.birthdayIsNotNull)
        this.birthdayIsNotNullValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.birthdayIsNotNull)
        this.birthdayControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.birthday)
        this.birthdayValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.birthday)
        this.vegetarianControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.vegetarian)
        this.vegetarianValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.vegetarian)
        this.genderControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormFieldName.gender)
        this.genderValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormFieldName.gender)
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    }

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */

    protected genderList = GenderEnumValues

    onAuthorLibraryAwardFormGroupEdit(formGroup: FormGroup<AuthorFormLibraryAwardListFormGroup>): void {
        this.authorLibraryAwardUnderEdit = formGroup;
    }

    onAuthorLibraryAwardFormGroupDelete(formGroup: FormGroup<AuthorFormLibraryAwardListFormGroup>): void {
        if(this.authorLibraryAwardUnderEdit == formGroup) {
            this.authorLibraryAwardUnderEdit = undefined
        }
        FormUtil.removeControl(this.libraryAwardListFormArray, formGroup)
    }

    closeAuthorLibraryAwardUnderEdit(): void {
        this.authorLibraryAwardUnderEdit = undefined;
    }
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}
