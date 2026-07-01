/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.angular.AttributeExampleDataRenderer

/**
 * Generate the content for the template `ItemExampleDataRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-example-data.ts`
 * - path: `opus-magnum/opus-magnum-example-data.ts`
 */
object ItemExampleDataRenderer {

    fun renderTemplate(itemModel: UiItemModel, entityModel: UiEntityModel): String {
        return """
          |            {
          |${ itemModel.attributes.joinToString("") { attribute ->  """        ${attribute.attributeName.camelCase}: ${AttributeExampleDataRenderer.renderTemplate(attributeModel = attribute, entityModel = entityModel)},
              |""" } }
          |    }
        """.trimMargin(marginPrefix = "|")
    }

    fun filePath(itemModel: UiItemModel, entityModel: UiEntityModel): String {
      return "opus-magnum/opus-magnum-example-data.ts"
    }
}