/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceTableComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-membrum-relatum-reference-table.component.scss`
 * - path: `entitas-relata/entitas-relata-membrum-relatum-reference-table/entitas-relata-membrum-relatum-reference-table.component.scss`
 */
object EntityReferenceTableComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |.table-container {
          |  padding: 20px;
          |
          |  table {
          |    width: 100%;
          |  }
          |
          |  th.mat-header-cell {
          |    font-weight: bold;
          |    color: rgba(0, 0, 0, 0.87);
          |  }
          |
          |  .action-bar {
          |    margin-bottom: 10px;
          |  }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table.component.scss"
    }
}