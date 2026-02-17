/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template ItemBoardComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemBoardComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<div class="${model.entityNameLowercase}-container">
          |    <h2>{{'${model.entityNameLowercase}.board.title' | transloco }}</h2>
          |
          |    <mat-accordion class="${model.entityNameLowercase}-accordion" multi>
          |        <!-- Search Panel -->
          |        <mat-expansion-panel>
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>search</mat-icon>
          |                    <ng-container>Search ${model.entityName}s</ng-container>
          |                </mat-panel-title>
          |            </mat-expansion-panel-header>
          |            <app-${model.entityNameLowercase}-search (search)="onSearch(${"$"}event)"></app-${model.entityNameLowercase}-search>
          |        </mat-expansion-panel>
          |
          |        <!-- Results Panel -->
          |        <mat-expansion-panel expanded>
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>list</mat-icon>
          |                    <ng-container>${model.entityName} List</ng-container>
          |                </mat-panel-title>
          |            </mat-expansion-panel-header>
          |            <app-${model.entityNameLowercase}-result
          |                    [searchCriteria]="currentSearchCriteria"
          |                    (select${model.entityName})="on${model.entityName}Select(${"$"}event)"
          |                    (delete${model.entityName})="onDelete${model.entityName}(${"$"}event)"
          |                    [refreshKey]="refreshKey">
          |            </app-${model.entityNameLowercase}-result>
          |        </mat-expansion-panel>
          |
          |        <!-- Edit Form Panel -->
          |        <mat-expansion-panel
          |                [expanded]="!!selected${model.entityName}"
          |                [disabled]="!selected${model.entityName}">
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>edit</mat-icon>
          |                    <ng-container>Edit ${model.entityName}</ng-container>
          |                </mat-panel-title>
          |                @if (selected${model.entityName}) {
          |                    <mat-panel-description>
          |                        {{ selected${model.entityName}.firstname }} {{ selected${model.entityName}.lastname }}
          |                    </mat-panel-description>
          |                }
          |            </mat-expansion-panel-header>
          |
          |            @if (selected${model.entityName}) {
          |                <app-${model.entityNameLowercase}-form
          |                        [${model.entityNameLowercase}]="selected${model.entityName}"
          |                        (save)="onSave(${"$"}event)"
          |                        (cancel)="onCancel()">
          |                </app-${model.entityNameLowercase}-form>
          |            }
          |        </mat-expansion-panel>
          |    </mat-accordion>
          |</div> 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-board/${model.entityNameLowercase}-board.component.html"
    }
}