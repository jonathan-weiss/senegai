/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemResultComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemResultComponentScssRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |.${model.itemNameLowercase}-table-container {
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
          |  .mat-elevation-z8 {
          |    box-shadow: 0 5px 5px -3px rgba(0, 0, 0, .2),
          |    0 8px 10px 1px rgba(0, 0, 0, .14),
          |    0 3px 14px 2px rgba(0, 0, 0, .12);
          |  }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-result/${model.itemNameLowercase}-result.component.scss"
    }
}