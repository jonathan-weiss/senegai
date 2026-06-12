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
 * - file: `silva-optionum-form-part.component.html`
 * - path: `opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part.component.html`
 */
object EntityItemFormPartBlocksComponentHtmlRenderer {

    fun renderTemplate(blocks: List<UiEntityFormBlockModel>, item: UiItemModel, entity: UiEntityModel): String {
        return """
          |
          |
          |                ${ blocks.joinToString("") { block ->  """
              |                <!-- p>${block}</p -->
              |                ${ if(block is senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormNamedSectionSplitBlockModel) { """
                  |                <app-section-splitter label="${block.sectionName}"></app-section-splitter>
                  |                
          """ } else if(block is senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormTextBlockModel) { """
                  |                <app-text-block label="${block.text}" />
                  |                
          """ } else if(block is senegai.codegen.renderer.model.ui.entityform.blocks.UiEntityFormItemAttributeBlockModel) { """
                  |                    ${ if(block.attribute.attributeCardinality == senegai.codegen.renderer.model.ui.AttributeCardinalityModel.SINGLE_ITEM) { """
                      |
                      |                    <div class="form-row">
                      |                        <app-field-wrapper label="${block.attribute.attributeName.pascalCase}">
                      |                            ${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute)}
                      |                        </app-field-wrapper>
                      |                    </div>
          """ } else { """
          """ } }                    ${ if(block.attribute.attributeCardinality == senegai.codegen.renderer.model.ui.AttributeCardinalityModel.NULLABLE_SINGLE_ITEM) { """
                      |
                      |                    <div class="form-row">
                      |                        <app-field-wrapper label="${block.attribute.attributeName.pascalCase}"
                      |                                            [nullabilityCheckboxFormControl]="${block.attribute.attributeName.camelCase}IsNotNullControl"
                      |                                            [formGroupToDisableIfNullField]="${block.attribute.attributeName.camelCase}Control"
                      |                         >
                      |                            ${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute)}
                      |                        </app-field-wrapper>
                      |                    </div>
          """ } else { """
          """ } }
                  |                ${ if(block.attribute.isItem && (block.attribute.attributeCardinality == senegai.codegen.renderer.model.ui.AttributeCardinalityModel.LIST_ITEMS)) { """
                      |
                      |                <div class="form-row">
                      |                    <app-field-wrapper label="${block.attribute.attributeName.pascalCase}">
                      |                        <app-${block.attribute.attributeAndItem.type.item.itemName.kebabCase}-table
                      |                                [${block.attribute.attributeAndItem.type.item.itemName.camelCase}FormArray]="${block.attribute.attributeName.camelCase}Control"
                      |                                (edit${block.attribute.attributeAndItem.type.item.itemName.pascalCase}FormGroup)="on${block.attribute.attributeName.pascalCase}FormGroupEdit(${"$"}event)"
                      |                                (delete${block.attribute.attributeAndItem.type.item.itemName.pascalCase}FormGroup)="on${block.attribute.attributeName.pascalCase}FormGroupDelete(${"$"}event)"
                      |                        />
                      |                        @if (${block.attribute.attributeName.camelCase}FormGroupUnderEdit) {
                      |                            <div class="edit-area">
                      |                                <button mat-icon-button color="primary" (click)="close${block.attribute.attributeName.pascalCase}FormGroupUnderEdit()">
                      |                                    <mat-icon>edit_off</mat-icon>
                      |                                </button>
                      |                                ${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute, isList = true)}
                      |                            </div>
                      |                        }
                      |                    </app-field-wrapper>
                      |                </div>
          """ } else { """
          """ } }
                  |                
          """ } else { """
          """ } }
          """ } }                
        """.trimMargin(marginPrefix = "|")
    }

    fun filePath(blocks: List<UiEntityFormBlockModel>, item: UiItemModel, entity: UiEntityModel): String {
      return "opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part.component.html"
    }
}