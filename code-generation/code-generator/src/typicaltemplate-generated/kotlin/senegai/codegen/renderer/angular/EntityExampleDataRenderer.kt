/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityExampleDataRenderer filled up
 * with the content of the passed models.
 */
object EntityExampleDataRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {${model.entityName}WTO} from "@app/wto/${model.entityNameDashCase}.wto";
          |export const ${model.entityNameUppercase}_EXAMPLE_DATA: ${model.entityName}WTO[] = [
          |    {${ model.allAttributes.joinToString("") { attribute ->  """        ${attribute.attributeName}: 'example',
          """ } }    }
          |];
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-example-data.ts"
    }
}