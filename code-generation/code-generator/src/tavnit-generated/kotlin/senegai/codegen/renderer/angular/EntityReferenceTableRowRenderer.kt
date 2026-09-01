/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceTableRowRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-membrum-relatum-reference-table-row.model.ts`
 * - path: `entitas-relata/entitas-relata-membrum-relatum-reference-table/entitas-relata-membrum-relatum-reference-table-row.model.ts`
 */
object EntityReferenceTableRowRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {FormControl} from "@angular/forms";
          |import {${model.entityRootItem.itemName.pascalCase}DisplayRow} from "@app/${model.entityName.kebabCase}/${model.entityRootItem.itemName.kebabCase}-display";
          |import {UUID} from "@app/shared/uuid";
          |
          |/**
          | * One row of the ${model.entityRootItem.itemName.pascalCase} reference table.
          | *
          | * A reference is stored as a bare UUID in the form, which is meaningless to the user. The row
          | * therefore carries the display attributes of the resolved ${model.entityRootItem.itemName.pascalCase} flattened out, plus
          | * a back-reference to the FormControl that actually holds the UUID.
          | */
          |export interface ${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceTableRow extends ${model.entityRootItem.itemName.pascalCase}DisplayRow {
          |    formControl: FormControl<UUID>
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table-row.model.ts"
    }
}