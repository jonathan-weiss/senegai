/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntitySearchResultWTORenderer"
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
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.restapi.wto

data class SilvaOptionumSearchResultWTO(
    val silvaOptionumList: List<SilvaOptionumWTO>,
)
