package senegai.codegen.renderer

import senegai.codegen.renderer.be.BeEnumRenderer
import senegai.codegen.renderer.be.BeItemRenderer
import senegai.codegen.renderer.model.be.BeEnumModel
import senegai.codegen.renderer.model.be.BeItemModel
import senegai.codegen.renderer.model.be.BeModel
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

object BackendRendering {

    fun renderBackendFiles(
        pathToGeneratedBackendRestFiles: Path,
        pathToGeneratedBackendServiceFiles: Path,
        pathToGeneratedBackendPersistenceFiles: Path,
        pathToGeneratedBackendExampleDataFiles: Path,
        beModel: BeModel,
    ) {
        val worker = RenderingWorker(
            pathToGeneratedBackendRestFiles = pathToGeneratedBackendRestFiles,
            pathToGeneratedBackendServiceFiles = pathToGeneratedBackendServiceFiles,
            pathToGeneratedBackendPersistenceFiles = pathToGeneratedBackendPersistenceFiles,
            pathToGeneratedBackendExampleDataFiles = pathToGeneratedBackendExampleDataFiles,
        )
        worker.renderClientFiles(beModel)
    }

    private data class RenderingWorker(
        val pathToGeneratedBackendRestFiles: Path,
        val pathToGeneratedBackendServiceFiles: Path,
        val pathToGeneratedBackendPersistenceFiles: Path,
        val pathToGeneratedBackendExampleDataFiles: Path,

        ) {

        fun renderClientFiles(beModel: BeModel) {
            val beEntities = beModel.entities

            beModel.items.forEach { beItemModel ->
                renderWTO(beItemModel)
            }

            beModel.enums.forEach { beEnumModel ->
                renderEnum(beEnumModel)
            }


            beEntities.forEach { beEntityModel ->
//                renderEntityBoard(beEntityModel)
//                renderEntityForm(beEntityModel)
            }
        }

        private fun renderWTO(beItemModel: BeItemModel) {
            val itemRenderer: List<BeItemRenderer> = listOf(
                //ItemWTOInterfaceRenderer,
            )

            itemRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedBackendRestFiles.resolve(renderer.filePath(beItemModel)),
                    content = renderer.renderTemplate(beItemModel),
                )
            }
        }

        private fun renderEnum(beEnumModel: BeEnumModel) {
            val enumRenderer: List<BeEnumRenderer> = listOf(
//                EnumDefinitionTypescriptRenderer,
//                EnumI18nComponentHtmlRenderer,
//                EnumI18nComponentScssRenderer,
//                EnumI18nComponentTypescriptRenderer,
//                EnumI18nComponentSpecTypescriptRenderer,
//                EnumSelectorComponentHtmlRenderer,
//                EnumSelectorComponentScssRenderer,
//                EnumSelectorComponentTypescriptRenderer,
//                EnumListFormFieldTableComponentHtmlRenderer,
//                EnumListFormFieldTableComponentTypescriptRenderer,
            )

            enumRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedBackendServiceFiles.resolve(renderer.filePath(beEnumModel)),
                    content = renderer.renderTemplate(beEnumModel),
                )
            }
        }

        private fun writeFile(filePath: Path, content: String) {
            require(!filePath.isDirectory()) { "$filePath is a directory" }
            filePath.parent.createDirectories()
            filePath.writeText(content)
        }
    }
}
