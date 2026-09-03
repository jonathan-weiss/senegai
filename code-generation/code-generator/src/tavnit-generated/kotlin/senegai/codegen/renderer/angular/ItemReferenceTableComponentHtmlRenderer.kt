/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemReferenceTableComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `membrum-relatum-reference-table.component.html`
 * - path: `reference/membrum-relatum-reference-table/membrum-relatum-reference-table.component.html`
 */
object ItemReferenceTableComponentHtmlRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |<div class="table-container">
          |    <div class="action-bar">
          |        <app-${model.itemName.kebabCase}-typeahead
          |                [label]="'${model.itemName.camelCase}.reference.addEntry' | transloco"
          |                [excluded${model.primaryKeyAttribute.attributeName.pascalCase}List]="referenced${model.primaryKeyAttribute.attributeName.pascalCase}List()"
          |                [disabled]="${model.itemName.camelCase}ReferenceFormArray.disabled"
          |                (${model.itemName.camelCase}Selected)="on${model.itemName.pascalCase}Selected(${"$"}event)" />
          |    </div>
          |    <table mat-table [dataSource]="dataSource">
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """        <!-- Display attribute: ${displayAttribute.attributeName.pascalCase} -->
              |        <ng-container matColumnDef="${displayAttribute.attributeName.camelCase}">
              |            <th mat-header-cell *matHeaderCellDef>{{ columnHeader${displayAttribute.attributeName.pascalCase}TranslationKey | transloco }}</th>
              |            <td mat-cell *matCellDef="let tableRow">{{ tableRow.${displayAttribute.attributeName.camelCase} }}</td>
              |        </ng-container>
              |""" } }
          |        <!-- Actions Column -->
          |        <ng-container matColumnDef="actions">
          |            <th mat-header-cell *matHeaderCellDef>{{ 'table.column.actions' | transloco }}</th>
          |            <td mat-cell *matCellDef="let tableRow">
          |                <button mat-icon-button color="warn" (click)="onDelete(tableRow)" type="button"
          |                        [disabled]="${model.itemName.camelCase}ReferenceFormArray.disabled">
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

    override fun filePath(model: UiItemModel): String {
      return "reference/${model.itemName.kebabCase}-reference-table/${model.itemName.kebabCase}-reference-table.component.html"
    }
}