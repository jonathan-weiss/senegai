/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template ItemWTOInterfaceRenderer filled up
 * with the content of the passed models.
 */
object ItemWTOInterfaceRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |
          |/**
          | * The ${model.itemName} WTO (Web Transfer Object) class.
          | */
          |export interface ${model.itemName}WTO {
          |    ${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName}: ${attribute.typescriptAttributeType};
          """ } }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "wto/${model.itemNameLowercase}.wto.ts"
    }
}