/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityFormFieldNameRenderer filled up
 * with the content of the passed models.
 */
object EntityFormFieldNameRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |export enum ${model.entityName}FormFieldName {${ model.chainedFormAttributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName} = "${attribute.attributeName}",
          """ } }}
          |
          |
          |export interface ${model.entityName}FormGroup {${ model.chainedFormAttributes.joinToString("") { attribute ->  """
              |    [${model.entityName}FormFieldName.${attribute.attributeName}]: FormControl<string>,
          """ } }}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form-field-name.ts"
    }
}