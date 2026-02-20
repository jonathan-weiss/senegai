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
          |export enum ${model.item.itemName.pascalCase}FormPartFieldName {${ model.item.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName.camelCase} = "${attribute.attributeName.camelCase}",
          """ } }}
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-field-name.ts"
    }
}