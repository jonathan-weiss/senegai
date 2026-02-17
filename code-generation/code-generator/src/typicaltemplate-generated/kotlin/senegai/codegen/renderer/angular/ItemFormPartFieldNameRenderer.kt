/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template ItemFormPartFieldNameRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartFieldNameRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |
          |export enum ${model.itemName}FormPartFieldName {${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName} = "${attribute.attributeName}",
          """ } }}
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "opus-magnum/opus-magnum-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-field-name.ts"
    }
}