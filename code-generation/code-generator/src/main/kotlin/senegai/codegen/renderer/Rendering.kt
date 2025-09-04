package senegai.codegen.renderer

import senegai.codegen.renderer.angular.ItemBoardComponentHtml
import senegai.codegen.renderer.angular.ItemBoardComponentScss
import senegai.codegen.renderer.angular.ItemBoardComponentTypescript
import senegai.codegen.renderer.angular.ItemConfirmDeleteDialogComponentHtml
import senegai.codegen.renderer.angular.ItemConfirmDeleteDialogComponentScss
import senegai.codegen.renderer.angular.ItemConfirmDeleteDialogComponentTypescript
import senegai.codegen.renderer.angular.ItemEditFormComponentHtml
import senegai.codegen.renderer.angular.ItemEditFormComponentScss
import senegai.codegen.renderer.angular.ItemEditFormComponentTypescript
import senegai.codegen.renderer.angular.ItemModelInterface
import senegai.codegen.renderer.angular.ItemResultComponentHtml
import senegai.codegen.renderer.angular.ItemResultComponentScss
import senegai.codegen.renderer.angular.ItemResultComponentTypescript
import senegai.codegen.renderer.angular.ItemSearchComponentHtml
import senegai.codegen.renderer.angular.ItemSearchComponentScss
import senegai.codegen.renderer.angular.ItemSearchComponentTypescript
import senegai.codegen.renderer.angular.ItemService
import senegai.codegen.renderer.angular.TypescriptItemsRoutingList
import senegai.codegen.renderer.angular.TypescriptSideNavLinkList
import senegai.codegen.renderer.model.ItemModel
import senegai.codegen.renderer.model.ItemsModel
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

object Rendering {

    fun renderClientFiles(pathToGeneratedAngularFiles: Path, itemsModel: ItemsModel) {
        generateSideNavLinks(pathToGeneratedAngularFiles, itemsModel)
        generateRoutes(pathToGeneratedAngularFiles, itemsModel)
        itemsModel.allItems.forEach { item ->
            generateModel(pathToGeneratedAngularFiles, item)
            generateService(pathToGeneratedAngularFiles, item)
            generateEditFormComponent(pathToGeneratedAngularFiles, item)
            generateSearchComponent(pathToGeneratedAngularFiles, item)
            generateBoardComponent(pathToGeneratedAngularFiles, item)
            generateResultComponent(pathToGeneratedAngularFiles, item)
            generateConfirmDeleteComponent(pathToGeneratedAngularFiles, item)
        }
    }

    private fun generateRoutes(basePath: Path, itemsModel: ItemsModel) {
        val routesContent = TypescriptItemsRoutingList.renderTemplate(itemsModel)
        writeFile(basePath.resolve("generated-routes.ts"), routesContent)
    }

    private fun generateSideNavLinks(basePath: Path, itemsModel: ItemsModel) {
        val siteNavLinksContent = TypescriptSideNavLinkList.renderTemplate(itemsModel)
        writeFile(basePath.resolve("generated-side-nav-links.ts"), siteNavLinksContent)
    }

    private fun generateSearchComponent(basePath: Path, itemModel: ItemModel) {
        val itemFileNamePrefix = itemModel.itemNameForAngularFile
        val componentSuffix = "search"
        val componentName = "$itemFileNamePrefix-$componentSuffix"
        val directoryPath = basePath.toAngularItemPath(itemModel).resolve(componentName)

        writeAngularComponent(
            directoryPath = directoryPath,
            componentName = componentName,
            htmlContent = ItemSearchComponentHtml.renderTemplate(itemModel),
            scssContent = ItemSearchComponentScss.renderTemplate(itemModel),
            typescriptContent = ItemSearchComponentTypescript.renderTemplate(itemModel),
        )
    }

