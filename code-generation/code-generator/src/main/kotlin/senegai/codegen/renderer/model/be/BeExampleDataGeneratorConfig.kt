package senegai.codegen.renderer.model.be

import java.util.Locale.getDefault

data class BeExampleDataGeneratorConfig(
    val generatorNamePrefix: String,
    val isNullable: Boolean,
    val isMultiple: Boolean
) {
    val exampleDataGeneratorVariableName: String = "${decapitalize(generatorNamePrefix)}DataGenerator"

    val exampleDataGeneratorClassName: String = "${capitalize(generatorNamePrefix)}DataGenerator"

    val exampleDataGeneratorPackageName: String = "senegai.server.exampledata.framework.datagenerator"

    val fullQualifiedName: String = "${exampleDataGeneratorPackageName}.${exampleDataGeneratorClassName}"

    val exampleDataGeneratorCallExpression: String = if(isMultiple) {
        "${exampleDataGeneratorVariableName}.generateDataList()"
    } else {
        "${exampleDataGeneratorVariableName}.generateData()"
    }

    private fun decapitalize(value: String): String =
        value.replaceFirstChar { it.lowercase(getDefault()) }
    private fun capitalize(value: String): String =
        value.replaceFirstChar { if (it.isLowerCase()) it.titlecase(getDefault()) else it.toString() }
}
