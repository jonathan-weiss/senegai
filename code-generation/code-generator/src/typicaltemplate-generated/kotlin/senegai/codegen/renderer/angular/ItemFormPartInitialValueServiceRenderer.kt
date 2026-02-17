/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template ItemFormPartInitialValueServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartInitialValueServiceRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}FormPartInitialValueService {
          |    
          |
          |${ model.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName}InitialValue(): string {
              |        return ''
              |    }
          """ } }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "opus-magnum/opus-magnum-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-initial-value.service.ts"
    }
}