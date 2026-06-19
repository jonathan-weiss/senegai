/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartGroupRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-form-part-group.ts`
 * - path: `opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group.ts`
 */
object EntityItemFormPartGroupRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-field-name";
          |
          |
          |${ model.item.directlyNestedItems.joinToString("") { directlyNestedItem ->  """
              |
              |import {
              |    ${directlyNestedItem.itemName.pascalCase}FormPartGroup
              |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${directlyNestedItem.itemName.kebabCase}-form-part/${directlyNestedItem.itemName.kebabCase}-form-part-group";
              |
          """ } }
          |
          |
          |
          |export interface ${model.item.itemName.pascalCase}FormPartGroup {
          |    ${ model.item.attributes.joinToString("") { attribute ->  """
              |    [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}]: ${attribute.angularFormControlTypeWithCollection},
              |    ${ if(attribute.isNullable) { """
                  |    [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull]: FormControl<boolean>,
                  |    
          """ } else { """
          """ } }
              |    
          """ } }
          |    
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-group.ts"
    }
}