/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityItemFormPartServiceRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @remove-blanks-and-linebreak-after-comment

}}}@ */

import {Injectable} from '@angular/core';
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {
    SilvaOptionumFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-validation.service";
import {
    SilvaOptionumFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-initial-value.service";
import {SilvaOptionumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-field-name";
import {SilvaOptionumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

/* @tt{{{ @remove-blanks-and-linebreak-before-comment
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
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */




@Injectable({providedIn: 'root'})
export class SilvaOptionumFormPartService {

    constructor(
        private silvaOptionumFormValidationService: SilvaOptionumFormPartValidationService,
        private silvaOptionumFormInitialValueService: SilvaOptionumFormPartInitialValueService,
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="libraryAward" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="LibraryAward" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */
        private libraryAwardFormPartService: LibraryAwardFormPartService,
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
    ) {}

    public createInitialSilvaOptionumForm(): FormGroup<SilvaOptionumFormPartGroup> {
        return new FormGroup({
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            [SilvaOptionumFormPartFieldName.id]: new FormControl<string>(
                {
                    value: this.silvaOptionumFormInitialValueService.idInitialValue(),
                    disabled: true, // ID is readonly
                }, {
                    nonNullable: true,
                },
            ),
            [SilvaOptionumFormPartFieldName.firstname]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.firstnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.firstname)
                },
            ),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="FormControl<string | null>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

            }}}@  */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
            [SilvaOptionumFormPartFieldName.nicknameIsNotNull]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.nicknameInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.nicknameIsNotNull)
                },
            ),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
            [SilvaOptionumFormPartFieldName.nickname]: new FormControl<string | null>(
                this.silvaOptionumFormInitialValueService.nicknameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.nickname)
                },
            ),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            [SilvaOptionumFormPartFieldName.lastname]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.lastnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.lastname)
                },
            ),
            [SilvaOptionumFormPartFieldName.libraryAwardList]: new FormArray(
                this.silvaOptionumFormInitialValueService.libraryAwardListInitialValue(),
                {
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.libraryAwardList)
                },
            ),
            [SilvaOptionumFormPartFieldName.birthdayIsNotNull]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.birthdayInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.birthdayIsNotNull)
                },
            ),
            [SilvaOptionumFormPartFieldName.birthday]: new FormControl<Date>(
                this.silvaOptionumFormInitialValueService.birthdayInitialValue(),
                {
                    nonNullable: false,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.birthday)
                },
            ),
            [SilvaOptionumFormPartFieldName.vegetarian]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.vegetarianInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.vegetarian)
                },
            ),
            [SilvaOptionumFormPartFieldName.gender]: new FormControl<GenderEnum>(
                this.silvaOptionumFormInitialValueService.genderInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.gender)
                },
            ),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        });
    }

    /**
     * patchValue does not create missing FormGroups inside the FormArray.
     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
     * We need to prefill the FormArray with empty values first
     */
    public patchPreparation(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {
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

        const libraryAwardListLength = form.controls[SilvaOptionumFormPartFieldName.libraryAwardList].controls.length
        if(libraryAwardListLength < silvaOptionum.libraryAwardList.length) {
            for (let i = libraryAwardListLength; i < silvaOptionum.libraryAwardList.length; i++) {
                form.controls[SilvaOptionumFormPartFieldName.libraryAwardList].push(this.libraryAwardFormPartService.createInitialLibraryAwardForm())
            }
        }
        /* @tt{{{ @end-foreach }}}@ */
    }

    public patchSilvaOptionumForm(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {

        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.id].patchValue(silvaOptionum.id);
        form.controls[SilvaOptionumFormPartFieldName.firstname].patchValue(silvaOptionum.firstname);
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]

            @remove-blanks-and-linebreak-after-comment
        }}}@  */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.nicknameIsNotNull].patchValue(!silvaOptionum.nickname);
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.nickname].patchValue(silvaOptionum.nickname ?? null);
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.lastname].patchValue(silvaOptionum.lastname);
        form.controls[SilvaOptionumFormPartFieldName.libraryAwardList].patchValue(silvaOptionum.libraryAwardList)
        form.controls[SilvaOptionumFormPartFieldName.birthdayIsNotNull].patchValue(!silvaOptionum.birthday);
        form.controls[SilvaOptionumFormPartFieldName.birthday].patchValue(silvaOptionum.birthday ?? null);
        form.controls[SilvaOptionumFormPartFieldName.vegetarian].patchValue(silvaOptionum.vegetarian);
        form.controls[SilvaOptionumFormPartFieldName.gender].patchValue(silvaOptionum.gender);
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    }

    public createSilvaOptionumFromFormData(form: FormGroup<SilvaOptionumFormPartGroup>): SilvaOptionumWTO {
        return {
            /* @tt{{{ @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            id: form.controls[SilvaOptionumFormPartFieldName.id].getRawValue(),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]

                @remove-blanks-and-linebreak-after-comment
            }}}@  */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="!attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
            firstname: form.controls[SilvaOptionumFormPartFieldName.firstname].getRawValue(),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @else @remove-blanks-and-linebreak-after-comment }}}@ */
            nickname: form.controls[SilvaOptionumFormPartFieldName.nicknameIsNotNull].value
                ? form.controls[SilvaOptionumFormPartFieldName.nickname].getRawValue()
                : null,
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            lastname: form.controls[SilvaOptionumFormPartFieldName.lastname].getRawValue(),
            libraryAwardList: form.controls[SilvaOptionumFormPartFieldName.libraryAwardList].getRawValue(),
            birthday: form.controls[SilvaOptionumFormPartFieldName.birthdayIsNotNull].value ? form.controls[SilvaOptionumFormPartFieldName.birthday].getRawValue() : null,
            vegetarian: form.controls[SilvaOptionumFormPartFieldName.vegetarian].getRawValue(),
            gender: form.controls[SilvaOptionumFormPartFieldName.gender].getRawValue(),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        };
    }
}
