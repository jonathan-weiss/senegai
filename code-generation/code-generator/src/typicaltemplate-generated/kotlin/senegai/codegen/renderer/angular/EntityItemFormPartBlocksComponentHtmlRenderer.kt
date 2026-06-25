/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormBlockModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.angular.SingleFormInputHtmlTagRenderer

/**
 * Generate the content for the template `EntityItemFormPartBlocksComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-silva-optionum-form-part.component.html`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-silva-optionum-form-part/opus-magnum-silva-optionum-form-part.component.html`
 */
object EntityItemFormPartBlocksComponentHtmlRenderer {

    fun renderTemplate(blocks: List<UiEntityFormBlockModel>, item: UiItemModel, entity: UiEntityModel): String {
        return """
          |
          |
          |${ blocks.joinToString("") { block ->  """${ if(block is senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel) { """                <app-section-splitter label="${block.sectionName}"></app-section-splitter>
                  |""" } else if(block is senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel) { """                <app-text-block label="${block.text}" />
                  |""" } else if(block is senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormItemAttributeBlockModel) { """${ if((block.attribute.isBuiltIn || block.attribute.isEnum || !block.attribute.isList) && !block.attribute.isNullable) { """
                      |                    <div class="form-row">
                      |                        <app-field-wrapper label="${block.attribute.attributeName.pascalCase}">
                      |${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute)}                        </app-field-wrapper>
                      |                    </div>
                      |""" } else { """""" } }${ if((block.attribute.isBuiltIn || block.attribute.isEnum || !block.attribute.isList) && block.attribute.isNullable) { """
                      |                    <div class="form-row">
                      |                        <app-field-wrapper label="${block.attribute.attributeName.pascalCase}"
                      |                                            [nullabilityCheckboxFormControl]="${block.attribute.attributeName.camelCase}IsNotNullControl"
                      |                                            [formGroupToDisableIfNullField]="${block.attribute.attributeName.camelCase}Control"
                      |                         >
                      |${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute)}                        </app-field-wrapper>
                      |                    </div>
                      |""" } else { """""" } }
                  |${ if(block.attribute is senegai.codegen.renderer.model.ui.ItemUiIAttributeModel && block.attribute.isList && !block.attribute.isNullable) { """
                      |                <div class="form-row">
                      |                    <app-field-wrapper label="${block.attribute.attributeName.pascalCase}">
                      |                        <app-${block.attribute.referencedEntity.entityName.kebabCase}-${block.attribute.referencedItem.itemName.kebabCase}-table
                      |                                [${block.attribute.referencedItem.itemName.camelCase}FormArray]="${block.attribute.attributeName.camelCase}Control"
                      |                                (edit${block.attribute.referencedItem.itemName.pascalCase}FormGroup)="${block.attribute.attributeName.camelCase}EditState.onEdit(${"$"}event)"
                      |                                (delete${block.attribute.referencedItem.itemName.pascalCase}FormGroup)="${block.attribute.attributeName.camelCase}EditState.onDelete(${"$"}event)"
                      |                        />
                      |                        @if (${block.attribute.attributeName.camelCase}EditState.formGroupUnderEdit) {
                      |                            <div class="edit-area">
                      |                                <button mat-icon-button color="primary" (click)="${block.attribute.attributeName.camelCase}EditState.close()">
                      |                                    <mat-icon>edit_off</mat-icon>
                      |                                </button>
                      |${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute, isList = true)}                            </div>
                      |                        }
                      |                    </app-field-wrapper>
                      |
                      |                </div>
                      |""" } else { """""" } }${ if(block.attribute is senegai.codegen.renderer.model.ui.ItemUiIAttributeModel && block.attribute.isList && block.attribute.isNullable) { """
                      |                <div class="form-row">
                      |                    <app-field-wrapper label="${block.attribute.attributeName.pascalCase}"
                      |                                       [nullabilityCheckboxFormControl]="${block.attribute.attributeName.camelCase}IsNotNullControl"
                      |                                       [formGroupToDisableIfNullField]="${block.attribute.attributeName.camelCase}Control"
                      |                    >
                      |                        <app-${block.attribute.referencedEntity.entityName.kebabCase}-${block.attribute.referencedItem.itemName.kebabCase}-table
                      |                                [${block.attribute.referencedItem.itemName.camelCase}FormArray]="${block.attribute.attributeName.camelCase}Control"
                      |                                (edit${block.attribute.referencedItem.itemName.pascalCase}FormGroup)="${block.attribute.attributeName.camelCase}EditState.onEdit(${"$"}event)"
                      |                                (delete${block.attribute.referencedItem.itemName.pascalCase}FormGroup)="${block.attribute.attributeName.camelCase}EditState.onDelete(${"$"}event)"
                      |                        />
                      |                        @if (${block.attribute.attributeName.camelCase}EditState.formGroupUnderEdit) {
                      |                            <div class="edit-area">
                      |                                <button mat-icon-button color="primary" (click)="${block.attribute.attributeName.camelCase}EditState.close()">
                      |                                    <mat-icon>edit_off</mat-icon>
                      |                                </button>
                      |${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute, isList = true)}                            </div>
                      |                        }
                      |                    </app-field-wrapper>
                      |
                      |                </div>
                      |""" } else { """""" } }
                  |""" } else { """""" } }""" } }
        """.trimMargin(marginPrefix = "|")
    }

    fun filePath(blocks: List<UiEntityFormBlockModel>, item: UiItemModel, entity: UiEntityModel): String {
      return "opus-magnum/opus-magnum-form/opus-magnum-silva-optionum-form-part/opus-magnum-silva-optionum-form-part.component.html"
    }
}