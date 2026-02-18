/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityResultComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntityResultComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<div class="${model.entityNameDashCase}-table-container">
          |    <table mat-table [dataSource]="dataSource">
          |        ${ model.searchResultAttributes.joinToString("") { attribute ->  """
              |        <!-- Firstname Column -->
              |        <ng-container matColumnDef="${attribute.attributeName}">
              |            <th mat-header-cell *matHeaderCellDef>${attribute.attributeName}</th>
              |            <td mat-cell *matCellDef="let ${model.entityNameLowercase}">{{ ${model.entityNameLowercase}.${attribute.attributeName} }}</td>
              |        </ng-container>
          """ } }
          |        <!-- Actions Column -->
          |        <ng-container matColumnDef="actions">
          |            <th mat-header-cell *matHeaderCellDef>Actions</th>
          |            <td mat-cell *matCellDef="let ${model.entityNameLowercase}">
          |                <button mat-icon-button color="primary" (click)="onEdit(${model.entityNameLowercase})">
          |                    <mat-icon>edit</mat-icon>
          |                </button>
          |                <button mat-icon-button color="warn" (click)="onDelete(${model.entityNameLowercase})">
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

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-result/${model.entityNameDashCase}-result.component.html"
    }
}