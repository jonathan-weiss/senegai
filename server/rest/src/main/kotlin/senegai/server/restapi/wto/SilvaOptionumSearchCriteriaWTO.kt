/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntitySearchCriteriaWTORenderer"
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
package senegai.server.restapi.wto

data class SilvaOptionumSearchCriteriaWTO(
    val query: String = "",
)
