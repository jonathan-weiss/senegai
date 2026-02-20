/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityFormComponentScssRenderer filled up
 * with the content of the passed models.
 */
object EntityFormComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |.edit-form-container {
          |  margin: 0 auto;
          |  padding: 20px;
          |
          |  mat-card {
          |    .mat-mdc-card-header {
          |      margin-bottom: 20px;
          |    }
          |  }
          |}
          |
          |.form-actions {
          |  display: flex;
          |  justify-content: flex-end;
          |  gap: 12px;
          |  margin-top: 32px;
          |
          |  button {
          |    min-width: 100px;
          |  }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form.component.scss"
    }
}