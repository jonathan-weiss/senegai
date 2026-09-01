/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityByIdsResultWtoRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-by-ids-result.wto.ts`
 * - path: `wto/silva-optionum-by-ids-result.wto.ts`
 */
object EntityByIdsResultWtoRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |
          |/**
          | * The Silva Optionum by-ids result WTO (Web Transfer Object), returned by the by-ids endpoint.
          | * It wraps the resolved Silva Optionums; unknown identifiers are omitted.
          | */
          |export interface ${model.entityRootItem.itemName.pascalCase}ByIdsResultWTO {
          |    ${model.entityRootItem.itemName.camelCase}List: Array<${model.entityRootItem.itemName.pascalCase}WTO>;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "wto/${model.entityRootItem.itemName.kebabCase}-by-ids-result.wto.ts"
    }
}