/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemSearchComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemSearchComponentScssRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |.search-card {
          |  margin-bottom: 20px;
          |}
          |
          |.search-fields {
          |  display: flex;
          |  flex-direction: column;
          |  gap: 16px;
          |  margin-bottom: 16px;
          |}
          |
          |.search-actions {
          |  display: flex;
          |  gap: 8px;
          |  justify-content: flex-end;
          |
          |  button {
          |    min-width: 100px;
          |  }
          |}
          |
          |mat-form-field {
          |  width: 100%;
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-search/${model.itemNameLowercase}-search.component.scss"
    }
}