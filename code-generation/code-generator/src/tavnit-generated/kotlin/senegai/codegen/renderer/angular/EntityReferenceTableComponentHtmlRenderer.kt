/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceTableComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-membrum-relatum-reference-table.component.html`
 * - path: `entitas-relata/entitas-relata-membrum-relatum-reference-table/entitas-relata-membrum-relatum-reference-table.component.html`
 */
object EntityReferenceTableComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<div class="table-container">
          |    <div class="action-bar">
          |        <app-${model.entityName.kebabCase}-typeahead
          |                label="Add ${model.entityName.pascalCase}..."
          |                [excluded${model.idAttribute.attributeName.pascalCase}List]="referenced${model.idAttribute.attributeName.pascalCase}List()"
          |                [disabled]="${model.entityRootItem.itemName.camelCase}ReferenceFormArray.disabled"
          |                (${model.entityRootItem.itemName.camelCase}Selected)="on${model.entityRootItem.itemName.pascalCase}Selected(${"$"}event)" />
          |    </div>
          |    <table mat-table [dataSource]="dataSource">
          |        <!-- Display attribute: ${model.idAttribute.attributeName.pascalCase} -->
          |        <ng-container matColumnDef="${model.idAttribute.attributeName.camelCase}">
          |            <th mat-header-cell *matHeaderCellDef>{{ columnHeader${model.idAttribute.attributeName.pascalCase} }}</th>
          |            <td mat-cell *matCellDef="let tableRow">{{ tableRow.${model.idAttribute.attributeName.camelCase} }}</td>
          |        </ng-container>
          |
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """        <!-- Display attribute: ${displayAttribute.attributeName.pascalCase} -->
              |        <ng-container matColumnDef="${displayAttribute.attributeName.camelCase}">
              |            <th mat-header-cell *matHeaderCellDef>{{ columnHeader${displayAttribute.attributeName.pascalCase} }}</th>
              |            <td mat-cell *matCellDef="let tableRow">{{ tableRow.${displayAttribute.attributeName.camelCase} }}</td>
              |        </ng-container>
              |""" } }
          |        <!-- Actions Column -->
          |        <ng-container matColumnDef="actions">
          |            <th mat-header-cell *matHeaderCellDef>Actions</th>
          |            <td mat-cell *matCellDef="let tableRow">
          |                <button mat-icon-button color="warn" (click)="onDelete(tableRow)" type="button"
          |                        [disabled]="${model.entityRootItem.itemName.camelCase}ReferenceFormArray.disabled">
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
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table.component.html"
    }
}