    private fun generateBoardComponent(basePath: Path, itemModel: ItemModel) {
        val itemFileNamePrefix = itemModel.itemNameForAngularFile
        val componentSuffix = "board"
        val componentName = "$itemFileNamePrefix-$componentSuffix"
        val directoryPath = basePath.toAngularItemPath(itemModel).resolve(componentName)

        writeAngularComponent(
            directoryPath = directoryPath,
            componentName = componentName,
            htmlContent = ItemBoardComponentHtml.renderTemplate(itemModel),
            scssContent = ItemBoardComponentScss.renderTemplate(itemModel),
            typescriptContent = ItemBoardComponentTypescript.renderTemplate(itemModel),
        )
    }

    private fun generateResultComponent(basePath: Path, itemModel: ItemModel) {
        val itemFileNamePrefix = itemModel.itemNameForAngularFile
        val componentSuffix = "result"
        val componentName = "$itemFileNamePrefix-$componentSuffix"
        val directoryPath = basePath.toAngularItemPath(itemModel).resolve(componentName)

        writeAngularComponent(
            directoryPath = directoryPath,
            componentName = componentName,
            htmlContent = ItemResultComponentHtml.renderTemplate(itemModel),
            scssContent = ItemResultComponentScss.renderTemplate(itemModel),
            typescriptContent = ItemResultComponentTypescript.renderTemplate(itemModel),
        )
    }

    private fun generateConfirmDeleteComponent(basePath: Path, itemModel: ItemModel) {
        val itemFileNamePrefix = itemModel.itemNameForAngularFile
        val componentSuffix = "confirm-delete-dialog"
        val componentName = "$itemFileNamePrefix-$componentSuffix"
        val directoryPath = basePath.toAngularItemPath(itemModel).resolve(componentName)

        writeAngularComponent(
            directoryPath = directoryPath,
            componentName = componentName,
            htmlContent = ItemConfirmDeleteDialogComponentHtml.renderTemplate(itemModel),
            scssContent = ItemConfirmDeleteDialogComponentScss.renderTemplate(itemModel),
            typescriptContent = ItemConfirmDeleteDialogComponentTypescript.renderTemplate(itemModel),
        )
    }

    private fun generateEditFormComponent(basePath: Path, itemModel: ItemModel) {
        val itemFileNamePrefix = itemModel.itemNameForAngularFile
        val componentSuffix = "edit-form"
        val componentName = "$itemFileNamePrefix-$componentSuffix"
        val directoryPath = basePath.toAngularItemPath(itemModel).resolve(componentName)

        writeAngularComponent(
            directoryPath = directoryPath,
            componentName = componentName,
            htmlContent = ItemEditFormComponentHtml.renderTemplate(itemModel),
            scssContent = ItemEditFormComponentScss.renderTemplate(itemModel),
            typescriptContent = ItemEditFormComponentTypescript.renderTemplate(itemModel),
        )
    }

    private fun writeAngularComponent(
        directoryPath: Path,
        componentName: String,
        htmlContent: String,
        scssContent: String,
        typescriptContent: String,
    ) {
        writeFile(directoryPath.resolve("${componentName}.component.html"), htmlContent)
        writeFile(directoryPath.resolve("${componentName}.component.scss"), scssContent)
        writeFile(directoryPath.resolve("${componentName}.component.ts"), typescriptContent)
    }

    private fun generateModel(basePath: Path, itemModel: ItemModel) {
        val modelContent = ItemModelInterface.renderTemplate(itemModel)
        writeFile(basePath.toAngularItemPath(itemModel).resolve("${itemModel.itemNameForAngularFile}.model.ts"), modelContent)
    }

    private fun generateService(basePath: Path, itemModel: ItemModel) {
        val modelContent = ItemService.renderTemplate(itemModel)
        writeFile(basePath.toAngularItemPath(itemModel).resolve("${itemModel.itemNameForAngularFile}.service.ts"), modelContent)
    }

    private fun Path.toAngularItemPath(itemModel: ItemModel): Path {
        val itemFileNamePrefix = itemModel.itemNameForAngularFile
        return this.resolve(itemFileNamePrefix)
    }

    private fun writeFile(filePath: Path, content: String) {
        require(!filePath.isDirectory()) { "$filePath is a directory" }
        filePath.parent.createDirectories()
        filePath.writeText(content)
    }
}
