/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemInMemoryRepositoryRenderer"
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
package senegai.server.persistence.silvaoptionum

import org.springframework.stereotype.Repository
import senegai.server.service.silvaoptionum.SilvaOptionumRepository
import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.bo.SilvaOptionumByIdsCriteriaBO
import senegai.server.service.bo.SilvaOptionumSearchCriteriaBO
import java.util.concurrent.ConcurrentHashMap
/* @tt{{{   @if [ conditionExpression="model.hasUuidPrimaryKey"]  }}}@ */
import java.util.UUID
/* @tt{{{   @end-if  }}}@ */

/**
 * Simple in-memory implementation of the [SilvaOptionumRepository] port defined in the
 * service module. Holds the [SilvaOptionumBO] aggregates in memory only; a real
 * persistence framework can replace this later without touching the service layer.
 */
@Repository
class InMemorySilvaOptionumRepository : SilvaOptionumRepository {

    private val store = ConcurrentHashMap<UUID, SilvaOptionumBO>()

    override fun findAll(): List<SilvaOptionumBO> = this.store.values.toList()

    override fun findById(indexUnicus: UUID): SilvaOptionumBO? = this.store[indexUnicus]

    override fun findByIds(criteria: SilvaOptionumByIdsCriteriaBO): List<SilvaOptionumBO> =
        criteria.indexUnicusList.mapNotNull { this.store[it] }

    override fun search(searchCriteria: SilvaOptionumSearchCriteriaBO): List<SilvaOptionumBO> =
        this.store.values.filter { it.toString().contains(searchCriteria.query, ignoreCase = true) }

    override fun save(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO {
        this.store[silvaOptionum.indexUnicus] = silvaOptionum
        return silvaOptionum
    }

    override fun deleteById(indexUnicus: UUID) {
        this.store.remove(indexUnicus)
    }

    /* @tt{{{
        @if [ conditionExpression="model.hasGeneratedPrimaryKey" ]
        @replace-value-by-expression
            [ searchValue="UUID.randomUUID()" replaceByExpression="model.nextPrimaryKeyValueExpression" ]
    }}}@ */
    override fun nextId(): UUID = UUID.randomUUID()
    /* @tt{{{   @end-replace-value-by-expression @end-if  }}}@ */
}
