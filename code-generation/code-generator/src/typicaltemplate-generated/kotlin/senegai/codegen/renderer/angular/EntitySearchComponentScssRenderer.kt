/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntitySearchComponentScssRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-search.component.scss`
 * - path: `opus-magnum/opus-magnum-search/opus-magnum-search.component.scss`
 */
object EntitySearchComponentScssRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |.search-card {
          |  margin-bottom: 20px;
          |}
          |
          |.search-fields {
          |  display: flex;
          |  flex-direction: column;
          |  gap: 16px;
          |  margin-bottom: 16px;
          |}
          |
          |.search-actions {
          |  display: flex;
          |  gap: 8px;
          |  justify-content: flex-end;
          |
          |  button {
          |    min-width: 100px;
          |  }
          |}
          |
          |mat-form-field {
          |  width: 100%;
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-search/${model.entityName.kebabCase}-search.component.scss"
    }
}