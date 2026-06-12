/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartFieldNameRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form-part-field-name.ts`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name.ts`
 */
object EntityItemFormPartFieldNameRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |
          |export enum ${model.entity.entityName.pascalCase}FormPartFieldName {
          |    ${ model.item.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName.camelCase} = "${attribute.attributeName.camelCase}",${ if(attribute.isNullable) { """    ${attribute.attributeName.camelCase}IsNotNull = "${attribute.attributeName.camelCase}IsNotNull",
                  |    
          """ } else { """
          """ } }
          """ } }}
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.entity.entityName.kebabCase}-form-part/${model.entity.entityName.kebabCase}-form-part-field-name.ts"
    }
}