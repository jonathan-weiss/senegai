/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemTableComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntityItemTableComponentHtmlRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |<div class="table-container">
          |    <div class="action-bar">
          |        <button mat-button color="primary" (click)="onAdd()">
          |            <mat-icon>add</mat-icon> Add ${model.item.itemName.pascalCase}...
          |        </button>
          |
          |    </div>
          |    <table mat-table [dataSource]="dataSource">
          |        ${ model.item.attributes.joinToString("") { attribute ->  """
              |
              |        <!-- ${attribute.attributeName.pascalCase} Column -->
              |        <ng-container matColumnDef="${attribute.attributeName.camelCase}">
              |            <th mat-header-cell *matHeaderCellDef>${attribute.attributeName.pascalCase}</th>
              |            <td mat-cell *matCellDef="let tableRow" (click)="onSelect(tableRow.formGroup)">
              |                {{ tableRow.${attribute.attributeName.camelCase} }}
              |            </td>
              |        </ng-container>
          """ } }        
          |
          |        <!-- Actions Column -->
          |        <ng-container matColumnDef="actions">
          |            <th mat-header-cell *matHeaderCellDef>Actions</th>
          |            <td mat-cell *matCellDef="let tableRow">
          |                <button mat-icon-button color="primary" (click)="onEdit(tableRow.formGroup)">
          |                    <mat-icon>edit</mat-icon>
          |                </button>
          |                <button mat-icon-button color="warn" (click)="onDelete(tableRow.formGroup)">
          |                    <mat-icon>delete</mat-icon>
          |                </button>
          |            </td>
          |        </ng-container>
          |
          |        <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
          |        <tr mat-row *matRowDef="let row; columns: displayedColumns;" [class.selectedRow] = "isSelected(row)"></tr>
          |    </table>
          |</div> 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-table/${model.item.itemName.kebabCase}-table.component.html"
    }
}