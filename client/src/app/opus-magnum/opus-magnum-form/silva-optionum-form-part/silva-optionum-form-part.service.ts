/* @tt{{{
    

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

/* @tt{{{   @ignore-text  }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{   @end-ignore-text  }}}@ */

/* @tt{{{ 
    @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="nestedItem" ]

    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]
        [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]

}}}@  */
import {
    ArticulusInteriorFormPartService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part.service";
/* @tt{{{   @end-foreach  }}}@ */

@Injectable({providedIn: 'root'})
export class SilvaOptionumFormPartService {

    constructor(
        private silvaOptionumFormValidationService: SilvaOptionumFormPartValidationService,
        private silvaOptionumFormInitialValueService: SilvaOptionumFormPartInitialValueService,
        /* @tt{{{ 
            @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */
        private articulusInteriorFormPartService: ArticulusInteriorFormPartService,
        /* @tt{{{   @end-foreach  }}}@ */
    ) {}

    public createInitialSilvaOptionumForm(): FormGroup<SilvaOptionumFormPartGroup> {
        return new FormGroup({
            /* @tt{{{   @ignore-text  }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusObligatorius]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.campusTextusObligatoriusInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusObligatorius)
                },
            ),
            /* @tt{{{   @end-ignore-text  }}}@ */
            /* @tt{{{ 
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="FormControl<string>" replaceByExpression="attribute.angularFormControlType" ]


            }}}@  */
            /* @tt{{{   @if [ conditionExpression="!attribute.isItem"]  }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: new FormControl<string>(
                this.silvaOptionumFormInitialValueService.campusTextusOptionalisInitialValue(),
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalis)
                },
            ),
            /* @tt{{{   @end-if  }}}@ */
            /* @tt{{{  @end-foreach  }}}@ */

            /* @tt{{{ 
                @foreach [ iteratorExpression="model.item.attributesWithItem.filter { !it.attribute.isList }" loopVariable="attributeWithItem" ]

                @replace-value-by-expression
                    [ searchValue="articulusInteriorSingularis" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

            }}}@  */

            [SilvaOptionumFormPartFieldName.articulusInteriorSingularis]: this.articulusInteriorFormPartService.createInitialArticulusInteriorForm(),
            /* @tt{{{  @end-foreach  }}}@ */
            /* @tt{{{ 
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
            /* @tt{{{  @end-foreach  }}}@ */
            /* @tt{{{   @ignore-text  }}}@ */
            [SilvaOptionumFormPartFieldName.articulusInteriorOptionalisList]: new FormArray(
                this.silvaOptionumFormInitialValueService.articulusInteriorOptionalisListInitialValue(),
                {
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.articulusInteriorOptionalisList)
                },
            ),
            [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis]: this.articulusInteriorFormPartService.createInitialArticulusInteriorForm(),
            [SilvaOptionumFormPartFieldName.campusDiei]: new FormControl<Date>(
                this.silvaOptionumFormInitialValueService.campusDieiInitialValue(),
                {
                    nonNullable: true,
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
            [SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum]: new FormArray<FormControl<string>>(
                [] as Array<FormControl<string>>,
                {
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum)
                },
            ),
            /* @tt{{{   @end-ignore-text  }}}@ */

            // ------------------------
            // All isNotNull controls
            // ------------------------

            /* @tt{{{ 
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="FormControl<string | null>" replaceByExpression="attribute.angularFormControlType" ]

            }}}@  */
            /* @tt{{{   @if [ conditionExpression="attribute.isNullable"]  }}}@ */
            [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: new FormControl<boolean>(
                false,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull)
                },
            ),
            /* @tt{{{   @end-if  }}}@ */
            /* @tt{{{  @end-foreach  }}}@ */
            /* @tt{{{   @ignore-text  }}}@ */
            [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull]: new FormControl<boolean>(
                false,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull)
                },
            ),
            [SilvaOptionumFormPartFieldName.articulusInteriorOptionalisListIsNotNull]: new FormControl<boolean>(
                false,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.articulusInteriorOptionalisListIsNotNull)
                },
            ),
            [SilvaOptionumFormPartFieldName.campusDieiIsNotNull]: new FormControl<boolean>(
                false,
                {
                    nonNullable: true,
                    validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.campusDieiIsNotNull)
                },
            ),
            /* @tt{{{   @end-ignore-text  }}}@ */

        });
    }

    /* @tt{{{   @ignore-text  }}}@ */
    public createInitialIteratioSimpliciumTextuumForm(): FormControl<string> {
        return new FormControl<string>(
            this.silvaOptionumFormInitialValueService.iteratioSimpliciumTextuumInitialValue(),
            {
                nonNullable: true,
                validators: this.silvaOptionumFormValidationService.validatorFunctions(SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum)
            },
        )
    }
    /* @tt{{{   @end-ignore-text  }}}@ */

    public patchSilvaOptionumForm(form: FormGroup<SilvaOptionumFormPartGroup>, silvaOptionum: SilvaOptionumWTO): void {
        this.patchPreparation(form, silvaOptionum);

        /* @tt{{{
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
                [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]

            
        }}}@  */
        /* @tt{{{   @if [ conditionExpression="!attribute.isNullable"]  }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius].patchValue(silvaOptionum.campusTextusObligatorius);
        /* @tt{{{   @end-if  }}}@ */
        /* @tt{{{   @if [ conditionExpression="attribute.isNullable"]  }}}@ */
        if(silvaOptionum.campusTextusOptionalis != null) {
            form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].patchValue(true);
            form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis].patchValue(silvaOptionum.campusTextusOptionalis);
        } else {
            form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].patchValue(false);
        }
        /* @tt{{{   @end-if  }}}@ */
        /* @tt{{{  @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].patchValue(silvaOptionum.articulusInteriorList)
        if(silvaOptionum.articulusInteriorOptionalisList != null) {
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisListIsNotNull].patchValue(true);
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisList].patchValue(silvaOptionum.articulusInteriorOptionalisList);
        } else {
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisListIsNotNull].patchValue(false);
        }
        form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis].patchValue(silvaOptionum.articulusInteriorSingularis);
        if(silvaOptionum.articulusInteriorSingularisOptionalis != null) {
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull].patchValue(true);
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis].patchValue(silvaOptionum.articulusInteriorSingularisOptionalis);
        } else {
            form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull].patchValue(false);
        }
        if(silvaOptionum.campusDiei != null) {
            form.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull].patchValue(true);
            form.controls[SilvaOptionumFormPartFieldName.campusDiei].patchValue(silvaOptionum.campusDiei);
        } else {
            form.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull].patchValue(false);
        }
        form.controls[SilvaOptionumFormPartFieldName.campusBivalens].patchValue(silvaOptionum.campusBivalens);
        form.controls[SilvaOptionumFormPartFieldName.appellatio].patchValue(silvaOptionum.appellatio);
        form.controls[SilvaOptionumFormPartFieldName.campusNumerorum].patchValue(silvaOptionum.campusNumerorum);
        form.controls[SilvaOptionumFormPartFieldName.indexUnicus].patchValue(silvaOptionum.indexUnicus);
        form.controls[SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum].patchValue(silvaOptionum.iteratioSimpliciumTextuum);
        /* @tt{{{   @end-ignore-text  }}}@ */

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
        if(silvaOptionum.articulusInteriorList != null) {
            const articulusInteriorListLength = form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].controls.length
            if (articulusInteriorListLength < silvaOptionum.articulusInteriorList.length) {
                for (let i = articulusInteriorListLength; i < silvaOptionum.articulusInteriorList.length; i++) {
                    form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].push(this.articulusInteriorFormPartService.createInitialArticulusInteriorForm())
                }
            }
        }
        /* @tt{{{ @end-foreach }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        if(silvaOptionum.articulusInteriorOptionalisList != null) {
            const articulusInteriorListOptionalisLength = form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisList].controls.length
            if (articulusInteriorListOptionalisLength < silvaOptionum.articulusInteriorOptionalisList.length) {
                for (let i = articulusInteriorListOptionalisLength; i < silvaOptionum.articulusInteriorOptionalisList.length; i++) {
                    form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisList].push(this.articulusInteriorFormPartService.createInitialArticulusInteriorForm())
                }
            }
        }
        const iteratioSimpliciumTextuumLength = form.controls[SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum].controls.length
        if (iteratioSimpliciumTextuumLength < silvaOptionum.iteratioSimpliciumTextuum.length) {
            for (let i = iteratioSimpliciumTextuumLength; i < silvaOptionum.iteratioSimpliciumTextuum.length; i++) {
                form.controls[SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum].push(this.createInitialIteratioSimpliciumTextuumForm())
            }
        }
        /* @tt{{{   @end-ignore-text  }}}@ */
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

        if(silvaOptionum.articulusInteriorList != null) {
            for (let i = 0; i < silvaOptionum.articulusInteriorList.length; i++) {
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
        /* @tt{{{   @ignore-text  }}}@ */
        if(silvaOptionum.articulusInteriorOptionalisList != null) {
            for (let i = 0; i < silvaOptionum.articulusInteriorOptionalisList.length; i++) {
                this.articulusInteriorFormPartService.patchArticulusInteriorForm(
                    form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisList].at(i),
                    silvaOptionum.articulusInteriorOptionalisList[i]
                )
            }
        }
        /* @tt{{{   @end-ignore-text  }}}@ */
    }

    public createSilvaOptionumWTOFromForm(form: FormGroup<SilvaOptionumFormPartGroup>): SilvaOptionumWTO {
        return {
            /* @tt{{{
                @foreach [ iteratorExpression="model.item.attributesWithItem" loopVariable="attributeWithItem" ]
                @replace-value-by-expression
                        [ searchValue="articulusInteriorSingularisOptionalis" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                        [ searchValue="articulusInteriorSingularis" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                        [ searchValue="articulusInteriorOptionalisList" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                        [ searchValue="articulusInteriorList" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
                        [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                        [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

                }}}@  */
            /* @tt{{{   @if [ conditionExpression="attributeWithItem.attribute.isList"]  }}}@ */
            /* @tt{{{   @if [ conditionExpression="attributeWithItem.attribute.isNullable"]  }}}@ */
            articulusInteriorOptionalisList: form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisListIsNotNull].value
                ? form.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisList].controls.map(
                    (controlEntry) => this.articulusInteriorFormPartService.createArticulusInteriorWTOFromForm(controlEntry))
                : null,
            /* @tt{{{   @else  }}}@ */
            articulusInteriorList: form.controls[SilvaOptionumFormPartFieldName.articulusInteriorList].controls.map(
                (controlEntry) => this.articulusInteriorFormPartService.createArticulusInteriorWTOFromForm(controlEntry)
            ),
            /* @tt{{{   @end-if  }}}@ */
            /* @tt{{{   @else  }}}@ */
            /* @tt{{{   @if [ conditionExpression="attributeWithItem.attribute.isNullable"]  }}}@ */
            articulusInteriorSingularisOptionalis: form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull].value
                ? this.articulusInteriorFormPartService.createArticulusInteriorWTOFromForm(form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis])
                : null,
            /* @tt{{{   @else  }}}@ */
            articulusInteriorSingularis: this.articulusInteriorFormPartService.createArticulusInteriorWTOFromForm(form.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis]),
            /* @tt{{{   @end-if  }}}@ */
            /* @tt{{{   @end-if  }}}@ */

            /* @tt{{{  @end-foreach  }}}@ */

            /* @tt{{{
                @foreach [ iteratorExpression="model.item.attributesWithBuiltInType" loopVariable="attributeWithBuiltInType" ]
            }}}@  */
            /* @tt{{{
                @replace-value-by-expression
                    [ searchValue="campusTextusObligatorius" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.camelCase" ]
                    [ searchValue="campusTextusOptionalis" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInteriorOptionalisList" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInteriorList" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInteriorSingularisOptionalis" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInteriorSingularis" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.camelCase" ]
                    [ searchValue="articulusInterior" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.camelCase" ]
                    [ searchValue="ArticulusInterior" replaceByExpression="attributeWithBuiltInType.attribute.attributeName.pascalCase" ]
                
            }}}@  */
            /* @tt{{{   @if [ conditionExpression="attributeWithBuiltInType.attribute.isList"]  }}}@ */
                /* @tt{{{   @if [ conditionExpression="attributeWithBuiltInType.attribute.isNullable"]  }}}@ */
                // TODO list of non-item for nullable values no implemented, yet
                /* @tt{{{   @else  }}}@ */
                // TODO list of non-item for non-nullable values no implemented, yet
                /* @tt{{{   @end-if  }}}@ */
            /* @tt{{{   @else  }}}@ */
                /* @tt{{{   @if [ conditionExpression="attributeWithBuiltInType.attribute.isNullable"]  }}}@ */
                campusTextusOptionalis: form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull].value
                    ? form.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis].getRawValue()
                    : null,
                /* @tt{{{   @else  }}}@ */
                campusTextusObligatorius: form.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius].getRawValue(),
                /* @tt{{{   @end-if  }}}@ */
            /* @tt{{{   @end-if  }}}@ */
            /* @tt{{{  @end-foreach  }}}@ */
            /* @tt{{{   @ignore-text  }}}@ */
            campusDiei: form.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull].value ? form.controls[SilvaOptionumFormPartFieldName.campusDiei].getRawValue() : null,
            campusBivalens: form.controls[SilvaOptionumFormPartFieldName.campusBivalens].getRawValue(),
            campusNumerorum: form.controls[SilvaOptionumFormPartFieldName.campusNumerorum].getRawValue(),
            appellatio: form.controls[SilvaOptionumFormPartFieldName.appellatio].getRawValue(),
            indexUnicus: form.controls[SilvaOptionumFormPartFieldName.indexUnicus].getRawValue(),
            iteratioSimpliciumTextuum: form.controls[SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum].getRawValue(),
            /* @tt{{{   @end-ignore-text  }}}@ */
        };
    }
}
