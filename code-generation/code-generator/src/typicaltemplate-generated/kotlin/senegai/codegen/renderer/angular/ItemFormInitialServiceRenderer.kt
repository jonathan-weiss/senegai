/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormInitialServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemFormInitialServiceRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}FormInitialValueService {
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

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-initial-value.service.ts"
    }
}