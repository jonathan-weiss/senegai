package senegai.codegen

import senegai.codegen.CommandLineArgument.ANGULAR
import senegai.codegen.CommandLineArgument.EXAMPLE_DATA
import senegai.codegen.CommandLineArgument.PERSISTENCE
import senegai.codegen.CommandLineArgument.REST
import senegai.codegen.CommandLineArgument.SERVICE
import java.nio.file.Path
import java.nio.file.Paths

enum class CommandLineArgument(val description: String) {
    ANGULAR("Path to angular generated sources"),
    SERVICE("Path to service generated sources"),
    REST("Path to rest generated sources"),
    PERSISTENCE("Path to persistence generated sources"),
    EXAMPLE_DATA("Path to example_data generated sources"),
    ;
}

data class CommandLineArguments(
    private val arguments: Map<CommandLineArgument, String>,
) {
    val directoryForAngularGeneratedSource: Path = Paths.get(requireNotNull(arguments[ANGULAR]))
    val directoryForServiceGeneratedSource: Path = Paths.get(requireNotNull(arguments[SERVICE]))
    val directoryForRestGeneratedSource: Path = Paths.get(requireNotNull(arguments[REST]))
    val directoryForPersistenceGeneratedSource: Path = Paths.get(requireNotNull(arguments[PERSISTENCE]))
    val directoryForExampleDataGeneratedSource: Path = Paths.get(requireNotNull(arguments[EXAMPLE_DATA]))
}

fun createCommandLineArguments(args: Array<String>): CommandLineArguments? {
    if (args.size != CommandLineArgument.entries.size) {
        val commandLineArgumentDescription = CommandLineArgument.entries.joinToString(separator = " ", prefix = "<", postfix = ">") { it.description }
        println("Wrong arguments! Need 5 paths: $commandLineArgumentDescription")
        return null
    }
    return CommandLineArguments(args
        .mapIndexed { index, argument -> Pair(CommandLineArgument.entries[index], argument) }
        .toMap())

}
