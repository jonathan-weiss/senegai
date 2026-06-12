/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemWTOInterfaceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum.wto.ts`
 * - path: `wto/silva-optionum.wto.ts`
 */
object ItemWTOInterfaceRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |
          |${ model.directlyNestedItems.joinToString("") { nestedItem ->  """
              |import {${nestedItem.itemName.pascalCase}WTO} from "@app/wto/${nestedItem.itemName.kebabCase}.wto";
          """ } }
          |/**
          | * The Silva Optionum WTO (Web Transfer Object) class.
          | */
          |export interface ${model.itemName.pascalCase}WTO {
          |    ${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName.camelCase}: ${attribute.typescriptAttributeType};
          """ } }    
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "wto/${model.itemName.kebabCase}.wto.ts"
    }
}