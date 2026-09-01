/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityByIdsCriteriaWtoRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-by-ids-criteria.wto.ts`
 * - path: `wto/silva-optionum-by-ids-criteria.wto.ts`
 */
object EntityByIdsCriteriaWtoRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {UUID} from "@app/shared/uuid";
          |
          |/**
          | * The Silva Optionum by-ids criteria WTO (Web Transfer Object), sent as the request body of
          | * the by-ids endpoint. It carries the identifier of every Silva Optionum to resolve, which is
          | * what a reference to this entity is stored as.
          | */
          |export interface ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaWTO {
          |    ${model.idAttribute.attributeName.camelCase}List: Array<UUID>;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "wto/${model.entityRootItem.itemName.kebabCase}-by-ids-criteria.wto.ts"
    }
}