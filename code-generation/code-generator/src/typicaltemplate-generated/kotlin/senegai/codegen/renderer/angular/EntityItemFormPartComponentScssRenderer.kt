/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template EntityItemFormPartComponentScssRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartComponentScssRenderer : UiEntityItemRenderer {

    override fun renderTemplate(entity: UiEntityModel, model: UiItemModel): String {
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

    override fun filePath(entity: UiEntityModel, model: UiItemModel): String {
      return "${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component.scss"
    }
}