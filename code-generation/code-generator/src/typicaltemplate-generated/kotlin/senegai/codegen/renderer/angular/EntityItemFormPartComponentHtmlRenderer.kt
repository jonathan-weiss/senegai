/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel
import senegai.codegen.renderer.angular.SingleFormInputHtmlTagRenderer

/**
 * Generate the content for the template `EntityItemFormPartComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form-part.component.html`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part.component.html`
 */
object EntityItemFormPartComponentHtmlRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |<div [formGroup]="${model.item.itemName.camelCase}Form">
          |
          |<mat-tab-group dynamicHeight>
          |${ model.tabs.joinToString("") { tab ->  """
              |
              |    <mat-tab label="${tab.tabName}">
              |        <div class="column-layout">
              |            ${ tab.columns.joinToString("") { column ->  """
                  |
                  |            <div class="column">
                  |                ${ column.blocks.joinToString("") { block ->  """
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
                              |                            ${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute, model = model)}
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
                              |                            ${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute, model = model)}
                              |                        </app-field-wrapper>
                              |                    </div>
          """ } else { """
          """ } }
                          |                ${ if(block.attribute.attributeCardinality == senegai.codegen.renderer.model.ui.AttributeCardinalityModel.LIST_ITEMS) { """
                              |
                              |                <div class="form-row">
                              |                    <app-field-wrapper label="${block.attribute.attributeName.pascalCase}">
                              |                        <app-${block.attribute.attributeName.kebabCase}-table
                              |                                [${block.attribute.attributeName.pascalCase}FormArray]="${block.attribute.attributeName.pascalCase}ListControl"
                              |                                (editArticulusInteriorFormGroup)="onArticulusInteriorListFormGroupEdit(${"$"}event)"
                              |                                (deleteArticulusInteriorFormGroup)="onArticulusInteriorListFormGroupDelete(${"$"}event)"
                              |                        />
                              |                        @if (${block.attribute.attributeName.pascalCase}ListFormGroupUnderEdit) {
                              |                            <div class="edit-area">
                              |                                <button mat-icon-button color="primary" (click)="closeArticulusInteriorListFormGroupUnderEdit()">
                              |                                    <mat-icon>edit_off</mat-icon>
                              |                                </button>
                              |                                ${SingleFormInputHtmlTagRenderer.renderTemplate(attributeModel = block.attribute, model = model)}
                              |                            </div>
                              |                        }
                              |                    </app-field-wrapper>
                              |                </div>
          """ } else { """
          """ } }
                          |                
          """ } else { """
          """ } }
          """ } }            </div>
                  |            
          """ } }                    </div>
              |    </mat-tab>
              |    
          """ } }    </mat-tab-group>
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part.component.html"
    }
}