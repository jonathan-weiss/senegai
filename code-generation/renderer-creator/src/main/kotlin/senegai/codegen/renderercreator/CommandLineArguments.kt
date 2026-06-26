package senegai.codegen.renderercreator

import senegai.codegen.renderercreator.CommandLineArgument.ANGULAR
import senegai.codegen.renderercreator.CommandLineArgument.EXAMPLE_DATA
import senegai.codegen.renderercreator.CommandLineArgument.PERSISTENCE
import senegai.codegen.renderercreator.CommandLineArgument.REST
import senegai.codegen.renderercreator.CommandLineArgument.SERVICE
import senegai.codegen.renderercreator.CommandLineArgument.TARGET_TEMPLATE_RENDERERS
import java.nio.file.Path
import java.nio.file.Paths

enum class CommandLineArgument(val description: String) {
    TARGET_TEMPLATE_RENDERERS("Path to the generated template renderers"),
    ANGULAR("Path to frontend angular sources"),
    SERVICE("Path to backend service sources"),
    REST("Path to backend rest sources"),
    PERSISTENCE("Path to backend persistence sources"),
    EXAMPLE_DATA("Path to backend example data sources"),
    ;
}

data class CommandLineArguments(
    private val arguments: Map<CommandLineArgument, String>,
) {
    val generatedTemplateRenderersBaseDir: Path = Paths.get(requireNotNull(arguments[TARGET_TEMPLATE_RENDERERS]))
    val directoryForAngularSource: Path = Paths.get(requireNotNull(arguments[ANGULAR]))
    val directoryForRestSource: Path = Paths.get(requireNotNull(arguments[REST]))
    val directoryForServiceSource: Path = Paths.get(requireNotNull(arguments[SERVICE]))
    val directoryForPersistenceSource: Path = Paths.get(requireNotNull(arguments[PERSISTENCE]))
    val directoryForExampleDataSource: Path = Paths.get(requireNotNull(arguments[EXAMPLE_DATA]))
}

fun createCommandLineArguments(args: Array<String>): CommandLineArguments? {
    if (args.size != CommandLineArgument.entries.size) {
        val commandLineArgumentDescription = CommandLineArgument.entries.joinToString(separator = " ", prefix = "<", postfix = ">") { it.description }
        println("Wrong number of arguments! Expected arguments: $commandLineArgumentDescription")
        return null
    }
    return CommandLineArguments(args
        .mapIndexed { index, argument -> Pair(CommandLineArgument.entries[index], argument) }
        .toMap())

}
