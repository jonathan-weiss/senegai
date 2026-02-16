/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemEditFormServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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

import {Injectable} from '@angular/core';
import {Author} from "@app/author/author.model";
import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    AuthorFormFieldName,
    AuthorFormGroup,
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    AuthorFormLibraryAwardListFormGroup,
    /* @tt{{{ @slbc  @end-ignore-text  }}}@ */
} from "@app/author/author-form/author-form-field-name";
import {AuthorFormValidationService} from "@app/author/author-form/author-form-validation.service";
import {AuthorFormInitialValueService} from "@app/author/author-form/author-form-initial-value.service";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {AuthorLibraryAward} from "@app/author/author-library-award.model";
import {GenderEnum} from "@app/author/gender.enum";

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormService {

    constructor(
        private authorFormValidationService: AuthorFormValidationService,
        private authorFormInitialValueService: AuthorFormInitialValueService,
    ) {}

    public createInitialAuthorForm(): FormGroup<AuthorFormGroup> {
        return new FormGroup({
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            [AuthorFormFieldName.id]: new FormControl<string>(
                {
                    value: this.authorFormInitialValueService.idInitialValue(),
                    disabled: true, // ID is readonly
                }, {
                    nonNullable: true,
                },
            ),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

            }}}@  */
            [AuthorFormFieldName.firstname]: new FormControl<string>(
                this.authorFormInitialValueService.firstnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.firstname)
                },
            ),
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            [AuthorFormFieldName.lastname]: new FormControl<string>(
                this.authorFormInitialValueService.lastnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.lastname)
                },
            ),
            [AuthorFormFieldName.nicknameIsNotNull]: new FormControl<boolean>(
                this.authorFormInitialValueService.nicknameInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.nicknameIsNotNull)
                },
            ),
            [AuthorFormFieldName.nickname]: new FormControl<string | null>(
                this.authorFormInitialValueService.nicknameInitialValue(),
                {
                    nonNullable: false,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.nickname)
                },
            ),
            [AuthorFormFieldName.libraryAwardList]: new FormArray(
                [] as Array<FormGroup<AuthorFormLibraryAwardListFormGroup>>,
                {
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.libraryAwardList)
                },
            ),
            [AuthorFormFieldName.birthdayIsNotNull]: new FormControl<boolean>(
                this.authorFormInitialValueService.birthdayInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.birthdayIsNotNull)
                },
            ),
            [AuthorFormFieldName.birthday]: new FormControl<Date>(
                this.authorFormInitialValueService.birthdayInitialValue(),
                {
                    nonNullable: false,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.birthday)
                },
            ),
            [AuthorFormFieldName.vegetarian]: new FormControl<boolean>(
                this.authorFormInitialValueService.vegetarianInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.vegetarian)
                },
            ),
            [AuthorFormFieldName.gender]: new FormControl<GenderEnum>(
                this.authorFormInitialValueService.genderInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.gender)
                },
            ),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        });
    }

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    public createInitialLibraryAwardListForm(): FormGroup<AuthorFormLibraryAwardListFormGroup> {
        return new FormGroup({
            [AuthorFormFieldName.libraryAwardListDescription]: new FormControl<string>(
                this.authorFormInitialValueService.libraryAwardListDescriptionInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.libraryAwardListDescription)
                },
            ),
            [AuthorFormFieldName.libraryAwardListYear]: new FormControl<number>(
                this.authorFormInitialValueService.libraryAwardListYearInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.libraryAwardListYear)
                },
            ),
            [AuthorFormFieldName.libraryAwardListJuryList]: new FormArray<FormControl<string>>(
                [] as Array<FormControl<string>>,
                {
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.libraryAwardListYear)
                },
            ),
        });
    }

    public createInitialLibraryAwardListJuryListForm(): FormControl<string> {
        return new FormControl<string>(
            this.authorFormInitialValueService.libraryAwardListJuryListInitialValue(),
            {
                nonNullable: true,
                validators: this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.libraryAwardListJuryList)
            },
        )
    }
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

    public patchAuthorForm(form: AbstractControl, author: Author): void {
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        FormUtil.requiredFormControl(form, AuthorFormFieldName.id).patchValue(author.id);
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

            @slac
        }}}@  */
        FormUtil.requiredFormControl(form, AuthorFormFieldName.firstname).patchValue(author.firstname);
        /* @tt{{{ @slbc @end-foreach @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        FormUtil.requiredFormControl(form, AuthorFormFieldName.nicknameIsNotNull).patchValue(!author.nickname);
        FormUtil.requiredFormControl(form, AuthorFormFieldName.nickname).patchValue(author.nickname ?? null);
        FormUtil.requiredFormControl(form, AuthorFormFieldName.lastname).patchValue(author.lastname);
        const libraryAwardList = FormUtil.requiredFormArray(form, AuthorFormFieldName.libraryAwardList);
        author.libraryAwardList.forEach((libraryAward: AuthorLibraryAward) => {
            const formGroup = this.createInitialLibraryAwardListForm()
            this.patchLibraryAwardListForm(formGroup, libraryAward);
            libraryAwardList.push(formGroup);
        })
        FormUtil.requiredFormControl(form, AuthorFormFieldName.birthdayIsNotNull).patchValue(!author.birthday);
        FormUtil.requiredFormControl(form, AuthorFormFieldName.birthday).patchValue(author.birthday ?? null);
        FormUtil.requiredFormControl(form, AuthorFormFieldName.vegetarian).patchValue(author.vegetarian);
        FormUtil.requiredFormControl(form, AuthorFormFieldName.gender).patchValue(author.gender);
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    }

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    public patchLibraryAwardListForm(form: AbstractControl, authorLibraryAward: AuthorLibraryAward): void {
        FormUtil.requiredFormControl(form, AuthorFormFieldName.libraryAwardListDescription).patchValue(authorLibraryAward.description);
        FormUtil.requiredFormControl(form, AuthorFormFieldName.libraryAwardListYear).patchValue(authorLibraryAward.year);
        authorLibraryAward.juryList.forEach((jury: string) => {
            const formGroup = this.createInitialLibraryAwardListJuryListForm()
            formGroup.patchValue(jury)
            FormUtil.requiredFormArray(form, AuthorFormFieldName.libraryAwardListJuryList).push(formGroup);
        })
    }
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

    public createAuthorFromFormData(form: AbstractControl): Author {
        return {
            /* @tt{{{ @ignore-text @slac }}}@ */
            id: FormUtil.requiredFormControl(form, AuthorFormFieldName.id).value as string,
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            /* @tt{{{
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

                @slac
            }}}@  */
            firstname: FormUtil.requiredFormControl(form, AuthorFormFieldName.firstname).value as string,
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            nickname: FormUtil.requiredFormControl(form, AuthorFormFieldName.nicknameIsNotNull).value
                ? FormUtil.requiredFormControl(form, AuthorFormFieldName.nickname).value as string
                : null,
            lastname: FormUtil.requiredFormControl(form, AuthorFormFieldName.lastname).value as string,
            libraryAwardList: FormUtil.requiredFormArray(form, AuthorFormFieldName.libraryAwardList)
                .controls.map(control => this.createAuthorLibraryAwardListFromFormData(control)),
            birthday: FormUtil.requiredFormControl(form, AuthorFormFieldName.birthdayIsNotNull).value ? FormUtil.requiredFormControl(form, AuthorFormFieldName.birthday).value as Date : null,
            vegetarian: FormUtil.requiredFormControl(form, AuthorFormFieldName.vegetarian).value as boolean,
            gender: FormUtil.requiredFormControl(form, AuthorFormFieldName.gender).value as GenderEnum,
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        };
    }

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    public createAuthorLibraryAwardListFromFormData(form: AbstractControl): AuthorLibraryAward {
        return {
            description: FormUtil.requiredFormControl(form, AuthorFormFieldName.libraryAwardListDescription).value as string,
            year: FormUtil.requiredFormControl(form, AuthorFormFieldName.libraryAwardListYear).value as number,
            juryList: FormUtil.requiredFormArray(form, AuthorFormFieldName.libraryAwardListJuryList)
                .controls.map(control => control.value as string),
        };
    }
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

} 
