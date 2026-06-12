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
      [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
      [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]
      [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {Injectable} from '@angular/core';
import {SilvaOptionumWTO} from "@app/wto/silva-optionum-wto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {
    SilvaOptionumFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-validation.service";
import {
    SilvaOptionumFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-initial-value.service";
import {SilvaOptionumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";
import {SilvaOptionumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-field-name";

/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

/* @tt{{{ @rlb
    @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="directlyNestedItem" ]

    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="directlyNestedItem.itemName.pascalCase" ]
        [ searchValue="articulus-interior" replaceByExpression="directlyNestedItem.itemName.kebabCase" ]

}}}@  */
import {
    ArticulusInteriorFormPartService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part.service";
/* @tt{{{ @rlb  @end-foreach @rla }}}@ */

@Injectable({providedIn: 'root'})
export class SilvaOptionumFormPartService {

    constructor(
        private silvaOptionumFormValidationService: SilvaOptionumFormPartValidationService,
        private silvaOptionumFormInitialValueService: SilvaOptionumFormPartInitialValueService,
        /* @tt{{{ @rlb
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */
        private articulusInteriorFormPartService: ArticulusInteriorFormPartService,
        /* @tt{{{ @rlb  @end-foreach @rla }}}@ */
    ) {}

    public createInitialSilvaOptionumForm(): FormGroup<SilvaOptionumFormPartGroup> {
        return new FormGroup({
            /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusObligatorius]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.campusTextusObligatoriusInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusObligatorius)
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
            [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.campusTextusOptionalisInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull)
                },
            ),
            /* @tt{{{ @rlb  @end-if @rla }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: new FormControl<string | null>(
                this.silvaOptionumFormInitialValueService.campusTextusOptionalisInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalis)
                },
            ),
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */
            /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
            [SilvaOptionumFormPartFieldName.articulusInteriorSingularis]: this.articulusInteriorFormPartService.createInitialArticulusInteriorForm(),
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
            [SilvaOptionumFormPartFieldName.campusBivalens]: new FormControl<boolean>(
                this.silvaOptionumFormInitialValueService.campusBivalensInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusBivalens)
                },
            ),
            [SilvaOptionumFormPartFieldName.appellatio]: new FormControl<AppellatioEnum>(
                this.silvaOptionumFormInitialValueService.appellatioInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.appellatio)
                },
            ),
            [SilvaOptionumFormPartFieldName.campusNumerorum]: new FormControl<number>(
                this.silvaOptionumFormInitialValueService.campusNumerorumInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusNumerorum)
                },
            ),
            [SilvaOptionumFormPartFieldName.indexUnicus]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.indexUnicusInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.indexUnicus)
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
    public patchPreparation(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {
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
                const articulusInteriorControl = this.articulusInteriorFormPartService.createInitialArticulusInteriorForm()
                const articulurInterior = silvaOptionum.articulusInteriorList[i]
                form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].push(articulusInteriorControl)
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
        this.articulusInteriorFormPartService.patchPreparation(form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis], silvaOptionum.articulusInteriorSingularis)
        /* @tt{{{ @end-foreach }}}@ */
    }
}
