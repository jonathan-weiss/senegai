/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemSearchCriteriaMapperRenderer"
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
