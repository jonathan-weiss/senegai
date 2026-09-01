/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityByIdsCriteriaMapperRenderer"
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
package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.SilvaOptionumByIdsCriteriaWTO
import senegai.server.service.bo.SilvaOptionumByIdsCriteriaBO

object SilvaOptionumByIdsCriteriaMapper {

    fun SilvaOptionumByIdsCriteriaWTO.toBo(): SilvaOptionumByIdsCriteriaBO = SilvaOptionumByIdsCriteriaBO(
        indexUnicusList = indexUnicusList,
    )
}
