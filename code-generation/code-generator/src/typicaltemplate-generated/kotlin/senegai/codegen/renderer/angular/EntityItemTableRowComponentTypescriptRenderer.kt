/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemTableRowComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object EntityItemTableRowComponentTypescriptRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {FormGroup} from "@angular/forms";
          |import {
          |    ${model.item.itemName.pascalCase}FormPartGroup
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-group";
          |
          |export interface ${model.item.itemName.pascalCase}TableRow {${ model.item.attributes.joinToString("") { attribute ->  """    ${attribute.attributeName.camelCase}: string
          """ } }    formGroup: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-table/${model.item.itemName.kebabCase}-table-row.model.ts"
    }
}