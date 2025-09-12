/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormPartComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartComponentScssRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |@use '@angular/material' as mat;
          |
          |.form-row {
          |  margin-bottom: 20px;
          |
          |  mat-form-field {
          |    width: 100%;
          |  }
          |}
          |
          |.edit-area {
          |  border-left-color: darkgrey;
          |  border-left-width: 1px;
          |  border-left-style: solid;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component.scss"
    }
}