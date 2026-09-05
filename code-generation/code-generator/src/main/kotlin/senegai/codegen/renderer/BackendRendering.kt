package senegai.codegen.renderer

import senegai.codegen.renderer.be.BeEnumRenderer
import senegai.codegen.renderer.be.BeItemRenderer
import senegai.codegen.renderer.be.EnumBORenderer
import senegai.codegen.renderer.be.EnumExampleDataCreatorRenderer
import senegai.codegen.renderer.be.EnumMapperRenderer
import senegai.codegen.renderer.be.EnumWTORenderer
import senegai.codegen.renderer.be.ItemBORenderer
import senegai.codegen.renderer.be.ItemByIdsCriteriaBORenderer
import senegai.codegen.renderer.be.ItemByIdsCriteriaMapperRenderer
import senegai.codegen.renderer.be.ItemByIdsCriteriaWTORenderer
import senegai.codegen.renderer.be.ItemByIdsResultWTORenderer
import senegai.codegen.renderer.be.ItemControllerRenderer
import senegai.codegen.renderer.be.ItemExampleDataCreatorRenderer
import senegai.codegen.renderer.be.ItemExampleDataFetcherRenderer
import senegai.codegen.renderer.be.ItemExampleDataPopulatorRenderer
import senegai.codegen.renderer.be.ItemInMemoryRepositoryRenderer
import senegai.codegen.renderer.be.ItemMapperRenderer
import senegai.codegen.renderer.be.ItemPostgresSqlRepositoryRenderer
import senegai.codegen.renderer.be.ItemRepositoryRenderer
import senegai.codegen.renderer.be.ItemSearchCriteriaBORenderer
import senegai.codegen.renderer.be.ItemSearchCriteriaMapperRenderer
import senegai.codegen.renderer.be.ItemSearchCriteriaWTORenderer
import senegai.codegen.renderer.be.ItemSearchResultWTORenderer
import senegai.codegen.renderer.be.ItemServiceRenderer
import senegai.codegen.renderer.be.ItemWTORenderer
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
            beModel.items.forEach { beItemModel ->
                renderItem(beItemModel)
            }

            // Controller, service, persistence and the example data initializer only exist for
            // an item that can be addressed by a primary key.
            beModel.itemsWithPrimaryKey.forEach { beItemModel ->
                renderItemWithPrimaryKey(beItemModel)
            }

            beModel.enums.forEach { beEnumModel ->
                renderEnum(beEnumModel)
            }
        }

        private fun renderItem(beItemModel: BeItemModel) {
            val itemRenderers: List<Pair<BeItemRenderer, Path>> = listOf(
                ItemExampleDataCreatorRenderer to pathToGeneratedBackendExampleDataFiles,
                ItemBORenderer to pathToGeneratedBackendServiceFiles,
                ItemMapperRenderer to pathToGeneratedBackendRestFiles,
                ItemWTORenderer to pathToGeneratedBackendRestFiles,
            )

            renderAll(itemRenderers, beItemModel)
        }

        private fun renderItemWithPrimaryKey(beItemModel: BeItemModel) {
            val itemRenderers: List<Pair<BeItemRenderer, Path>> = listOf(
                ItemExampleDataPopulatorRenderer to pathToGeneratedBackendExampleDataFiles,
                ItemRepositoryRenderer to pathToGeneratedBackendServiceFiles,
                ItemServiceRenderer to pathToGeneratedBackendServiceFiles,
                ItemInMemoryRepositoryRenderer to pathToGeneratedBackendPersistenceFiles,
                ItemPostgresSqlRepositoryRenderer to pathToGeneratedBackendPersistenceFiles,
                ItemControllerRenderer to pathToGeneratedBackendRestFiles,
                ItemSearchCriteriaBORenderer to pathToGeneratedBackendServiceFiles,
                ItemSearchCriteriaWTORenderer to pathToGeneratedBackendRestFiles,
                ItemSearchResultWTORenderer to pathToGeneratedBackendRestFiles,
                ItemSearchCriteriaMapperRenderer to pathToGeneratedBackendRestFiles,
                ItemByIdsCriteriaBORenderer to pathToGeneratedBackendServiceFiles,
                ItemByIdsCriteriaWTORenderer to pathToGeneratedBackendRestFiles,
                ItemByIdsResultWTORenderer to pathToGeneratedBackendRestFiles,
                ItemByIdsCriteriaMapperRenderer to pathToGeneratedBackendRestFiles,
                ItemExampleDataFetcherRenderer to pathToGeneratedBackendExampleDataFiles,
            )

            renderAll(itemRenderers, beItemModel)
        }

        private fun renderAll(renderers: List<Pair<BeItemRenderer, Path>>, beItemModel: BeItemModel) {
            renderers.forEach { (renderer, basePath) ->
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
                EnumMapperRenderer to pathToGeneratedBackendRestFiles,
                EnumWTORenderer to pathToGeneratedBackendRestFiles,
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
            // createDirectories() is not reliably a no-op for an existing directory on every
            // file system, and several files share one directory, so check first.
            val parent = filePath.parent
            if (!parent.isDirectory()) {
                parent.createDirectories()
            }
            filePath.writeText(content)
        }
    }
}
