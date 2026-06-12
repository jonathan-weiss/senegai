/* @tt{{{
    @rlb

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
        [ searchValue="opus-magnum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.item.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {Injectable} from '@angular/core';
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {
    OpusMagnumFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-validation.service";
import {
    OpusMagnumFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-initial-value.service";
import {OpusMagnumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-group";
import {OpusMagnumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";

/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

/* @tt{{{ @rlb
    @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
    @replace-value-by-expression
        [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]
        [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

}}}@  */
import {
    ArticulusInteriorFormPartService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part.service";
/* @tt{{{ @rlb  @end-foreach @rla }}}@ */

@Injectable({providedIn: 'root'})
export class OpusMagnumFormPartService {

    constructor(
        private opusMagnumFormValidationService: OpusMagnumFormPartValidationService,
        private opusMagnumFormInitialValueService: OpusMagnumFormPartInitialValueService,
        /* @tt{{{ @rlb
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */
        private articulusInteriorFormPartService: ArticulusInteriorFormPartService,
        /* @tt{{{ @rlb  @end-foreach @rla }}}@ */
    ) {}

    public createInitialOpusMagnumForm(): FormGroup<OpusMagnumFormPartGroup> {
        return new FormGroup({
            /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
            [OpusMagnumFormPartFieldName.campusTextusObligatorius]: new FormControl<string>(
                this.opusMagnumFormInitialValueService.campusTextusObligatoriusInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.campusTextusObligatorius)
                },
            ),
            /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
            /* @tt{{{ @rlb
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="FormControl<string | null>" replaceByExpression="attribute.angularFormControlType" ]

            }}}@  */
            /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
            [OpusMagnumFormPartFieldName.campusTextusOptionalisIsNotNull]: new FormControl<boolean>(
                this.opusMagnumFormInitialValueService.campusTextusOptionalisInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.campusTextusOptionalisIsNotNull)
                },
            ),
            /* @tt{{{ @rlb  @end-if @rla }}}@ */
            [OpusMagnumFormPartFieldName.campusTextusOptionalis]: new FormControl<string | null>(
                this.opusMagnumFormInitialValueService.campusTextusOptionalisInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.campusTextusOptionalis)
                },
            ),
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */
            /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
            [OpusMagnumFormPartFieldName.articulusInteriorSingularis]: this.articulusInteriorFormPartService.createInitialArticulusInteriorForm(),
            [OpusMagnumFormPartFieldName.articulusInteriorList]: new FormArray(
                this.opusMagnumFormInitialValueService.articulusInteriorListInitialValue(),
                {
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.articulusInteriorList)
                },
            ),
            [OpusMagnumFormPartFieldName.campusDieiIsNotNull]: new FormControl<boolean>(
                this.opusMagnumFormInitialValueService.campusDieiInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.campusDieiIsNotNull)
                },
            ),
            [OpusMagnumFormPartFieldName.campusDiei]: new FormControl<Date>(
                this.opusMagnumFormInitialValueService.campusDieiInitialValue(),
                {
                    nonNullable: false,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.campusDiei)
                },
            ),
            [OpusMagnumFormPartFieldName.campusBivalens]: new FormControl<boolean>(
                this.opusMagnumFormInitialValueService.campusBivalensInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.campusBivalens)
                },
            ),
            [OpusMagnumFormPartFieldName.appellatio]: new FormControl<AppellatioEnum>(
                this.opusMagnumFormInitialValueService.appellatioInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.appellatio)
                },
            ),
            [OpusMagnumFormPartFieldName.campusNumerorum]: new FormControl<number>(
                this.opusMagnumFormInitialValueService.campusNumerorumInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.campusNumerorum)
                },
            ),
            [OpusMagnumFormPartFieldName.indexUnicus]: new FormControl<string>(
                this.opusMagnumFormInitialValueService.indexUnicusInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.indexUnicus)
                },
            ),
            /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
        });
    }

    /**
     * patchValue does not create missing FormGroups inside the FormArray.
     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
     * We need to prefill the FormArray with empty values first
     */
    public patchPreparation(form: FormGroup<OpusMagnumFormPartGroup>, opusMagnum: OpusMagnumWTO): void {
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributesWithLists" loopVariable="attribute" ]
            @replace-value-by-expression
                    [ searchValue="articulusInteriorList" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="ArticulusInteriorList" replaceByExpression="attribute.attributeName.pascalCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attribute.attributeName.pascalCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attribute.attributeName.pascalCase" ]

            }}}@  */

        const articulusInteriorListLength = form.controls[OpusMagnumFormPartFieldName.articulusInteriorList].controls.length
        if(articulusInteriorListLength < opusMagnum.articulusInteriorList.length) {
            for (let i = articulusInteriorListLength; i < opusMagnum.articulusInteriorList.length; i++) {
                const articulusInteriorControl = this.articulusInteriorFormPartService.createInitialArticulusInteriorForm()
                const articulurInterior = opusMagnum.articulusInteriorList[i]
                form.controls[OpusMagnumFormPartFieldName.articulusInteriorList].push(articulusInteriorControl)
                this.articulusInteriorFormPartService.patchPreparation(articulusInteriorControl, articulurInterior)
            }
        }
        /* @tt{{{ @end-foreach }}}@ */

        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributesWithItems" loopVariable="attribute" ]
            @replace-value-by-expression
                    [ searchValue="articulusInteriorSingularis" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attribute.attributeName.pascalCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attribute.attributeName.pascalCase" ]

            }}}@  */
        this.articulusInteriorFormPartService.patchPreparation(form.controls[OpusMagnumFormPartFieldName.articulusInteriorSingularis], opusMagnum.articulusInteriorSingularis)
        /* @tt{{{ @end-foreach }}}@ */
    }
}
