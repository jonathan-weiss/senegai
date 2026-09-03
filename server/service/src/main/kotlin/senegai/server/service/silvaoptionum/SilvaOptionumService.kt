/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemServiceRenderer"
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
        [ searchValue="silvaOptionum" replaceByExpression="model.itemName.camelCase" ]
        [ searchValue="silvaoptionum" replaceByExpression="model.itemName.lowerCase" ]

    @replace-value-by-expression
        [ searchValue="indexUnicus" replaceByExpression="model.primaryKeyAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.service.silvaoptionum

import org.springframework.stereotype.Service
import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.bo.SilvaOptionumByIdsCriteriaBO
import senegai.server.service.bo.SilvaOptionumSearchCriteriaBO
import java.util.UUID

/**
 * Business service of the SilvaOptionum business context. It is called by the REST layer
 * with [SilvaOptionumBO] business objects (never WTOs) and delegates persistence to the
 * [SilvaOptionumRepository] port.
 *
 * Every operation works on the whole [SilvaOptionumBO] aggregate as a single unit.
 */
@Service
class SilvaOptionumService(
    private val silvaOptionumRepository: SilvaOptionumRepository,
) {

    fun getSilvaOptionumList(): List<SilvaOptionumBO> = silvaOptionumRepository.findAll()

    fun getSilvaOptionumById(indexUnicus: UUID): SilvaOptionumBO? =
        silvaOptionumRepository.findById(indexUnicus)

    fun getSilvaOptionumListByIds(criteria: SilvaOptionumByIdsCriteriaBO): List<SilvaOptionumBO> =
        silvaOptionumRepository.findByIds(criteria)

    fun searchSilvaOptionumList(searchCriteria: SilvaOptionumSearchCriteriaBO): List<SilvaOptionumBO> =
        silvaOptionumRepository.search(searchCriteria)

    fun createSilvaOptionum(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO {
        val toCreate = silvaOptionum.copy(indexUnicus = UUID.randomUUID())
        return silvaOptionumRepository.save(toCreate)
    }

    fun updateSilvaOptionum(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO =
        silvaOptionumRepository.save(silvaOptionum)

    fun deleteSilvaOptionum(indexUnicus: UUID) = silvaOptionumRepository.deleteById(indexUnicus)
}
