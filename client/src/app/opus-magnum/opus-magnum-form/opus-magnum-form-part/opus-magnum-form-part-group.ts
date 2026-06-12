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
        [ searchValue="opus-magnum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {OpusMagnumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";

/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */


export interface OpusMagnumFormPartGroup {
    /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="FormControl<string | null>" replaceByExpression="attribute.angularFormControlType" ]

    }}}@  */
    [OpusMagnumFormPartFieldName.campusTextusOptionalis]: FormControl<string | null>,
    /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
    [OpusMagnumFormPartFieldName.campusTextusOptionalisIsNotNull]: FormControl<boolean>,
    /* @tt{{{ @rlb  @end-if @rla }}}@ */
    /* @tt{{{ @rlb @end-foreach @rla }}}@ */
    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    [OpusMagnumFormPartFieldName.campusTextusObligatorius]: FormControl<string>,
    [OpusMagnumFormPartFieldName.articulusInteriorSingularis]: FormGroup<ArticulusInteriorFormPartGroup>,
    [OpusMagnumFormPartFieldName.articulusInteriorList]: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>,
    [OpusMagnumFormPartFieldName.campusDieiIsNotNull]: FormControl<boolean>,
    [OpusMagnumFormPartFieldName.campusDiei]: FormControl<Date | null>,
    [OpusMagnumFormPartFieldName.campusBivalens]: FormControl<boolean>,
    [OpusMagnumFormPartFieldName.appellatio]: FormControl<AppellatioEnum>,
    [OpusMagnumFormPartFieldName.campusNumerorum]: FormControl<number>,
    [OpusMagnumFormPartFieldName.indexUnicus]: FormControl<string>,
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
}
