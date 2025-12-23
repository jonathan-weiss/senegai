package senegai.codegen

import senegai.codegen.sourceamazing.DefinitionDataCollection
import senegai.codegen.renderer.Rendering
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if(args.size != 1) {
        println("Wrong arguments!")
        println("Use <path to generated angular files>")
        exitProcess(1)
    }

    val pathToGeneratedAngularFiles = Paths.get(args[0])

    val itemsModel = DefinitionDataCollection.collectDefinitionData()
    Rendering.renderClientFiles(pathToGeneratedAngularFiles, itemsModel)
}
