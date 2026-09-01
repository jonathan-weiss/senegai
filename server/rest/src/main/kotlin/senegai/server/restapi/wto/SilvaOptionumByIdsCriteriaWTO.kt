/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityByIdsCriteriaWTORenderer"
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
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.restapi.wto

import java.util.UUID

data class SilvaOptionumByIdsCriteriaWTO(
    val indexUnicusList: List<UUID> = emptyList(),
)
