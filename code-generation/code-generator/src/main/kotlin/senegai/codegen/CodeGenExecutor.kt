package senegai.codegen

import senegai.codegen.definitions.DefinitionDataCollection
import senegai.codegen.renderer.Rendering
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if(args.size != 2) {
        println("Wrong arguments!")
        println("Use <path to xml definition file> <path to generated angular files>")
        exitProcess(1)
    }

    val pathToXmlDefinitionFile = Paths.get(args[0])
    val pathToGeneratedAngularFiles = Paths.get(args[1])

    val itemsModel = DefinitionDataCollection.collectDefinitionData(pathToXmlDefinitionFile)
    Rendering.renderClientFiles(pathToGeneratedAngularFiles, itemsModel)
}
