/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemSearchCriteriaWtoRenderer"
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

    @modify-provided-filepath-by-replacements

}}}@ */

/**
 * The Silva Optionum search criteria WTO (Web Transfer Object), sent as the request body
 * of the search endpoint. A blank query matches every Silva Optionum.
 */
export interface SilvaOptionumSearchCriteriaWTO {
    query: string;
}
