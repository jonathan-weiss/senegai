/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartComponentHtmlRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |<div [formGroup]="${model.item.itemName.camelCase}Form">
          |
          |<div>
          |    <div class="form-row">
          |        <app-field-wrapper label="ID">
          |            <mat-form-field appearance="fill">
          |                <input matInput [formControl]="idControl" readonly>
          |                <mat-hint>ID cannot be modified</mat-hint>
          |            </mat-form-field>
          |        </app-field-wrapper>
          |    </div>
          |</div>
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
                              |
                              |                            <app-text-input [textFormControl]="${block.attribute.attributeName.camelCase}Control" label="${block.attribute.attributeName.camelCase}" placeholder="Enter ${block.attribute.attributeName.pascalCase}" [validatorTranslations]="${block.attribute.attributeName.camelCase}ValidatorNames" />
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
                              |
                              |                            <app-text-input [textFormControl]="${block.attribute.attributeName.camelCase}Control" label="${block.attribute.attributeName.camelCase}" placeholder="Enter ${block.attribute.attributeName.camelCase}" [validatorTranslations]="${block.attribute.attributeName.camelCase}ValidatorNames" />
                              |                        </app-field-wrapper>
                              |                    </div>
          """ } else { """
          """ } }
                          |                ${ if(block.attribute.attributeCardinality == senegai.codegen.renderer.model.ui.AttributeCardinalityModel.LIST_ITEMS) { """
                              |
                              |                <div class="form-row">
                              |                    <app-field-wrapper label="${block.attribute.attributeName.pascalCase}">
                              |                        <app-${block.attribute.attributeName.kebabCase}-table
                              |                                [${block.attribute.attributeName.pascalCase}FormArray]="${block.attribute.attributeName.pascalCase}ListFormArray"
                              |                                (editLibraryAwardFormGroup)="on${model.item.itemName.pascalCase}LibraryAwardFormGroupEdit(${"$"}event)"
                              |                                (deleteLibraryAwardFormGroup)="on${model.item.itemName.pascalCase}LibraryAwardFormGroupDelete(${"$"}event)"
                              |                        />
                              |                        @if (${model.item.itemName.camelCase}LibraryAwardUnderEdit) {
                              |                            <div class="edit-area">
                              |                                <button mat-icon-button color="primary" (click)="close${model.item.itemName.pascalCase}LibraryAwardUnderEdit()">
                              |                                    <mat-icon>edit_off</mat-icon>
                              |                                </button>
                              |                                <app-${block.attribute.attributeName.kebabCase}-form-part [${block.attribute.attributeName.pascalCase}Form]="${model.item.itemName.camelCase}LibraryAwardUnderEdit!"  />
                              |                            </div>
                              |                        }
                              |                    </app-field-wrapper>
                              |                </div>
          """ } else { """
          """ } }
          """ } else { """
          """ } }
          """ } }                            </div>
          """ } }        </div>
              |    </mat-tab>
          """ } }</mat-tab-group>
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part.component.html"
    }
}