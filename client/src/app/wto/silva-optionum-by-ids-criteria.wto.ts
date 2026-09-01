/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityByIdsCriteriaWtoRenderer"
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
        [ searchValue="silva-optionum" replaceByExpression="model.entityRootItem.itemName.kebabCase" ]
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
import {UUID} from "@app/shared/uuid";

/**
 * The Silva Optionum by-ids criteria WTO (Web Transfer Object), sent as the request body of
 * the by-ids endpoint. It carries the identifier of every Silva Optionum to resolve, which is
 * what a reference to this entity is stored as.
 */
export interface SilvaOptionumByIdsCriteriaWTO {
    indexUnicusList: Array<UUID>;
}
