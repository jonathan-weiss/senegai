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
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

/* @tt{{{ @remove-blanks-and-linebreak-before-comment
    @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
    @replace-value-by-expression
        [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]
        [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

}}}@  */
import {ArticulusInteriorWTO} from "@app/wto/articulus-interior.wto";
import {
    ArticulusInteriorFormPartService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part.service";
import {
    ArticulusInteriorFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-field-name";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */




@Injectable({providedIn: 'root'})
export class SilvaOptionumFormPartService {

    constructor(
        private silvaOptionumFormValidationService: SilvaOptionumFormPartValidationService,
        private silvaOptionumFormInitialValueService: SilvaOptionumFormPartInitialValueService,
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */
        private articulusInteriorFormPartService: ArticulusInteriorFormPartService,
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
            [SilvaOptionumFormPartFieldName.campusTextusObligatorius]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.campusTextusObligatoriusInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusObligatorius)
                },
            ),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="FormControl<string | null>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

            }}}@  */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.campusTextusOptionalisInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull)
                },
            ),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: new FormControl<string | null>(
                this.silvaOptionumFormInitialValueService.campusTextusOptionalisInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalis)
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
            [SilvaOptionumFormPartFieldName.articulusInteriorList]: new FormArray(
                this.silvaOptionumFormInitialValueService.articulusInteriorListInitialValue(),
                {
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.articulusInteriorList)
                },
            ),
            [SilvaOptionumFormPartFieldName.campusDieiIsNotNull]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.campusDieiInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusDieiIsNotNull)
                },
            ),
            [SilvaOptionumFormPartFieldName.campusDiei]: new FormControl<Date>(
                this.silvaOptionumFormInitialValueService.campusDieiInitialValue(),
                {
                    nonNullable: false,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusDiei)
                },
            ),
            [SilvaOptionumFormPartFieldName.vegetarian]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.vegetarianInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.vegetarian)
                },
            ),
            [SilvaOptionumFormPartFieldName.appellatio]: new FormControl<AppellatioEnum>(
                this.silvaOptionumFormInitialValueService.appellatioInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.appellatio)
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
        // TODO Rename articulusInteriorList to awardList to bypass the name clash when replacing
        // TODO If the attribute is an item attribute, call the formPartService
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributesWithLists" loopVariable="attribute" ]
            @replace-value-by-expression
                    [ searchValue="articulusInteriorList" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="ArticulusInteriorList" replaceByExpression="attribute.attributeName.pascalCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attribute.attributeName.pascalCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attribute.attributeName.pascalCase" ]

            }}}@  */

        const articulusInteriorListLength = form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].controls.length
        if(articulusInteriorListLength < silvaOptionum.articulusInteriorList.length) {
            for (let i = articulusInteriorListLength; i < silvaOptionum.articulusInteriorList.length; i++) {
                form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].push(this.articulusInteriorFormPartService.createInitialArticulusInteriorForm())
            }
        }
        /* @tt{{{ @end-foreach }}}@ */
    }

    public patchSilvaOptionumForm(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {

        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.id].patchValue(silvaOptionum.id);
        form.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius].patchValue(silvaOptionum.campusTextusObligatorius);
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

            @remove-blanks-and-linebreak-after-comment
        }}}@  */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].patchValue(!silvaOptionum.campusTextusOptionalis);
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis].patchValue(silvaOptionum.campusTextusOptionalis ?? null);
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.lastname].patchValue(silvaOptionum.lastname);
        form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].patchValue(silvaOptionum.articulusInteriorList)
        form.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull].patchValue(!silvaOptionum.campusDiei);
        form.controls[SilvaOptionumFormPartFieldName.campusDiei].patchValue(silvaOptionum.campusDiei ?? null);
        form.controls[SilvaOptionumFormPartFieldName.vegetarian].patchValue(silvaOptionum.vegetarian);
        form.controls[SilvaOptionumFormPartFieldName.appellatio].patchValue(silvaOptionum.appellatio);
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
                    [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

                @remove-blanks-and-linebreak-after-comment
            }}}@  */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="!attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
            campusTextusObligatorius: form.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius].getRawValue(),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @else @remove-blanks-and-linebreak-after-comment }}}@ */
            campusTextusOptionalis: form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].value
                ? form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis].getRawValue()
                : null,
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            lastname: form.controls[SilvaOptionumFormPartFieldName.lastname].getRawValue(),
            articulusInteriorList: form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].getRawValue(),
            campusDiei: form.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull].value ? form.controls[SilvaOptionumFormPartFieldName.campusDiei].getRawValue() : null,
            vegetarian: form.controls[SilvaOptionumFormPartFieldName.vegetarian].getRawValue(),
            appellatio: form.controls[SilvaOptionumFormPartFieldName.appellatio].getRawValue(),
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        };
    }
}
