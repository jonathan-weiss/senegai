/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityFormInitialServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityFormInitialServiceRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName}FormInitialValueService {
          |    
          |
          |${ model.chainedFormAttributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName}InitialValue(): string {
              |        return ''
              |    }
          """ } }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form-initial-value.service.ts"
    }
}