/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemExampleDataPopulatorRenderer"
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
        [ searchValue="indexUnicus" replaceByExpression="model.primaryKeyAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.exampledata.silvaoptionum

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.ExampleDataCreator
import senegai.server.exampledata.bo.SilvaOptionumExampleDataCreator
import senegai.server.exampledata.framework.datafaker.FakerHelper
import senegai.server.service.bo.SilvaOptionumBO
import senegai.server.service.silvaoptionum.SilvaOptionumRepository

/**
 * Orchestrates the creation of SilvaOptionum example data.
 *
 * Builds a list of [SilvaOptionumBO] aggregates by delegating to the per-business-object
 * example data creators and persists the result through the [SilvaOptionumRepository] port.
 */
@Component
class SilvaOptionumExampleDataPopulator(
    private val silvaOptionumExampleDataCreator: SilvaOptionumExampleDataCreator,
    private val silvaOptionumRepository: SilvaOptionumRepository,
): ExampleDataCreator {

    /**
     * Creates the example [SilvaOptionumBO] aggregates and writes each of them to the
     * persistence via the [SilvaOptionumRepository].
     *
     * The primary key of the created aggregates is the one the persistence hands out, not the
     * one the example data creator generated: only that way every example aggregate is stored
     * under a key of its own, whatever type the primary key is of.
     */
    override fun createExampleData(dataContext: DataContext) {
        silvaOptionumExampleDataCreator.createList(dataContext, FakerHelper.itemListRandomSize(dataContext))
            .forEach { silvaOptionumRepository.save(it.copy(indexUnicus = silvaOptionumRepository.nextId())) }
    }
}
