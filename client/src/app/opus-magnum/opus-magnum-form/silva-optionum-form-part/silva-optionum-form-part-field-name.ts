/* @tt{{{
    

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

    

}}}@ */

/* @tt{{{   @ignore-text  }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{   @end-ignore-text  }}}@ */

export enum SilvaOptionumFormPartFieldName {
    /* @tt{{{ 
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

    }}}@  */
    campusTextusOptionalis = "campusTextusOptionalis",
    /* @tt{{{   @if [ conditionExpression="attribute.isNullable"]  }}}@ */
    campusTextusOptionalisIsNotNull = "campusTextusOptionalisIsNotNull",
    /* @tt{{{   @end-if  }}}@ */
/* @tt{{{  @end-foreach  }}}@ */
/* @tt{{{   @ignore-text  }}}@ */
    campusTextusObligatorius = "campusTextusObligatorius",
    articulusInteriorSingularis = "articulusInteriorSingularis",
    articulusInteriorSingularisOptionalis = "articulusInteriorSingularisOptionalis",
    articulusInteriorSingularisOptionalisIsNotNull = "articulusInteriorSingularisOptionalisIsNotNull",
    articulusInteriorList = "articulusInteriorList",
    articulusInteriorOptionalisList = "articulusInteriorOptionalisList",
    articulusInteriorOptionalisListIsNotNull = "articulusInteriorOptionalisListIsNotNull",
    campusDieiIsNotNull = "campusDieiIsNotNull",
    campusDiei = "campusDiei",
    campusBivalens = "campusBivalens",
    appellatio = "appellatio",
    campusNumerorum = "campusNumerorum",
    indexUnicus = "indexUnicus",
    iteratioSimpliciumTextuum = "iteratioSimpliciumTextuum",
/* @tt{{{   @end-ignore-text  }}}@ */
}

