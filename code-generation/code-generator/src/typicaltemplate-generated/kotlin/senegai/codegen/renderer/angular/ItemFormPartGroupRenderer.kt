/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template ItemFormPartGroupRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartGroupRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {${model.itemName}FormPartFieldName} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-field-name";
          |
          |export interface ${model.itemName}FormPartGroup {${ model.attributes.joinToString("") { attribute ->  """
              |    [${model.itemName}FormPartFieldName.${attribute.attributeName}]: FormControl<string>,
          """ } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-group.ts"
    }
}