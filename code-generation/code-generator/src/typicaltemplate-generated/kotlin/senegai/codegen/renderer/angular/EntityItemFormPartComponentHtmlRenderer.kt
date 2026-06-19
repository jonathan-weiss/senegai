/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel
import senegai.codegen.renderer.angular.EntityItemFormPartBlocksComponentHtmlRenderer

/**
 * Generate the content for the template `EntityItemFormPartComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-form-part.component.html`
 * - path: `opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part.component.html`
 */
object EntityItemFormPartComponentHtmlRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |<div [formGroup]="${model.item.itemName.camelCase}Form">
          |${ if(model.tabs.isNotEmpty()) { """
              |<mat-tab-group dynamicHeight>
              |${ model.tabs.joinToString("") { tab ->  """
                  |
                  |    <mat-tab label="${tab.tabName}">
                  |        <div class="column-layout">
                  |            ${ tab.columns.joinToString("") { column ->  """
                      |
                      |            <div class="column">
                      |                ${EntityItemFormPartBlocksComponentHtmlRenderer.renderTemplate(blocks = column.blocks, item = model.item, entity = model.entity)}
                      |
                      |                
                      |            </div>
                      |            
          """ } }
                  |            
                  |        </div>
                  |    </mat-tab>
                  |    
          """ } }
              |    
              |</mat-tab-group>
              |
          """ } else { """
          """ } }
          |
          |${ if(model.noTab.isNotEmpty()) { """
              |<div class="column-layout">
              |    ${ model.noTab.joinToString("") { column ->  """
                  |    <div class="column">
                  |            ${EntityItemFormPartBlocksComponentHtmlRenderer.renderTemplate(blocks = column.blocks, item = model.item, entity = model.entity)}
                  |    </div>
                  |    
          """ } }
              |</div>
              |
          """ } else { """
          """ } }
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part.component.html"
    }
}