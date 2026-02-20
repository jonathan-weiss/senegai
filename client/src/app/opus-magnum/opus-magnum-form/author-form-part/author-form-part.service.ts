/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="author" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {AuthorWTO} from "@app/wto/author.wto";
import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    AuthorFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-validation.service";
import {
    AuthorFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-initial-value.service";
import {AuthorFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
import {AuthorFormPartGroup} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-group";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {GenderEnum} from "@app/wto/gender.enum";
import {
    LibraryAwardFormPartService
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part.service";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormPartService {

    constructor(
        private authorFormValidationService: AuthorFormPartValidationService,
        private authorFormInitialValueService: AuthorFormPartInitialValueService,
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        private libraryAwardFormPartService: LibraryAwardFormPartService,
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    ) {}

    public createInitialAuthorForm(): FormGroup<AuthorFormPartGroup> {
        return new FormGroup({
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            [AuthorFormPartFieldName.id]: new FormControl<string>(
                {
                    value: this.authorFormInitialValueService.idInitialValue(),
                    disabled: true, // ID is readonly
                }, {
                    nonNullable: true,
                },
            ),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]

            }}}@  */
            [AuthorFormPartFieldName.firstname]: new FormControl<string>(
                this.authorFormInitialValueService.firstnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.firstname)
                },
            ),
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            [AuthorFormPartFieldName.lastname]: new FormControl<string>(
                this.authorFormInitialValueService.lastnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.lastname)
                },
            ),
            [AuthorFormPartFieldName.nicknameIsNotNull]: new FormControl<boolean>(
                this.authorFormInitialValueService.nicknameInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.nicknameIsNotNull)
                },
            ),
            [AuthorFormPartFieldName.nickname]: new FormControl<string | null>(
                this.authorFormInitialValueService.nicknameInitialValue(),
                {
                    nonNullable: false,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.nickname)
                },
            ),
            [AuthorFormPartFieldName.libraryAwardList]: new FormArray(
                [] as Array<FormGroup<LibraryAwardFormPartGroup>>,
                {
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.libraryAwardList)
                },
            ),
            [AuthorFormPartFieldName.birthdayIsNotNull]: new FormControl<boolean>(
                this.authorFormInitialValueService.birthdayInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.birthdayIsNotNull)
                },
            ),
            [AuthorFormPartFieldName.birthday]: new FormControl<Date>(
                this.authorFormInitialValueService.birthdayInitialValue(),
                {
                    nonNullable: false,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.birthday)
                },
            ),
            [AuthorFormPartFieldName.vegetarian]: new FormControl<boolean>(
                this.authorFormInitialValueService.vegetarianInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.vegetarian)
                },
            ),
            [AuthorFormPartFieldName.gender]: new FormControl<GenderEnum>(
                this.authorFormInitialValueService.genderInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.gender)
                },
            ),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        });
    }

    public patchAuthorForm(form: FormGroup<AuthorFormPartGroup>, author: AuthorWTO): void {
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.id).patchValue(author.id);
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]

            @slac
        }}}@  */
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.firstname).patchValue(author.firstname);
        /* @tt{{{ @slbc @end-foreach @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.nicknameIsNotNull).patchValue(!author.nickname);
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.nickname).patchValue(author.nickname ?? null);
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.lastname).patchValue(author.lastname);
        const libraryAwardList = FormUtil.requiredFormArray(form, AuthorFormPartFieldName.libraryAwardList);
        author.libraryAwardList.forEach((libraryAward: LibraryAwardWTO) => {
            const formGroup = this.libraryAwardFormPartService.createInitialLibraryAwardForm()
            this.libraryAwardFormPartService.patchLibraryAwardForm(formGroup, libraryAward);
            libraryAwardList.push(formGroup);
        })
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.birthdayIsNotNull).patchValue(!author.birthday);
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.birthday).patchValue(author.birthday ?? null);
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.vegetarian).patchValue(author.vegetarian);
        FormUtil.requiredFormControl(form, AuthorFormPartFieldName.gender).patchValue(author.gender);
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    }

    public createAuthorFromFormData(form: AbstractControl): AuthorWTO {
        return {
            /* @tt{{{ @ignore-text @slac }}}@ */
            id: FormUtil.requiredFormControl(form, AuthorFormPartFieldName.id).value as string,
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            /* @tt{{{
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]

                @slac
            }}}@  */
            firstname: FormUtil.requiredFormControl(form, AuthorFormPartFieldName.firstname).value as string,
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            nickname: FormUtil.requiredFormControl(form, AuthorFormPartFieldName.nicknameIsNotNull).value
                ? FormUtil.requiredFormControl(form, AuthorFormPartFieldName.nickname).value as string
                : null,
            lastname: FormUtil.requiredFormControl(form, AuthorFormPartFieldName.lastname).value as string,
            libraryAwardList: FormUtil.requiredFormArray<FormGroup<LibraryAwardFormPartGroup>>(form, AuthorFormPartFieldName.libraryAwardList)
                .controls.map(control => this.libraryAwardFormPartService.createLibraryAwardFromFormData(control)),
            birthday: FormUtil.requiredFormControl(form, AuthorFormPartFieldName.birthdayIsNotNull).value ? FormUtil.requiredFormControl(form, AuthorFormPartFieldName.birthday).value as Date : null,
            vegetarian: FormUtil.requiredFormControl(form, AuthorFormPartFieldName.vegetarian).value as boolean,
            gender: FormUtil.requiredFormControl(form, AuthorFormPartFieldName.gender).value as GenderEnum,
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        };
    }
}
