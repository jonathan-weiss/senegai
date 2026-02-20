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
          |<div [formGroup]="${model.item.itemNameLowercase}Form">
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
                          |                <div class="form-row">
                          |                    <app-field-wrapper label="${block.attributeName}">
                          |                        <app-text-input [textFormControl]="${block.attributeName}Control" label="${block.attributeName}" placeholder="Enter ${block.attributeName}" [validatorTranslations]="${block.attributeName}ValidatorNames" />
                          |                    </app-field-wrapper>
                          |                </div>
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
      return "${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part.component.html"
    }
}