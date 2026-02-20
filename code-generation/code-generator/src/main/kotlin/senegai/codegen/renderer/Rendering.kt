package senegai.codegen.renderer

import senegai.codegen.renderer.angular.*
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiEntityViewsModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.UiModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

object Rendering {

    fun renderClientFiles(pathToGeneratedAngularFiles: Path, uiModel: UiModel) {
        val worker = RenderingWorker(pathToGeneratedAngularFiles)
        worker.renderClientFiles(uiModel)
    }

    private data class RenderingWorker(
        val pathToGeneratedAngularFiles: Path,
    ) {

        fun renderClientFiles(uiModel: UiModel) {
            val uiEntities = uiModel.uiEntities

            renderNavigation(uiEntities)
            uiModel.uiItems.forEach { uiItemModel ->
                renderWTO(uiItemModel)
            }


            uiModel.uiEntitiesViews.forEach { uiEntityView ->
                uiEntityView.formView.entityItems.forEach { entityItem ->
                    renderFormPart(entityItem)
                }
            }

            uiEntities.forEach { uiEntityModel ->
                renderEntityBoard(uiEntityModel)
                renderEntityForm(uiEntityModel)
            }
        }

        private fun renderNavigation(uiEntities: List<UiEntityModel>) {
            val entityListRenderer = listOf(
                TypescriptItemsRoutingListRenderer,
                TypescriptSideNavLinkListRenderer,
            )

            entityListRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiEntities)),
                    content = renderer.renderTemplate(uiEntities),
                )
            }
        }

        private fun renderWTO(uiItemModel: UiItemModel) {
            val itemRenderer: List<UiItemRenderer> = listOf(
                ItemWTOInterfaceRenderer,
            )

            itemRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiItemModel)),
                    content = renderer.renderTemplate(uiItemModel),
                )
            }
        }

        private fun renderEntityBoard(uiEntityModel: UiEntityModel) {
            val entityRenderer: List<UiEntityRenderer> = listOf(
                EntityBoardComponentHtmlRenderer,
                EntityBoardComponentScssRenderer,
                EntityBoardComponentTypescriptRenderer,
                EntityRoutableEditComponentHtmlRenderer,
                EntityRoutableEditComponentScssRenderer,
                EntityRoutableEditComponentTypescriptRenderer,
                EntityConfirmDeleteDialogComponentHtmlRenderer,
                EntityConfirmDeleteDialogComponentScssRenderer,
                EntityConfirmDeleteDialogComponentTypescriptRenderer,
                EntityResultComponentHtmlRenderer,
                EntityResultComponentScssRenderer,
                EntityResultComponentTypescriptRenderer,
                EntitySearchComponentHtmlRenderer,
                EntitySearchComponentScssRenderer,
                EntitySearchComponentTypescriptRenderer,
                EntityServiceRenderer,
                EntityExampleDataRenderer,
            )

            entityRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiEntityModel)),
                    content = renderer.renderTemplate(uiEntityModel),
                )
            }
        }

        private fun renderEntityForm(uiEntityModel: UiEntityModel) {
            val entityRenderer: List<UiEntityRenderer> = listOf(
                EntityFormComponentHtmlRenderer,
                EntityFormComponentScssRenderer,
                EntityFormComponentTypescriptRenderer,
            )

            entityRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiEntityModel)),
                    content = renderer.renderTemplate(uiEntityModel),
                )
            }
        }

        private fun renderFormPart(
            formViewItemModel: UiEntityFormViewItemModel,
        ) {
            val uiEntityItemRenderer: List<UiEntityItemRenderer> = listOf(
                EntityItemFormPartComponentHtmlRenderer,
                EntityItemFormPartComponentScssRenderer,
                EntityItemFormPartComponentTypescriptRenderer,
                EntityItemFormPartFieldNameRenderer,
                EntityItemFormPartValidationServiceRenderer,
                EntityItemFormPartInitialValueServiceRenderer,
                EntityItemFormPartServiceRenderer,
                EntityItemFormPartGroupRenderer,
            )

            uiEntityItemRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(model = formViewItemModel)),
                    content = renderer.renderTemplate(model = formViewItemModel),
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
