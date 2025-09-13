/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemResultComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemResultComponentHtmlRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |<div class="${model.itemNameLowercase}-table-container">
          |    <table mat-table [dataSource]="dataSource">
          |        <!-- ID Column -->
          |        <ng-container matColumnDef="id">
          |            <th mat-header-cell *matHeaderCellDef>ID</th>
          |            <td mat-cell *matCellDef="let ${model.itemNameLowercase}">{{ ${model.itemNameLowercase}.id }}</td>
          |        </ng-container>
          |
          |        ${ model.attributes.joinToString("") { attribute ->  """
              |        <!-- ${attribute.attributeName} Column -->
              |        <ng-container matColumnDef="${attribute.attributeName}">
              |            <th mat-header-cell *matHeaderCellDef>${attribute.attributeName}</th>
              |            <td mat-cell *matCellDef="let ${model.itemNameLowercase}">{{ ${model.itemNameLowercase}.${attribute.attributeName} }}</td>
              |        </ng-container>
          """ } }
          |        <!-- Actions Column -->
          |        <ng-container matColumnDef="actions">
          |            <th mat-header-cell *matHeaderCellDef>Actions</th>
          |            <td mat-cell *matCellDef="let ${model.itemNameLowercase}">
          |                <button mat-icon-button color="primary" (click)="onEdit(${model.itemNameLowercase})">
          |                    <mat-icon>edit</mat-icon>
          |                </button>
          |                <button mat-icon-button color="warn" (click)="onDelete(${model.itemNameLowercase})">
          |                    <mat-icon>delete</mat-icon>
          |                </button>
          |            </td>
          |        </ng-container>
          |
          |        <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
          |        <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>
          |    </table>
          |</div> 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-result/${model.itemNameLowercase}-result.component.html"
    }
}