/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemBoardComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemBoardComponentHtmlRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |<div class="${model.itemNameLowercase}-container">
          |    <h2 i18n>${model.itemName}s</h2>
          |
          |    <mat-accordion class="${model.itemNameLowercase}-accordion" multi>
          |        <!-- Search Panel -->
          |        <mat-expansion-panel>
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>search</mat-icon>
          |                    <ng-container i18n>Search ${model.itemName}s</ng-container>
          |                </mat-panel-title>
          |            </mat-expansion-panel-header>
          |            <app-${model.itemNameLowercase}-search (search)="onSearch(${"$"}event)"></app-${model.itemNameLowercase}-search>
          |        </mat-expansion-panel>
          |
          |        <!-- Results Panel -->
          |        <mat-expansion-panel expanded>
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>list</mat-icon>
          |                    <ng-container i18n>${model.itemName} List</ng-container>
          |                </mat-panel-title>
          |            </mat-expansion-panel-header>
          |            <app-${model.itemNameLowercase}-result
          |                    [searchCriteria]="currentSearchCriteria"
          |                    (select${model.itemName})="on${model.itemName}Select(${"$"}event)"
          |                    (delete${model.itemName})="onDelete${model.itemName}(${"$"}event)"
          |                    [refreshKey]="refreshKey">
          |            </app-${model.itemNameLowercase}-result>
          |        </mat-expansion-panel>
          |
          |        <!-- Edit Form Panel -->
          |        <mat-expansion-panel
          |                [expanded]="!!selected${model.itemName}"
          |                [disabled]="!selected${model.itemName}">
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>edit</mat-icon>
          |                    <ng-container i18n>Edit ${model.itemName}</ng-container>
          |                </mat-panel-title>
          |                @if (selected${model.itemName}) {
          |                    <mat-panel-description>
          |                        {{ selected${model.itemName}.firstname }} {{ selected${model.itemName}.lastname }}
          |                    </mat-panel-description>
          |                }
          |            </mat-expansion-panel-header>
          |
          |            @if (selected${model.itemName}) {
          |                <app-${model.itemNameLowercase}-form
          |                        [${model.itemNameLowercase}]="selected${model.itemName}"
          |                        (save)="onSave(${"$"}event)"
          |                        (cancel)="onCancel()">
          |                </app-${model.itemNameLowercase}-form>
          |            }
          |        </mat-expansion-panel>
          |    </mat-accordion>
          |</div> 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-board/${model.itemNameLowercase}-board.component.html"
    }
}