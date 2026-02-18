package senegai.codegen.renderer

import senegai.codegen.renderer.angular.*
import senegai.codegen.renderer.model.ui.UiModel
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

object Rendering {

    fun renderClientFiles(pathToGeneratedAngularFiles: Path, uiModel: UiModel) {
        val entityListRenderer = listOf(
            TypescriptItemsRoutingListRenderer,
            TypescriptSideNavLinkListRenderer,
        )
        val uiItems = uiModel.uiItems
        val uiEntities = uiModel.uiEntities

        entityListRenderer.forEach { renderer ->
            writeFile(
                filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiEntities)),
                content = renderer.renderTemplate(uiEntities),
            )
        }

        val entityRenderer: List<UiEntityRenderer> = listOf(
            EntityBoardComponentHtmlRenderer,
            EntityBoardComponentScssRenderer,
            EntityBoardComponentTypescriptRenderer,
            EntityConfirmDeleteDialogComponentHtmlRenderer,
            EntityConfirmDeleteDialogComponentScssRenderer,
            EntityConfirmDeleteDialogComponentTypescriptRenderer,
            EntityFormComponentHtmlRenderer,
            EntityFormComponentScssRenderer,
            EntityFormComponentTypescriptRenderer,
            EntityResultComponentHtmlRenderer,
            EntityResultComponentScssRenderer,
            EntityResultComponentTypescriptRenderer,
            EntitySearchComponentHtmlRenderer,
            EntitySearchComponentScssRenderer,
            EntitySearchComponentTypescriptRenderer,
            EntityServiceRenderer,
            EntityExampleDataRenderer,
        )

        uiEntities.forEach { uiEntityModel ->
            entityRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiEntityModel)),
                    content = renderer.renderTemplate(uiEntityModel),
                )
            }
        }

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

        uiEntities.forEach { uiEntityModel ->
            uiEntityModel.entityItemModels.forEach { uiItemModel ->
                uiEntityItemRenderer.forEach { renderer ->
                    writeFile(
                        filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(entity = uiEntityModel, model = uiItemModel)),
                        content = renderer.renderTemplate(entity = uiEntityModel, model = uiItemModel),
                    )
                }
            }
        }

        val itemRenderer: List<UiItemRenderer> = listOf(
            ItemWTOInterfaceRenderer,
        )

        uiItems.forEach { uiItemModel ->
            itemRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiItemModel)),
                    content = renderer.renderTemplate(uiItemModel),
                )
            }
        }
    }

    private fun writeFile(filePath: Path, content: String) {
        require(!filePath.isDirectory()) { "$filePath is a directory" }
        filePath.parent.createDirectories()
        filePath.writeText(content)
    }
}
