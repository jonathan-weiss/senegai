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
        [ searchValue="UUID" replaceByExpression="model.primaryKeyAttribute.kotlinAttributeType" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.service.silvaoptionum

import org.springframework.stereotype.Service
import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.bo.SilvaOptionumByIdsCriteriaBO
import senegai.server.service.bo.SilvaOptionumSearchCriteriaBO
/* @tt{{{   @if [ conditionExpression="model.hasUuidPrimaryKey"]  }}}@ */
import java.util.UUID
/* @tt{{{   @end-if  }}}@ */

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
        /* @tt{{{   @if [ conditionExpression="model.hasGeneratedPrimaryKey" ]  }}}@ */
        val toCreate = silvaOptionum.copy(indexUnicus = silvaOptionumRepository.nextId())
        return silvaOptionumRepository.save(toCreate)
        /* @tt{{{
            @else
            @replace-value-by-expression
                [ searchValue="UUID(0L, 0L)" replaceByExpression="model.unresolvablePrimaryKeyValueExpression" ]
        }}}@ */
        require(silvaOptionum.indexUnicus != UUID(0L, 0L)) {
            "The primary key of SilvaOptionum is supplied by the caller and must not be empty."
        }
        return silvaOptionumRepository.save(silvaOptionum)
        /* @tt{{{   @end-replace-value-by-expression @end-if  }}}@ */
    }

    fun updateSilvaOptionum(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO =
        silvaOptionumRepository.save(silvaOptionum)

    fun deleteSilvaOptionum(indexUnicus: UUID) = silvaOptionumRepository.deleteById(indexUnicus)
}
