/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartInitialValueServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartInitialValueServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.item.itemName}FormPartInitialValueService {
          |    
          |
          |${ model.item.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName}InitialValue(): string {
              |        return ''
              |    }
          """ } }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-initial-value.service.ts"
    }
}