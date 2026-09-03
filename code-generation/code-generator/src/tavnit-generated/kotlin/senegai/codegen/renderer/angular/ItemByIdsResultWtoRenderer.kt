/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemByIdsResultWtoRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-by-ids-result.wto.ts`
 * - path: `wto/silva-optionum-by-ids-result.wto.ts`
 */
object ItemByIdsResultWtoRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |import {${model.itemName.pascalCase}WTO} from "@app/wto/${model.itemName.kebabCase}.wto";
          |
          |/**
          | * The Silva Optionum by-ids result WTO (Web Transfer Object), returned by the by-ids endpoint.
          | * It wraps the resolved Silva Optionums; unknown identifiers are omitted.
          | */
          |export interface ${model.itemName.pascalCase}ByIdsResultWTO {
          |    ${model.itemName.camelCase}List: Array<${model.itemName.pascalCase}WTO>;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "wto/${model.itemName.kebabCase}-by-ids-result.wto.ts"
    }
}