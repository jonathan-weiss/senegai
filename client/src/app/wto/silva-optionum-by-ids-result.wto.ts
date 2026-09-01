/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityByIdsResultWtoRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.entityRootItem.itemName.kebabCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";

/**
 * The Silva Optionum by-ids result WTO (Web Transfer Object), returned by the by-ids endpoint.
 * It wraps the resolved Silva Optionums; unknown identifiers are omitted.
 */
export interface SilvaOptionumByIdsResultWTO {
    silvaOptionumList: Array<SilvaOptionumWTO>;
}
