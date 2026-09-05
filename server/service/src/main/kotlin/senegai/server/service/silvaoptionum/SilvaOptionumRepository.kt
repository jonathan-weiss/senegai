/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemRepositoryRenderer"
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

import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.bo.SilvaOptionumByIdsCriteriaBO
import senegai.server.service.bo.SilvaOptionumSearchCriteriaBO
/* @tt{{{   @if [ conditionExpression="model.hasUuidPrimaryKey"]  }}}@ */
import java.util.UUID
/* @tt{{{   @end-if  }}}@ */

/**
 * Port for persisting the SilvaOptionum root object [SilvaOptionumBO]. The implementation
 * lives in the persistence module, so the service (business) layer stays independent of
 * any persistence technology.
 *
 * Always operates on the whole [SilvaOptionumBO] aggregate, never on nested items.
 */
interface SilvaOptionumRepository {

    fun findAll(): List<SilvaOptionumBO>

    fun findById(indexUnicus: UUID): SilvaOptionumBO?

    fun findByIds(criteria: SilvaOptionumByIdsCriteriaBO): List<SilvaOptionumBO>

    fun search(searchCriteria: SilvaOptionumSearchCriteriaBO): List<SilvaOptionumBO>

    fun save(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO

    fun deleteById(indexUnicus: UUID)

    /* @tt{{{   @if [ conditionExpression="model.hasGeneratedPrimaryKey" ]  }}}@ */
    /**
     * A primary key that no stored SilvaOptionum is identified by, for a SilvaOptionum that is
     * created. Handing out identity is the job of the persistence, like a sequence of a database.
     *
     * Only exists for an item whose key is handed out; a key of a textual type is supplied by
     * the caller instead.
     */
    fun nextId(): UUID
    /* @tt{{{   @end-if  }}}@ */
}
