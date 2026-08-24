package senegai.codegen.renderer.model.be

data class BeExampleDataGeneratorConfig(
    val generatorNamePrefix: String,
    val isNullable: Boolean,
    val isMultiple: Boolean
) {
    val exampleDataGeneratorVariableName: String = "${generatorNamePrefix.decapitalize()}DataGenerator"

    val exampleDataGeneratorClassName: String = "${generatorNamePrefix.capitalize()}DataGenerator"

    val exampleDataGeneratorPackageName: String = "senegai.server.exampledata.framework.datagenerator"

    val fullQualifiedName: String = "${exampleDataGeneratorPackageName}.${exampleDataGeneratorClassName}"

    val exampleDataGeneratorCallExpression: String = if(isMultiple) {
        "${exampleDataGeneratorVariableName}.generateDataList()"
    } else {
        "${exampleDataGeneratorVariableName}.generateData()"
    }
}
