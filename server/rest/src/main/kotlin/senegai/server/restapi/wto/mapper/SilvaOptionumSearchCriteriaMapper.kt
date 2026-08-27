/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntitySearchCriteriaMapperRenderer"
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
package senegai.server.restapi.wto.mapper

import senegai.server.restapi.wto.SilvaOptionumSearchCriteriaWTO
import senegai.server.service.bo.SilvaOptionumSearchCriteriaBO

object SilvaOptionumSearchCriteriaMapper {

    fun SilvaOptionumSearchCriteriaWTO.toBo(): SilvaOptionumSearchCriteriaBO = SilvaOptionumSearchCriteriaBO(
        query = query,
    )
}
