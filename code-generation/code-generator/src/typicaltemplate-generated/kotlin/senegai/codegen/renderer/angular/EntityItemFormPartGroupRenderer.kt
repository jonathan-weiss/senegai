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
          |import {${model.item.itemName}FormPartFieldName} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-field-name";
          |
          |export interface ${model.item.itemName}FormPartGroup {${ model.item.attributes.joinToString("") { attribute ->  """
              |    [${model.item.itemName}FormPartFieldName.${attribute.attributeName}]: FormControl<string>,
          """ } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-group.ts"
    }
}