/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityRepositoryRenderer"
        templateRendererPackageName="senegai.codegen.renderer.be"
        templateRendererInterfaceName="BeEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.be"
    ] [
        modelClassName="BeEntityModel"
        modelPackageName="senegai.codegen.renderer.model.be"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="opusmagnum" replaceByExpression="model.entityName.lowerCase" ]

    @replace-value-by-expression
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.service.opusmagnum

import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.bo.SilvaOptionumByIdsCriteriaBO
import senegai.server.service.bo.SilvaOptionumSearchCriteriaBO
import java.util.UUID

/**
 * Port for persisting the OpusMagnum root object [SilvaOptionumBO]. The implementation
 * lives in the persistence module, so the service (business) layer stays independent of
 * any persistence technology.
 *
 * Always operates on the whole [SilvaOptionumBO] aggregate, never on nested items.
 */
interface OpusMagnumRepository {

    fun findAll(): List<SilvaOptionumBO>

    fun findById(indexUnicus: UUID): SilvaOptionumBO?

    fun findByIds(criteria: SilvaOptionumByIdsCriteriaBO): List<SilvaOptionumBO>

    fun search(searchCriteria: SilvaOptionumSearchCriteriaBO): List<SilvaOptionumBO>

    fun save(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO

    fun deleteById(indexUnicus: UUID)
}
