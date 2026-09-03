/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EnumExampleDataCreatorRenderer"
        templateRendererPackageName="senegai.codegen.renderer.be"
        templateRendererInterfaceName="BeEnumRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.be"
    ] [
        modelClassName="BeEnumModel"
        modelPackageName="senegai.codegen.renderer.model.be"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="AppellatioComis" replaceByExpression="model.enumName.pascalCase" ]
        [ searchValue="silvaoptionum" replaceByExpression="model.itemName.lowerCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.exampledata.bo

import org.springframework.stereotype.Component
import senegai.server.exampledata.DataContext
import senegai.server.exampledata.framework.datafaker.FakerHelper
import senegai.server.exampledata.framework.datagenerator.RandomEnumValueDataGenerator
import senegai.server.service.bo.AppellatioComis

/**
 * Creates example data for the [AppellatioComis] business enum.
 */
@Component
class AppellatioComisExampleDataCreator(
    private val randomEnumValueDataGenerator: RandomEnumValueDataGenerator,
) {

    fun create(dataContext: DataContext): AppellatioComis =
        randomEnumValueDataGenerator.generateData(
            dataContext = dataContext,
            enumClass = AppellatioComis::class,
        )

    fun createList(dataContext: DataContext, size: Int): List<AppellatioComis> =
        randomEnumValueDataGenerator.generateDataList(
            dataContext = dataContext,
            enumClass = AppellatioComis::class,
            size = size,
        )
}
