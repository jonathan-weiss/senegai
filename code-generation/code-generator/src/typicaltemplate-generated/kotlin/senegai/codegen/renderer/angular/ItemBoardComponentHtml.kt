/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemBoardComponentHtml filled up
 * with the content of the passed models.
 */
object ItemBoardComponentHtml {

    fun renderTemplate(model: ItemModel): String {
        return """
          |<div class="${model.itemNameLowercase}-container">
          |    <h2>${model.itemName}s</h2>
          |
          |    <mat-accordion class="${model.itemNameLowercase}-accordion" multi>
          |        <!-- Search Panel -->
          |        <mat-expansion-panel expanded>
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>search</mat-icon>
          |                    Search ${model.itemName}s
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
          |                    ${model.itemName} List
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
          |                    Edit ${model.itemName}
          |                </mat-panel-title>
          |                @if (selected${model.itemName}) {
          |                    <mat-panel-description>
          |                        {{ selected${model.itemName}.firstname }} {{ selected${model.itemName}.lastname }}
          |                    </mat-panel-description>
          |                }
          |            </mat-expansion-panel-header>
          |
          |            @if (selected${model.itemName}) {
          |                <app-${model.itemNameLowercase}-edit-form
          |                        [${model.itemNameLowercase}]="selected${model.itemName}"
          |                        (save)="onSave(${"$"}event)"
          |                        (cancel)="onCancel()">
          |                </app-${model.itemNameLowercase}-edit-form>
          |            }
          |        </mat-expansion-panel>
          |    </mat-accordion>
          |</div> 
          |
        """.trimMargin(marginPrefix = "|")
    }
}