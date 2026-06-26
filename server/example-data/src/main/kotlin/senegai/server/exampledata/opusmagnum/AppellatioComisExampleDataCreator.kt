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
        [ searchValue="VIR_HONORATUS" replaceByExpression="model.enumValues.first().screamingSnakeCase" ]
        [ searchValue="opusmagnum" replaceByExpression="model.entityName.lowerCase" ]

    @modify-provided-filename-by-replacements

}}}@ */
package senegai.server.exampledata.opusmagnum

import org.springframework.stereotype.Component
import senegai.server.service.opusmagnum.bo.AppellatioComis

/**
 * Creates example data for the [AppellatioComis] business enum.
 */
@Component
class AppellatioComisExampleDataCreator {

    /** A single representative example value. */
    fun create(): AppellatioComis = AppellatioComis.VIR_HONORATUS

    /** All enum values as example data. */
    fun createList(): List<AppellatioComis> = AppellatioComis.entries.toList()
}
