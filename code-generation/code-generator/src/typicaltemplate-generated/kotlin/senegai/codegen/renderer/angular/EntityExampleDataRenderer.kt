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
          |import {${model.entityName.pascalCase}WTO} from "@app/wto/${model.entityName.kebabCase}.wto";
          |export const ${model.entityName.screamingSnakeCase}_EXAMPLE_DATA: ${model.entityName.pascalCase}WTO[] = [
          |    {${ model.allAttributes.joinToString("") { attribute ->  """        ${attribute.attributeName.camelCase}: 'example',
          """ } }    }
          |];
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-example-data.ts"
    }
}