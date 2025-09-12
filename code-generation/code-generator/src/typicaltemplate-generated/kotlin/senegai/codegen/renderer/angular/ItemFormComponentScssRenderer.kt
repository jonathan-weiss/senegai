/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemFormComponentScssRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |.edit-form-container {
          |  margin: 0 auto;
          |  padding: 20px;
          |
          |  mat-card {
          |    .mat-mdc-card-header {
          |      margin-bottom: 20px;
          |    }
          |  }
          |}
          |
          |.form-actions {
          |  display: flex;
          |  justify-content: flex-end;
          |  gap: 12px;
          |  margin-top: 32px;
          |
          |  button {
          |    min-width: 100px;
          |  }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form.component.scss"
    }
}