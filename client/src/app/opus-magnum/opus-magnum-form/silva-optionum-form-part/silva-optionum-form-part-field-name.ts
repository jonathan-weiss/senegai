/* @tt{{{
    @rlb

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityItemFormPartFieldNameRenderer"
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

/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

export enum SilvaOptionumFormPartFieldName {
    /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

    }}}@  */
    campusTextusOptionalis = "campusTextusOptionalis",
    /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
    campusTextusOptionalisIsNotNull = "campusTextusOptionalisIsNotNull",
    /* @tt{{{ @rlb  @end-if @rla }}}@ */
/* @tt{{{ @rlb @end-foreach @rla }}}@ */
/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    campusTextusObligatorius = "campusTextusObligatorius",
    articulusInteriorSingularis = "articulusInteriorSingularis",
    articulusInteriorList = "articulusInteriorList",
    campusDieiIsNotNull = "campusDieiIsNotNull",
    campusDiei = "campusDiei",
    campusBivalens = "campusBivalens",
    appellatio = "appellatio",
    campusNumerorum = "campusNumerorum",
    indexUnicus = "indexUnicus",
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
}

