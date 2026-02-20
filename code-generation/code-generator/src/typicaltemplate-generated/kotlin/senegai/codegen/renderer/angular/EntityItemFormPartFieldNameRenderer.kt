/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartFieldNameRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartFieldNameRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |export enum ${model.item.itemName}FormPartFieldName {${ model.item.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName} = "${attribute.attributeName}",
          """ } }}
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-field-name.ts"
    }
}