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
          |export class ${model.item.itemName.pascalCase}FormPartInitialValueService {
          |    
          |
          |${ model.item.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName.camelCase}InitialValue(): ${attribute.typescriptAttributeFormType} {
              |        return ''
              |    }
          """ } }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-initial-value.service.ts"
    }
}