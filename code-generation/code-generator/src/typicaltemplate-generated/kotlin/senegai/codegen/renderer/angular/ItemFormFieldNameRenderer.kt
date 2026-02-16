/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormFieldNameRenderer filled up
 * with the content of the passed models.
 */
object ItemFormFieldNameRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |export enum ${model.itemName}FormFieldName {${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName} = "${attribute.attributeName}",
          """ } }}
          |
          |
          |export interface ${model.itemName}FormGroup {${ model.attributes.joinToString("") { attribute ->  """
              |    [${model.itemName}FormFieldName.${attribute.attributeName}]: FormControl<string>,
          """ } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-field-name.ts"
    }
}