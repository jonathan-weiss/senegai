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
        [ searchValue="silva-optionum" replaceByExpression="model.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {ArticulusInteriorWTO} from "@app/wto/articulus-interior.wto";
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

/**
 * The Silva Optionum WTO (Web Transfer Object) class.
 */
export interface SilvaOptionumWTO {
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
    articulusInteriorList: Array<ArticulusInteriorWTO>;
    campusDiei: Date | null;
    campusBivalens: boolean;
    /* @tt{{{ @rlb  @end-ignore-text }}}@ */
}
