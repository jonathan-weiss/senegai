/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemByIdsCriteriaMapperRenderer"
        templateRendererPackageName="senegai.codegen.renderer.be"
        templateRendererInterfaceName="BeItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.be"
    ] [
        modelClassName="BeItemModel"
        modelPackageName="senegai.codegen.renderer.model.be"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="SilvaOptionum" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="indexUnicus" replaceByExpression="model.primaryKeyAttribute.attributeName.camelCase" ]

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
