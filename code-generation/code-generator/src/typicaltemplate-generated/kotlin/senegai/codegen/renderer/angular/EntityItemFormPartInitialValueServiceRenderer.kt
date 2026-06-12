/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartInitialValueServiceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form-part-initial-value.service.ts`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-initial-value.service.ts`
 */
object EntityItemFormPartInitialValueServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entity.entityName.pascalCase}FormPartInitialValueService {
          |    ${ model.item.attributes.joinToString("") { attribute ->  """
              |    ${attribute.attributeName.camelCase}InitialValue(): ${attribute.typescriptAttributeFormType} {
              |        return ${attribute.formInitialValue}
              |    }
          """ } }    
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.entity.entityName.kebabCase}-form-part/${model.entity.entityName.kebabCase}-form-part-initial-value.service.ts"
    }
}