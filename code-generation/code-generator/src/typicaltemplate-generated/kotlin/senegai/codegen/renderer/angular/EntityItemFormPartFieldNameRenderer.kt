/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template EntityItemFormPartFieldNameRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartFieldNameRenderer : UiEntityItemRenderer {

    override fun renderTemplate(entity: UiEntityModel, model: UiItemModel): String {
        return """
          |
          |export enum ${model.itemName}FormPartFieldName {${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName} = "${attribute.attributeName}",
          """ } }}
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(entity: UiEntityModel, model: UiItemModel): String {
      return "${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-field-name.ts"
    }
}