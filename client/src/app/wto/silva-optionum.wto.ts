/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

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

    @remove-blanks-and-linebreak-after-comment

}}}@ */

/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
import {ArticulusInteriorWTO} from "@app/wto/articulus-interior.wto";
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

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
        @remove-blanks-and-linebreak-before-comment
        @end-foreach
        @remove-blanks-and-linebreak-after-comment
    }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    campusTextusOptionalis: string | null;
    appellatio: AppellatioEnum;
    articulusInteriorList: Array<ArticulusInteriorWTO>;
    campusDiei: Date | null;
    campusBivalens: boolean;
    id: string;
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text }}}@ */
}
