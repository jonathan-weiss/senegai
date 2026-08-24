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
        [ searchValue="opusmagnum" replaceByExpression="model.entityName.lowerCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
package senegai.server.exampledata.bo

import org.springframework.stereotype.Component
import senegai.server.exampledata.datagenerator.RandomEnumValueDataGenerator
import senegai.server.service.bo.AppellatioComis

/**
 * Creates example data for the [AppellatioComis] business enum.
 */
@Component
class AppellatioComisExampleDataCreator(
    private val randomEnumValueDataGenerator: RandomEnumValueDataGenerator,
) {

    /** A single representative example value. */
    fun create(): AppellatioComis = randomEnumValueDataGenerator.generateData(AppellatioComis::class)

    /** All enum values as example data. */
    fun createList(): List<AppellatioComis> = randomEnumValueDataGenerator.generateDataList(AppellatioComis::class)
}
