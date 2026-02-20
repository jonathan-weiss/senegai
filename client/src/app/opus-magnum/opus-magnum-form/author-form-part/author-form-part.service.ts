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
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {
    AuthorFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-validation.service";
import {
    AuthorFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-initial-value.service";
import {AuthorFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
import {AuthorFormPartGroup} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-group";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

/* @tt{{{ @slbc
    @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
    @replace-value-by-expression
        [ searchValue="library-award" replaceByExpression="nestedItem.itemName.kebabCase" ]
        [ searchValue="libraryAward" replaceByExpression="nestedItem.itemName.camelCase" ]
        [ searchValue="LibraryAward" replaceByExpression="nestedItem.itemName.pascalCase" ]

}}}@  */
import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {
    LibraryAwardFormPartService
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part.service";
import {
    LibraryAwardFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-field-name";
/* @tt{{{ @slbc  @end-foreach @slac }}}@ */




@Injectable({providedIn: 'root'})
export class AuthorFormPartService {

    constructor(
        private authorFormValidationService: AuthorFormPartValidationService,
        private authorFormInitialValueService: AuthorFormPartInitialValueService,
        /* @tt{{{ @slbc
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="libraryAward" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="LibraryAward" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */
        private libraryAwardFormPartService: LibraryAwardFormPartService,
        /* @tt{{{ @slbc  @end-foreach @slac }}}@ */
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
            [AuthorFormPartFieldName.firstname]: new FormControl<string>(
                this.authorFormInitialValueService.firstnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.firstname)
                },
            ),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="FormControl<string | null>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

            }}}@  */
            /* @tt{{{ @slbc  @if [ conditionExpression="attribute.isNullable"] @slac }}}@ */
            [AuthorFormPartFieldName.nicknameIsNotNull]: new FormControl<boolean>(
                this.authorFormInitialValueService.nicknameInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.nicknameIsNotNull)
                },
            ),
            /* @tt{{{ @slbc  @end-if @slac }}}@ */
            [AuthorFormPartFieldName.nickname]: new FormControl<string | null>(
                this.authorFormInitialValueService.nicknameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.authorFormValidationService.validatorFunctions(AuthorFormPartFieldName.nickname)
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
            [AuthorFormPartFieldName.libraryAwardList]: new FormArray(
                this.authorFormInitialValueService.libraryAwardListInitialValue(),
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

    /**
     * patchValue does not create missing FormGroups inside the FormArray.
     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
     * We need to prefill the FormArray with empty values first
     */
    public patchPreparation(form: FormGroup<AuthorFormPartGroup>, author: AuthorWTO): void {
        // TODO Rename libraryAwardList to awardList to bypass the name clash when replacing
        // TODO If the attribute is an item attribute, call the formPartService
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributesWithLists" loopVariable="attribute" ]
            @replace-value-by-expression
                    [ searchValue="libraryAwardList" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="LibraryAwardList" replaceByExpression="attribute.attributeName.pascalCase" ]
                    [ searchValue="libraryAward" replaceByExpression="attribute.attributeName.pascalCase" ]
                    [ searchValue="LibraryAward" replaceByExpression="attribute.attributeName.pascalCase" ]

            }}}@  */

        const libraryAwardListLength = form.controls[AuthorFormPartFieldName.libraryAwardList].controls.length
        if(libraryAwardListLength < author.libraryAwardList.length) {
            for (let i = libraryAwardListLength; i < author.libraryAwardList.length; i++) {
                form.controls[AuthorFormPartFieldName.libraryAwardList].push(this.libraryAwardFormPartService.createInitialLibraryAwardForm())
            }
        }
        /* @tt{{{ @end-foreach }}}@ */
    }

    public patchAuthorForm(form: FormGroup<AuthorFormPartGroup>, author: AuthorWTO): void {

        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        form.controls[AuthorFormPartFieldName.id].patchValue(author.id);
        form.controls[AuthorFormPartFieldName.firstname].patchValue(author.firstname);
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]

            @slac
        }}}@  */
        /* @tt{{{ @slbc  @if [ conditionExpression="attribute.isNullable"] @slac }}}@ */
        form.controls[AuthorFormPartFieldName.nicknameIsNotNull].patchValue(!author.nickname);
        /* @tt{{{ @slbc  @end-if @slac }}}@ */
        form.controls[AuthorFormPartFieldName.nickname].patchValue(author.nickname ?? null);
        /* @tt{{{ @slbc @end-foreach @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        form.controls[AuthorFormPartFieldName.lastname].patchValue(author.lastname);
        form.controls[AuthorFormPartFieldName.libraryAwardList].patchValue(author.libraryAwardList)
        form.controls[AuthorFormPartFieldName.birthdayIsNotNull].patchValue(!author.birthday);
        form.controls[AuthorFormPartFieldName.birthday].patchValue(author.birthday ?? null);
        form.controls[AuthorFormPartFieldName.vegetarian].patchValue(author.vegetarian);
        form.controls[AuthorFormPartFieldName.gender].patchValue(author.gender);
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    }

    public createAuthorFromFormData(form: FormGroup<AuthorFormPartGroup>): AuthorWTO {
        return {
            /* @tt{{{ @ignore-text @slac }}}@ */
            id: form.controls[AuthorFormPartFieldName.id].getRawValue(),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            /* @tt{{{
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]

                @slac
            }}}@  */
            /* @tt{{{ @slbc  @if [ conditionExpression="!attribute.isNullable"] @slac }}}@ */
            firstname: form.controls[AuthorFormPartFieldName.firstname].getRawValue(),
            /* @tt{{{ @slbc  @else @slac }}}@ */
            nickname: form.controls[AuthorFormPartFieldName.nicknameIsNotNull].value
                ? form.controls[AuthorFormPartFieldName.nickname].getRawValue()
                : null,
            /* @tt{{{ @slbc  @end-if @slac }}}@ */
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            lastname: form.controls[AuthorFormPartFieldName.lastname].getRawValue(),
            libraryAwardList: form.controls[AuthorFormPartFieldName.libraryAwardList].getRawValue(),
            birthday: form.controls[AuthorFormPartFieldName.birthdayIsNotNull].value ? form.controls[AuthorFormPartFieldName.birthday].getRawValue() : null,
            vegetarian: form.controls[AuthorFormPartFieldName.vegetarian].getRawValue(),
            gender: form.controls[AuthorFormPartFieldName.gender].getRawValue(),
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        };
    }
}
