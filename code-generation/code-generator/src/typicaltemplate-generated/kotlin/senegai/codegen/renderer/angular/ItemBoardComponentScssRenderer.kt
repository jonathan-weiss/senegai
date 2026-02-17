/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template ItemBoardComponentScssRenderer filled up
 * with the content of the passed models.
 */
object ItemBoardComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |.${model.entityNameLowercase}-container {
          |  padding: 20px;
          |
          |  h2 {
          |    margin-bottom: 20px;
          |    color: rgba(0, 0, 0, 0.87);
          |  }
          |}
          |
          |.${model.entityNameLowercase}-accordion {
          |  .mat-expansion-panel {
          |    margin-bottom: 16px;
          |  }
          |
          |  .mat-expansion-panel-header {
          |    mat-panel-title {
          |      display: flex;
          |      align-items: center;
          |      gap: 8px;
          |    }
          |  }
          |
          |  mat-icon {
          |    margin-right: 8px;
          |  }
          |}
          |
          |::ng-deep .mat-expansion-panel-body {
          |  padding: 16px 24px 24px !important;
          |}
          |
          |.edit-form-section {
          |  margin-top: 32px;
          |  border-top: 1px solid rgba(0, 0, 0, 0.12);
          |  padding-top: 24px;
          |
          |  h3 {
          |    margin-bottom: 20px;
          |    color: rgba(0, 0, 0, 0.87);
          |  }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-board/${model.entityNameLowercase}-board.component.scss"
    }
}