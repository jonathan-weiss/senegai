package senegai.codegen.renderer

import senegai.codegen.renderer.angular.*
import senegai.codegen.renderer.model.ItemsModel
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

object Rendering {

    fun renderClientFiles(pathToGeneratedAngularFiles: Path, itemsModel: ItemsModel) {
        val itemsRenderer = listOf(
            TypescriptItemsRoutingListRenderer,
            TypescriptSideNavLinkListRenderer,
        )

        itemsRenderer.forEach { renderer ->
            writeFile(
                filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(itemsModel)),
                content = renderer.renderTemplate(itemsModel),
            )
        }


        val itemRenderer = listOf(
            ItemBoardComponentHtmlRenderer,
            ItemBoardComponentScssRenderer,
            ItemBoardComponentTypescriptRenderer,
            ItemConfirmDeleteDialogComponentHtmlRenderer,
            ItemConfirmDeleteDialogComponentScssRenderer,
            ItemConfirmDeleteDialogComponentTypescriptRenderer,
            ItemEditFormServiceRenderer,
            ItemFormComponentHtmlRenderer,
            ItemFormComponentScssRenderer,
            ItemFormComponentTypescriptRenderer,
            ItemFormPartComponentHtmlRenderer,
            ItemFormPartComponentScssRenderer,
            ItemFormPartComponentTypescriptRenderer,
            ItemModelInterfaceRenderer,
            ItemResultComponentHtmlRenderer,
            ItemResultComponentScssRenderer,
            ItemResultComponentTypescriptRenderer,
            ItemSearchComponentHtmlRenderer,
            ItemSearchComponentScssRenderer,
            ItemSearchComponentTypescriptRenderer,
            ItemServiceRenderer,
        )

        itemsModel.allItems.forEach { itemModel ->
            itemRenderer.forEach { renderer ->
                writeFile(
                    filePath = pathToGeneratedAngularFiles.resolve(renderer.filePath(itemModel)),
                    content = renderer.renderTemplate(itemModel),
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
