/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form-part.component.scss`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part.component.scss`
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
      return "${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part.component.scss"
    }
}