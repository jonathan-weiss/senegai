/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityByIdsCriteriaBORenderer"
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
package senegai.server.service.bo

import java.util.UUID

/**
 * Business object for resolving a whole set of references to SilvaOptionum aggregates at once.
 *
 * Holds the identifiers a reference to this entity is stored as; unknown ones are simply not
 * part of the result.
 */
data class SilvaOptionumByIdsCriteriaBO(
    val indexUnicusList: List<UUID>,
)
