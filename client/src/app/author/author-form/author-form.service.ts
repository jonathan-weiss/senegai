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
import {AbstractControl, FormArray, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {AuthorFormFieldName} from "@app/author/author-form/author-form-field-name";
import {AuthorFormValidationService} from "@app/author/author-form/author-form-validation.service";
import {AuthorFormInitialValueService} from "@app/author/author-form/author-form-initial-value.service";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {AuthorLibraryAward} from "@app/author/author-library-award.model";
import {
    AuthorLibraryAwardEditFormService
} from "@app/author/author-form/author-library-award-form-part/author-library-award-edit-form.service";
import {GenderEnum} from "@app/author/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormService {

    constructor(
        private authorFormValidationService: AuthorFormValidationService,
        private authorFormInitialValueService: AuthorFormInitialValueService,
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        private authorLibraryAwardEditFormService: AuthorLibraryAwardEditFormService,
        /* @tt{{{ @slbc  @end-ignore-text }}}@ */
    ) {}

    public createEmptyForm(): FormGroup {
        /* @tt{{{ @slbc
            @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

        }}}@  */
        const firstnameInitialValue = this.authorFormInitialValueService.firstnameInitialValue()
        const firstnameValidatorFunctions = this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.firstname)
        /* @tt{{{ @slbc @end-foreach }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        const nicknameInitialValue = this.authorFormInitialValueService.nicknameInitialValue()
        const nicknameValidatorFunctions = this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.nickname)
        const lastnameInitialValue = this.authorFormInitialValueService.lastnameInitialValue()
        const lastnameValidatorFunctions = this.authorFormValidationService.validatorFunctions(AuthorFormFieldName.lastname)
        /* @tt{{{ @slbc  @end-ignore-text }}}@ */
        return new FormGroup({
            id: new FormControl<number>({value: 0, disabled: true}), // ID is readonly
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

            }}}@  */
            [AuthorFormFieldName.firstname]: new FormControl<string>(firstnameInitialValue, firstnameValidatorFunctions),
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            [AuthorFormFieldName.lastname]: new FormControl<string>(lastnameInitialValue, lastnameValidatorFunctions),
            [AuthorFormFieldName.nicknameIsNotNull]: new FormControl<boolean>(nicknameInitialValue != null),
            [AuthorFormFieldName.nickname]: new FormControl<string | null>(nicknameInitialValue, nicknameValidatorFunctions),
            // TODO continue here using initial values and validator functions
            libraryAwardList: new FormArray([]),
            birthdayIsNotNull: new FormControl<boolean>(true),
            birthday: new FormControl<Date | null>(null),
            vegetarian: new FormControl<boolean>(false),
            gender: new FormControl<GenderEnum>(GenderEnum.FEMALE),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        });
    }

    public patchForm(form: AbstractControl, author: Author): void {
        FormUtil.requiredFormControl(form, "id").patchValue(author.id);
        /* @tt{{{ @slbc
            @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

            @slac
        }}}@  */
        FormUtil.requiredFormControl(form, "firstname").patchValue(author.firstname);
        /* @tt{{{ @slbc @end-foreach @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        FormUtil.requiredFormControl(form, "nicknameIsNotNull").patchValue(!author.nickname);
        FormUtil.requiredFormControl(form, "nickname").patchValue(author.nickname ?? null);
        FormUtil.requiredFormControl(form, "lastname").patchValue(author.lastname);
        const libraryAwardList = FormUtil.requiredFormArray(form, "libraryAwardList");
        author.libraryAwardList.forEach((libraryAward: AuthorLibraryAward) => {
            const formGroup = this.authorLibraryAwardEditFormService.createEmptyForm()
            this.authorLibraryAwardEditFormService.patchForm(formGroup, libraryAward);
            libraryAwardList.push(formGroup);
        })
        FormUtil.requiredFormControl(form, "birthdayIsNotNull").patchValue(!author.birthday);
        FormUtil.requiredFormControl(form, "birthday").patchValue(author.birthday ?? null);
        FormUtil.requiredFormControl(form, "vegetarian").patchValue(author.vegetarian);
        FormUtil.requiredFormControl(form, "gender").patchValue(author.gender);
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    }

    public createAuthorFromFormData(form: AbstractControl): Author {
        return {
            id: FormUtil.requiredFormControl(form, "id").value as number,
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]
                    [ searchValue="string" replaceByExpression="attribute.typescriptAttributeType" ]

                @slac
            }}}@  */
            firstname: FormUtil.requiredFormControl(form, "firstname").value as string,
            /* @tt{{{ @slbc @end-foreach  @end-replace-value-by-expression @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            nickname: FormUtil.requiredFormControl(form, "nicknameIsNotNull").value ? FormUtil.requiredFormControl(form, "nickname").value as string : null,
            lastname: FormUtil.requiredFormControl(form, "lastname").value as string,
            libraryAwardList: FormUtil.requiredFormArray(form, "libraryAwardList")
                .controls.map(control => this.authorLibraryAwardEditFormService.createAuthorFromFormData(control)),
            birthday: FormUtil.requiredFormControl(form, "birthdayIsNotNull").value ? FormUtil.requiredFormControl(form, "birthday").value as Date : null,
            vegetarian: FormUtil.requiredFormControl(form, "vegetarian").value as boolean,
            gender: FormUtil.requiredFormControl(form, "gender").value as GenderEnum,
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        };
    }
} 
