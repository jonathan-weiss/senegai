package senegai.codegen.renderer

import senegai.codegen.renderer.be.BeEntityRenderer
import senegai.codegen.renderer.be.BeEnumRenderer
import senegai.codegen.renderer.be.BeItemRenderer
import senegai.codegen.renderer.be.EntityExampleDataCreatorRenderer
import senegai.codegen.renderer.be.EntityInMemoryRepositoryRenderer
import senegai.codegen.renderer.be.EntityRepositoryRenderer
import senegai.codegen.renderer.be.EntityServiceRenderer
import senegai.codegen.renderer.be.EnumBORenderer
import senegai.codegen.renderer.be.EnumExampleDataCreatorRenderer
import senegai.codegen.renderer.be.ItemBORenderer
import senegai.codegen.renderer.be.ItemExampleDataCreatorRenderer
import senegai.codegen.renderer.model.be.BeEntityModel
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
        worker.renderBackendFiles(beModel)
    }

    private data class RenderingWorker(
        val pathToGeneratedBackendRestFiles: Path,
        val pathToGeneratedBackendServiceFiles: Path,
        val pathToGeneratedBackendPersistenceFiles: Path,
        val pathToGeneratedBackendExampleDataFiles: Path,

        ) {

        fun renderBackendFiles(beModel: BeModel) {
            beModel.entities.forEach { beEntityModel ->
                renderEntity(beEntityModel)
            }

            beModel.items.forEach { beItemModel ->
                renderItem(beItemModel)
            }

            beModel.enums.forEach { beEnumModel ->
                renderEnum(beEnumModel)
            }
        }

        private fun renderEntity(beEntityModel: BeEntityModel) {
            val entityRenderer: List<Pair<BeEntityRenderer, Path>> = listOf(
                EntityExampleDataCreatorRenderer to pathToGeneratedBackendExampleDataFiles,
                EntityRepositoryRenderer to pathToGeneratedBackendServiceFiles,
                EntityServiceRenderer to pathToGeneratedBackendServiceFiles,
                EntityInMemoryRepositoryRenderer to pathToGeneratedBackendPersistenceFiles,
            )

            entityRenderer.forEach { (renderer, basePath) ->
                writeFile(
                    filePath = basePath.resolve(renderer.filePath(beEntityModel)),
                    content = renderer.renderTemplate(beEntityModel),
                )
            }
        }

        private fun renderItem(beItemModel: BeItemModel) {
            val itemRenderer: List<Pair<BeItemRenderer, Path>> = listOf(
                ItemExampleDataCreatorRenderer to pathToGeneratedBackendExampleDataFiles,
                ItemBORenderer to pathToGeneratedBackendServiceFiles,
            )

            itemRenderer.forEach { (renderer, basePath) ->
                writeFile(
                    filePath = basePath.resolve(renderer.filePath(beItemModel)),
                    content = renderer.renderTemplate(beItemModel),
                )
            }
        }

        private fun renderEnum(beEnumModel: BeEnumModel) {
            val enumRenderer: List<Pair<BeEnumRenderer, Path>> = listOf(
                EnumExampleDataCreatorRenderer to pathToGeneratedBackendExampleDataFiles,
                EnumBORenderer to pathToGeneratedBackendServiceFiles,
            )

            enumRenderer.forEach { (renderer, basePath) ->
                writeFile(
                    filePath = basePath.resolve(renderer.filePath(beEnumModel)),
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
