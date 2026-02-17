/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template ItemSearchComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemSearchComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
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

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-search/${model.entityNameLowercase}-search.component.scss"
    }
}