/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityExampleDataFetcherRenderer"
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
        [ searchValue="opusMagnum" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="opusmagnum" replaceByExpression="model.entityName.lowerCase" ]

    @replace-value-by-expression
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.exampledata.opusmagnum

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper
import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.opusmagnum.OpusMagnumRepository
import java.util.UUID

/**
 * Fetches already persisted OpusMagnum example data so that other example data creators can
 * reference it.
 *
 * Unlike the example data creators, this does not create anything: it reads the
 * [SilvaOptionumBO] instances that the [OpusMagnumExampleDataCreator] has written before,
 * so that a reference is always a valid one. It therefore only returns something once
 * OpusMagnum example data has been created.
 */
@Component
class OpusMagnumExampleDataFetcher(
    private val opusMagnumRepository: OpusMagnumRepository,
) {

    /**
     * A random subset of the indexUnicus of the existing [SilvaOptionumBO] instances, or an
     * empty list if none exist yet.
     */
    fun fetchRandomKeysList(dataContext: DataContext): List<UUID> =
        FakerHelper.manyOfRandom(
            dataContext = dataContext,
            array = opusMagnumRepository.findAll().toTypedArray(),
            size = FakerHelper.referenceListRandomSize(dataContext),
        ).map { it.indexUnicus }

    /**
     * The indexUnicus of one random existing [SilvaOptionumBO].
     *
     * A mandatory reference needs a value even where nothing can be referenced yet, for example
     * where an entity references itself or where the referenced entity has not created its
     * example data yet. Such a reference gets [UNRESOLVABLE_KEY], which resolves to nothing and
     * is therefore shown with the fallback of the display attributes.
     */
    fun fetchRandomKey(dataContext: DataContext): UUID {
        val silvaOptionumList = opusMagnumRepository.findAll()
        if (silvaOptionumList.isEmpty()) {
            return UNRESOLVABLE_KEY
        }
        return FakerHelper.oneRandomOf(
            dataContext = dataContext,
            array = silvaOptionumList.toTypedArray(),
        ).indexUnicus
    }

    private companion object {
        /** The nil UUID, which no entity is ever identified by. */
        private val UNRESOLVABLE_KEY: UUID = UUID(0L, 0L)
    }
}
