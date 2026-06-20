/* @tt{{{
    

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityItemFormPartGroupRenderer"
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

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {SilvaOptionumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-field-name";
/* @tt{{{   @ignore-text  }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{   @end-ignore-text  }}}@ */

/* @tt{{{ 
    @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="directlyNestedItem" ]

    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="directlyNestedItem.itemName.pascalCase" ]
        [ searchValue="articulus-interior" replaceByExpression="directlyNestedItem.itemName.kebabCase" ]

}}}@  */

import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
/* @tt{{{  @end-foreach  }}}@ */



export interface SilvaOptionumFormPartGroup {
    /* @tt{{{ 
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="FormControl<string>" replaceByExpression="attribute.angularFormControlTypeWithCollection" ]

    }}}@  */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: FormControl<string>,
    /* @tt{{{   @if [ conditionExpression="attribute.isNullable"] }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: FormControl<boolean>,
    /* @tt{{{   @end-if }}}@ */
    /* @tt{{{  @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusObligatorius]: FormControl<string>,
    [SilvaOptionumFormPartFieldName.articulusInteriorSingularis]: FormGroup<ArticulusInteriorFormPartGroup>,
    [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis]: FormGroup<ArticulusInteriorFormPartGroup>,
    [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.articulusInteriorIteratus]: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>,
    [SilvaOptionumFormPartFieldName.articulusInteriorOptionalisIteratus]: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>,
    [SilvaOptionumFormPartFieldName.articulusInteriorOptionalisIteratusIsNotNull]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.campusDieiIsNotNull]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.campusDiei]: FormControl<Date>,
    [SilvaOptionumFormPartFieldName.campusBivalens]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.appellatio]: FormControl<AppellatioEnum>,
    [SilvaOptionumFormPartFieldName.campusNumerorum]: FormControl<number>,
    [SilvaOptionumFormPartFieldName.indexUnicus]: FormControl<string>,
    [SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum]: FormArray<FormControl<string>>,
    /* @tt{{{   @end-ignore-text  }}}@ */
}
