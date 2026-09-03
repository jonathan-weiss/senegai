/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemSearchResultWtoRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-search-result.wto.ts`
 * - path: `wto/silva-optionum-search-result.wto.ts`
 */
object ItemSearchResultWtoRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |import {${model.itemName.pascalCase}WTO} from "@app/wto/${model.itemName.kebabCase}.wto";
          |
          |/**
          | * The Silva Optionum search result WTO (Web Transfer Object), returned by the search
          | * endpoint. It wraps the found Silva Optionums.
          | */
          |export interface ${model.itemName.pascalCase}SearchResultWTO {
          |    ${model.itemName.camelCase}List: Array<${model.itemName.pascalCase}WTO>;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "wto/${model.itemName.kebabCase}-search-result.wto.ts"
    }
}