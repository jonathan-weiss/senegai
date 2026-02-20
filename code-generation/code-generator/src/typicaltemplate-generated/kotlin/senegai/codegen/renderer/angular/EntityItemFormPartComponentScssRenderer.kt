/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartComponentScssRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartComponentScssRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
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
          |.column-layout {
          |  display: flex;
          |  gap: 16px;
          |  padding: 16px;
          |}
          |
          |.column {
          |  flex: 1;
          |}
          |
          |@media (max-width: 768px) {
          |  .column-layout {
          |    flex-direction: column;
          |  }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part.component.scss"
    }
}