/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntitySearchResultWtoRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-search-result.wto.ts`
 * - path: `wto/silva-optionum-search-result.wto.ts`
 */
object EntitySearchResultWtoRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |
          |/**
          | * The Silva Optionum search result WTO (Web Transfer Object), returned by the search
          | * endpoint. It wraps the found Silva Optionums.
          | */
          |export interface ${model.entityRootItem.itemName.pascalCase}SearchResultWTO {
          |    ${model.entityRootItem.itemName.camelCase}List: Array<${model.entityRootItem.itemName.pascalCase}WTO>;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "wto/${model.entityRootItem.itemName.kebabCase}-search-result.wto.ts"
    }
}