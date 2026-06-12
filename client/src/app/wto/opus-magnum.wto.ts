/* @tt{{{
    @rlb

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemWTOInterfaceRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="opus-magnum" replaceByExpression="model.itemName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

/* @tt{{{
    @foreach [ iteratorExpression="model.attributeItemsFlat" loopVariable="nestedItem" ]

    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]
        [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]
}}}@  */
import {ArticulusInteriorWTO} from "@app/wto/articulus-interior.wto";
/* @tt{{{ @rlb  @end-foreach @rla }}}@ */
/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

/**
 * The Silva Optionum WTO (Web Transfer Object) class.
 */
export interface OpusMagnumWTO {
    /* @tt{{{
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string" replaceByExpression="attribute.typescriptAttributeType" ]

    }}}@  */
    campusTextusObligatorius: string;
    /* @tt{{{
        @rlb
        @end-foreach
        @rla
    }}}@ */
    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    campusTextusOptionalis: string | null;
    appellatio: AppellatioEnum;
    articulusInteriorSingularis: ArticulusInteriorWTO;
    articulusInteriorList: Array<ArticulusInteriorWTO>;
    campusDiei: Date | null;
    campusBivalens: boolean;
    indexUnicus: string;
    /* @tt{{{ @rlb  @end-ignore-text }}}@ */
}
