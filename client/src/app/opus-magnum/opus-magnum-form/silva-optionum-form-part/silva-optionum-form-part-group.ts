/* @tt{{{
    @rlb

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

    @rla

}}}@ */

import {FormArray, FormControl, FormGroup} from "@angular/forms";
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
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
/* @tt{{{ @rlb @end-foreach @rla }}}@ */



export interface SilvaOptionumFormPartGroup {
    /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="FormControl<string | null>" replaceByExpression="attribute.angularFormControlTypeWithCollection" ]

    }}}@  */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: FormControl<string | null>,
    /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: FormControl<boolean>,
    /* @tt{{{ @rlb  @end-if @rla }}}@ */
    /* @tt{{{ @rlb @end-foreach @rla }}}@ */
    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusObligatorius]: FormControl<string>,
    [SilvaOptionumFormPartFieldName.articulusInteriorSingularis]: FormGroup<ArticulusInteriorFormPartGroup>,
    [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis]: FormGroup<ArticulusInteriorFormPartGroup>,
    [SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.articulusInteriorList]: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>,
    [SilvaOptionumFormPartFieldName.campusDieiIsNotNull]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.campusDiei]: FormControl<Date | null>,
    [SilvaOptionumFormPartFieldName.campusBivalens]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.appellatio]: FormControl<AppellatioEnum>,
    [SilvaOptionumFormPartFieldName.campusNumerorum]: FormControl<number>,
    [SilvaOptionumFormPartFieldName.indexUnicus]: FormControl<string>,
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
}
