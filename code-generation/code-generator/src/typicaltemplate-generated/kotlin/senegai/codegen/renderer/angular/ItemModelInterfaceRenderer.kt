/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemModelInterfaceRenderer filled up
 * with the content of the passed models.
 */
object ItemModelInterfaceRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |/**
          | * The ${model.itemName} DTO (Data Transfer Object) class.
          | */
          |export interface ${model.itemName} {
          |    id: number;
          |    ${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName}: ${attribute.typescriptAttributeType};
          """ } }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}.model.ts"
    }
}