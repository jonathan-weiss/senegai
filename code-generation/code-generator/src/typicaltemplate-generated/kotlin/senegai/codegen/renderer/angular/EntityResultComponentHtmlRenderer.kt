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
          |<div class="${model.entityName.kebabCase}-table-container">
          |    <table mat-table [dataSource]="dataSource">
          |        ${ model.searchResultAttributes.joinToString("") { attribute ->  """
              |        <!-- Firstname Column -->
              |        <ng-container matColumnDef="${attribute.attributeName.camelCase}">
              |            <th mat-header-cell *matHeaderCellDef>${attribute.attributeName.pascalCase}</th>
              |            <td mat-cell *matCellDef="let ${model.entityName.camelCase}">{{ ${model.entityName.camelCase}.${attribute.attributeName.camelCase} }}</td>
              |        </ng-container>
          """ } }
          |        <!-- Actions Column -->
          |        <ng-container matColumnDef="actions">
          |            <th mat-header-cell *matHeaderCellDef>Actions</th>
          |            <td mat-cell *matCellDef="let ${model.entityName.camelCase}">
          |                <button mat-icon-button color="primary" (click)="onEdit(${model.entityName.camelCase})">
          |                    <mat-icon>edit</mat-icon>
          |                </button>
          |                <button mat-icon-button color="warn" (click)="onDelete(${model.entityName.camelCase})">
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
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-result/${model.entityName.kebabCase}-result.component.html"
    }
}