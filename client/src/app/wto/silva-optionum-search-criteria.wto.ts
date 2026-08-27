/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntitySearchCriteriaWtoRenderer"
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

    @modify-provided-filepath-by-replacements

}}}@ */

/**
 * The Silva Optionum search criteria WTO (Web Transfer Object), sent as the request body
 * of the search endpoint. A blank query matches every Silva Optionum.
 */
export interface SilvaOptionumSearchCriteriaWTO {
    query: string;
}
