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
            ItemBoardComponentHtmlRenderer,
            ItemBoardComponentScssRenderer,
            ItemBoardComponentTypescriptRenderer,
            ItemConfirmDeleteDialogComponentHtmlRenderer,
            ItemConfirmDeleteDialogComponentScssRenderer,
            ItemConfirmDeleteDialogComponentTypescriptRenderer,
            ItemFormFieldNameRenderer,
            ItemFormValidationServiceRenderer,
            ItemFormInitialServiceRenderer,
            ItemEditFormServiceRenderer,
            ItemFormComponentHtmlRenderer,
            ItemFormComponentScssRenderer,
            ItemFormComponentTypescriptRenderer,
            ItemResultComponentHtmlRenderer,
            ItemResultComponentScssRenderer,
            ItemResultComponentTypescriptRenderer,
            ItemSearchComponentHtmlRenderer,
            ItemSearchComponentScssRenderer,
            ItemSearchComponentTypescriptRenderer,
            ItemServiceRenderer,
        )

        uiEntities.forEach { uiEntityModel ->
            entityRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(uiEntityModel)),
                    content = renderer.renderTemplate(uiEntityModel),
                )
            }
        }


        val itemRenderer: List<UiItemRenderer> = listOf(
            ItemFormPartComponentHtmlRenderer,
            ItemFormPartComponentScssRenderer,
            ItemFormPartComponentTypescriptRenderer,
            ItemModelInterfaceRenderer,
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
