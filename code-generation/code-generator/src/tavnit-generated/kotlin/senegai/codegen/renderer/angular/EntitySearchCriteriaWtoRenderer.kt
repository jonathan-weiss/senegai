/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntitySearchCriteriaWtoRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-search-criteria.wto.ts`
 * - path: `wto/silva-optionum-search-criteria.wto.ts`
 */
object EntitySearchCriteriaWtoRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |/**
          | * The Silva Optionum search criteria WTO (Web Transfer Object), sent as the request body
          | * of the search endpoint. A blank query matches every Silva Optionum.
          | */
          |export interface ${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO {
          |    query: string;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "wto/${model.entityRootItem.itemName.kebabCase}-search-criteria.wto.ts"
    }
}