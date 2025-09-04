package senegai.codegen.renderercreator

import org.codeblessing.typicaltemplate.FileSearchLocation
import org.codeblessing.typicaltemplate.TemplateRendererConfiguration
import org.codeblessing.typicaltemplate.TemplatingConfiguration
import org.codeblessing.typicaltemplate.TypicalTemplateApi
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.system.exitProcess

private const val PRINT_CREATED_TEMPLATE_RENDERERS = true

fun main(args: Array<String>) {
    if(args.size != 2) {
        println("Wrong arguments!")
        println("Use <path to angular source> <path to target template renderers base dir>")
        exitProcess(1)
    }

    println("Generating template renderer with typical template")
    val config = gatherTemplatingConfigurations(
        pathToAngularSourceTemplates = Paths.get(args[0]),
        pathToTargetTemplateRenderersBaseDir = Paths.get(args[1])
    )
    val createdTemplateRenderers = TypicalTemplateApi.runTypicalTemplate(config)

    if(PRINT_CREATED_TEMPLATE_RENDERERS) {
        printCreatedTemplateRenders(createdTemplateRenderers)
    }
}

private fun printCreatedTemplateRenders(createdTemplateRenderers: Map<TemplatingConfiguration, List<Path>>) {
    for ((configuration, paths) in createdTemplateRenderers) {
        println("Configuration: $configuration created paths:")
        for (path in paths) {
            println("- TemplateRenderer $path")
        }
    }
}

private fun gatherTemplatingConfigurations(
    pathToAngularSourceTemplates: Path,
    pathToTargetTemplateRenderersBaseDir: Path,
): List<TemplatingConfiguration> {
    val rootDirectoriesToSearch = listOf(
        FileSearchLocation(
            rootDirectoryToSearch = pathToAngularSourceTemplates,
            filenameMatchingPattern = Regex(".*\\.html"),
        ),
        FileSearchLocation(
            rootDirectoryToSearch = pathToAngularSourceTemplates,
            filenameMatchingPattern = Regex(".*\\.scss"),
        ),
        FileSearchLocation(
            rootDirectoryToSearch = pathToAngularSourceTemplates,
            filenameMatchingPattern = Regex(".*\\.ts"),
        ),
    )

    val templateRendererConfiguration = TemplateRendererConfiguration(
        templateRendererTargetSourceBasePath = pathToTargetTemplateRenderersBaseDir,
    )
    val configuration = TemplatingConfiguration(
        fileSearchLocations = rootDirectoriesToSearch,
        templateRendererConfiguration = templateRendererConfiguration,
    )
    return listOf(configuration)
}
