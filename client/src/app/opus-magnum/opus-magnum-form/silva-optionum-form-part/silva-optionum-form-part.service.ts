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
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
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
    @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="nestedItem" ]

    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]
        [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]

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
            @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="nestedItem" ]
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
                    [ searchValue="FormControl<string>" replaceByExpression="attribute.angularFormControlType" ]


            }}}@  */
            /* @tt{{{ @rlb  @if [ conditionExpression="!attribute.isItem"] @rla }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.campusTextusOptionalisInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalis)
                },
            ),
            /* @tt{{{ @rlb  @end-if @rla }}}@ */
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */

            /* @tt{{{ @rlb
                @foreach [ iteratorExpression="model.item.attributesWithItem.filter { !it.attribute.isList }" loopVariable="attributeWithItem" ]

                @replace-value-by-expression
                    [ searchValue="articulusInteriorSingularis" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

            }}}@  */

            [SilvaOptionumFormPartFieldName.articulusInteriorSingularis]: this.articulusInteriorFormPartService.createInitialArticulusInteriorForm(),
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */
            /* @tt{{{ @rlb
                @foreach [ iteratorExpression="model.item.attributesWithItem.filter { it.attribute.isList }" loopVariable="attributeWithItem" ]

                @replace-value-by-expression
                    [ searchValue="articulusInteriorList" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

            }}}@  */
            [SilvaOptionumFormPartFieldName.articulusInteriorList]: new FormArray(
                this.silvaOptionumFormInitialValueService.articulusInteriorListInitialValue(),
                {
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.articulusInteriorList)
                },
            ),
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */
            /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
            [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis]: this.articulusInteriorFormPartService.createInitialArticulusInteriorForm(),
            [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull]: new FormControl<boolean>(
                false,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull)
                },
            ),
            [SilvaOptionumFormPartFieldName.campusDieiIsNotNull]: new FormControl<boolean>(
                false,
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

            // ------------------------
            // All isNotNull controls
            // ------------------------

            /* @tt{{{ @rlb
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="FormControl<string | null>" replaceByExpression="attribute.angularFormControlType" ]

            }}}@  */
            /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: new FormControl<boolean>(
                false,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull)
                },
            ),
            /* @tt{{{ @rlb  @end-if @rla }}}@ */
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */
        });
    }

    public patchSilvaOptionumForm(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {
        this.patchPreparation(form, silvaOptionum);

        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
                [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]

            @rla
        }}}@  */
        /* @tt{{{ @rlb  @if [ conditionExpression="!attribute.isNullable"] @rla }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius].patchValue(silvaOptionum.campusTextusObligatorius);
        /* @tt{{{ @rlb  @end-if @rla }}}@ */
        /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
        if(silvaOptionum.campusTextusOptionalis != null) {
            form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].patchValue(true);
            form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis].patchValue(silvaOptionum.campusTextusOptionalis);
        } else {
            form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].patchValue(false);
        }
        /* @tt{{{ @rlb  @end-if @rla }}}@ */
        /* @tt{{{ @rlb @end-foreach @rla }}}@ */
        /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].patchValue(silvaOptionum.articulusInteriorList)
        form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis].patchValue(silvaOptionum.articulusInteriorSingularis);
        if(silvaOptionum.articulusInteriorSingularisOptionalis != null) {
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull].patchValue(true);
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis].patchValue(silvaOptionum.articulusInteriorSingularisOptionalis);
        } else {
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull].patchValue(false);
        }
        form.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull].patchValue(!silvaOptionum.campusDiei);
        form.controls[SilvaOptionumFormPartFieldName.campusDiei].patchValue(silvaOptionum.campusDiei ?? null);
        form.controls[SilvaOptionumFormPartFieldName.campusBivalens].patchValue(silvaOptionum.campusBivalens);
        form.controls[SilvaOptionumFormPartFieldName.appellatio].patchValue(silvaOptionum.appellatio);
        form.controls[SilvaOptionumFormPartFieldName.campusNumerorum].patchValue(silvaOptionum.campusNumerorum);
        form.controls[SilvaOptionumFormPartFieldName.indexUnicus].patchValue(silvaOptionum.indexUnicus);
        /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

        this.patchNestedItems(form, silvaOptionum);
    }

    /**
     * patchValue does not create missing FormGroups inside the FormArray.
     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
     * We need to prefill the FormArray with empty values first
     */
    private patchPreparation(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributesWithItem.filter { it.attribute.isList }" loopVariable="attributeWithItem" ]
            @replace-value-by-expression
                    [ searchValue="articulusInteriorList" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                    [ searchValue="ArticulusInteriorList" replaceByExpression="attributeWithItem.attribute.attributeName.pascalCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

            }}}@  */

        const articulusInteriorListLength = form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].controls.length
        if(articulusInteriorListLength < silvaOptionum.articulusInteriorList.length) {
            for (let i = articulusInteriorListLength; i < silvaOptionum.articulusInteriorList.length; i++) {
                form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].push(this.articulusInteriorFormPartService.createInitialArticulusInteriorForm())
            }
        }
        /* @tt{{{ @end-foreach }}}@ */
    }


    private patchNestedItems(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {
        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributesWithItem.filter { it.attribute.isList }" loopVariable="attributeWithItem" ]
            @replace-value-by-expression
                    [ searchValue="articulusInteriorList" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                    [ searchValue="ArticulusInteriorList" replaceByExpression="attributeWithItem.attribute.attributeName.pascalCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

            }}}@  */

        const articulusInteriorListLength = form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].controls.length
        if(articulusInteriorListLength < silvaOptionum.articulusInteriorList.length) {
            for (let i = articulusInteriorListLength; i < silvaOptionum.articulusInteriorList.length; i++) {
                this.articulusInteriorFormPartService.patchArticulusInteriorForm(
                    form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].at(i),
                    silvaOptionum.articulusInteriorList[i]
                )
            }
        }
        /* @tt{{{ @end-foreach }}}@ */

        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributesWithItem.filter { !it.attribute.isList }" loopVariable="attributeWithItem" ]
            @replace-value-by-expression
                    [ searchValue="articulusInteriorSingularisOptionalis" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInteriorSingularis" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

            }}}@  */
        /* @tt{{{ @if [conditionExpression="attributeWithItem.attribute.isNullable"] }}}@ */
        if(silvaOptionum.articulusInteriorSingularisOptionalis != null) {
            this.articulusInteriorFormPartService.patchArticulusInteriorForm(form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis], silvaOptionum.articulusInteriorSingularisOptionalis)
        }
        /* @tt{{{ @else }}}@ */
        this.articulusInteriorFormPartService.patchArticulusInteriorForm(form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis], silvaOptionum.articulusInteriorSingularis)
        /* @tt{{{ @end-if }}}@ */
        /* @tt{{{ @end-foreach }}}@ */
        /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
        /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
    }

    public createSilvaOptionumWTOFromForm(form: FormGroup<SilvaOptionumFormPartGroup>): SilvaOptionumWTO {
        return {
            /* @tt{{{
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

                @rla
            }}}@  */
            /* @tt{{{ @rlb  @if [ conditionExpression="!attribute.isNullable"] @rla }}}@ */
            campusTextusObligatorius: form.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius].getRawValue(),
            /* @tt{{{ @rlb  @else @rla }}}@ */
            campusTextusOptionalis: form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].value
                ? form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis].getRawValue()
                : null,
            /* @tt{{{ @rlb  @end-if @rla }}}@ */
            /* @tt{{{ @rlb @end-foreach @rla }}}@ */
            /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
            articulusInteriorSingularis: this.articulusInteriorFormPartService.createArticulusInteriorWTOFromForm(form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis]),
            articulusInteriorList: form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].controls.map(
                (controlEntry) => this.articulusInteriorFormPartService.createArticulusInteriorWTOFromForm(controlEntry)
            ),
            articulusInteriorSingularisOptionalis: form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis].value
                ? this.articulusInteriorFormPartService.createArticulusInteriorWTOFromForm(form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis])
                : null,
            campusDiei: form.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull].value ? form.controls[SilvaOptionumFormPartFieldName.campusDiei].getRawValue() : null,
            campusBivalens: form.controls[SilvaOptionumFormPartFieldName.campusBivalens].getRawValue(),
            campusNumerorum: form.controls[SilvaOptionumFormPartFieldName.campusNumerorum].getRawValue(),
            appellatio: form.controls[SilvaOptionumFormPartFieldName.appellatio].getRawValue(),
            indexUnicus: form.controls[SilvaOptionumFormPartFieldName.indexUnicus].getRawValue(),
            /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
        };
    }
}
