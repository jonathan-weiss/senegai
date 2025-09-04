/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemEditFormComponentScss filled up
 * with the content of the passed models.
 */
object ItemEditFormComponentScss {

    fun renderTemplate(model: ItemModel): String {
        return """
          |.edit-form-container {
          |  max-width: 600px;
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
          |.form-row {
          |  margin-bottom: 20px;
          |
          |  mat-form-field {
          |    width: 100%;
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
}