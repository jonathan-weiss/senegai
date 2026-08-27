/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntitySearchCriteriaBORenderer"
        templateRendererPackageName="senegai.codegen.renderer.be"
        templateRendererInterfaceName="BeEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.be"
    ] [
        modelClassName="BeEntityModel"
        modelPackageName="senegai.codegen.renderer.model.be"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.service.bo

/**
 * Business object for the search criteria of the SilvaOptionum aggregates.
 *
 * Holds a single free text [query]; a blank query matches every [SilvaOptionumBO].
 */
data class SilvaOptionumSearchCriteriaBO(
    val query: String,
)
