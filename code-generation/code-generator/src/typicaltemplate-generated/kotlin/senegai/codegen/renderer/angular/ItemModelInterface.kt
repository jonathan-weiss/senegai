/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemModelInterface filled up
 * with the content of the passed models.
 */
object ItemModelInterface {

    fun renderTemplate(model: ItemModel): String {
        return """
          |
          |/**
          | * The ${model.itemName} DTO (Data Transfer Object) class.
          | */
          |export interface ${model.itemName} {
          |    id: number;
          |    ${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName}: string;
          """ } }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }
}