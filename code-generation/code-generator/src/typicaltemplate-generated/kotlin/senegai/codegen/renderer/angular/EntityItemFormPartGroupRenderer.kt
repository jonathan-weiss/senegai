/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartGroupRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartGroupRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-field-name";
          |
          |export interface ${model.item.itemName.pascalCase}FormPartGroup {${ model.item.attributes.joinToString("") { attribute ->  """
              |    [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}]: FormControl<${attribute.typescriptAttributeFormType}>,${ if(attribute.isNullable) { """    [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull]: FormControl<boolean>,
          """ } else { """
          """ } }
          """ } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-group.ts"
    }
}