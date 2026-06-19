/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.angular.ItemExampleDataRenderer

/**
 * Generate the content for the template `EntityExampleDataRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-example-data.ts`
 * - path: `opus-magnum/opus-magnum-example-data.ts`
 */
object EntityExampleDataRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |
          |
          |export const ${model.entityRootItem.itemName.screamingSnakeCase}_EXAMPLE_DATA: ${model.entityRootItem.itemName.pascalCase}WTO[] = [
          |    ${ItemExampleDataRenderer.renderTemplate(itemModel = model.entityRootItem, entityModel = model)}
          |
          |    
          |];
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-example-data.ts"
    }
}