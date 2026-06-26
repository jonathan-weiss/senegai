/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityExampleDataCreatorRenderer"
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

    @modify-provided-filename-by-replacements

}}}@ */
package senegai.server.exampledata.opusmagnum

import org.springframework.stereotype.Component
import senegai.server.exampledata.bo.SilvaOptionumExampleDataCreator
import senegai.server.service.opusmagnum.OpusMagnumRepository
import senegai.server.service.bo.SilvaOptionumBO

/**
 * Orchestrates the creation of OpusMagnum example data.
 *
 * Builds a list of [SilvaOptionumBO] aggregates by delegating to the per-business-object
 * example data creators and persists the result through the [OpusMagnumRepository] port.
 */
@Component
class OpusMagnumExampleDataCreator(
    private val silvaOptionumExampleDataCreator: SilvaOptionumExampleDataCreator,
    private val opusMagnumRepository: OpusMagnumRepository,
) {

    /**
     * Creates the example [SilvaOptionumBO] aggregates, writes each of them to the
     * persistence via the [OpusMagnumRepository] and returns the persisted list.
     */
    fun createExampleData(): List<SilvaOptionumBO> =
        silvaOptionumExampleDataCreator.createList()
            .map { opusMagnumRepository.save(it) }
}
