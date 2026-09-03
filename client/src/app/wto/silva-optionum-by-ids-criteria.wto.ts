/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemByIdsCriteriaWtoRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="SilvaOptionum" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.itemName.kebabCase" ]
        [ searchValue="indexUnicus" replaceByExpression="model.primaryKeyAttribute.attributeName.camelCase" ]

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
