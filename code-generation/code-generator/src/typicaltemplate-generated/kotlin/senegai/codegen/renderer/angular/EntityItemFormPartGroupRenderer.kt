/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartGroupRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form-part-group.ts`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-group.ts`
 */
object EntityItemFormPartGroupRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {${model.entity.entityName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.entity.entityName.kebabCase}-form-part/${model.entity.entityName.kebabCase}-form-part-field-name";
          |
          |
          |export interface ${model.entity.entityName.pascalCase}FormPartGroup {
          |    ${ model.item.attributes.joinToString("") { attribute ->  """
              |    [${model.entity.entityName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}]: ${attribute.angularFormControlType},${ if(attribute.isNullable) { """    [${model.entity.entityName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull]: FormControl<boolean>,
                  |    
          """ } else { """
          """ } }    
          """ } }    }
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.entity.entityName.kebabCase}-form-part/${model.entity.entityName.kebabCase}-form-part-group.ts"
    }
}