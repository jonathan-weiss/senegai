/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityBoardComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntityBoardComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<div class="${model.entityName.kebabCase}-container">
          |    <h2>{{'${model.entityName.camelCase}.board.title' | transloco }}</h2>
          |
          |    <mat-accordion class="${model.entityName.kebabCase}-accordion" multi>
          |        <!-- Search Panel -->
          |        <mat-expansion-panel>
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>search</mat-icon>
          |                    <ng-container>Search ${model.entityName.pascalCase}s</ng-container>
          |                </mat-panel-title>
          |            </mat-expansion-panel-header>
          |            <app-${model.entityName.kebabCase}-search (search)="onSearch(${"$"}event)"></app-${model.entityName.kebabCase}-search>
          |        </mat-expansion-panel>
          |
          |        <!-- Results Panel -->
          |        <mat-expansion-panel expanded>
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>list</mat-icon>
          |                    <ng-container>${model.entityName.pascalCase} List</ng-container>
          |                </mat-panel-title>
          |            </mat-expansion-panel-header>
          |            <app-${model.entityName.kebabCase}-result
          |                    [searchCriteria]="currentSearchCriteria"
          |                    (select${model.entityName.pascalCase})="on${model.entityName.pascalCase}Select(${"$"}event)"
          |                    (delete${model.entityName.pascalCase})="onDelete${model.entityName.pascalCase}(${"$"}event)"
          |                    [refreshKey]="refreshKey">
          |            </app-${model.entityName.kebabCase}-result>
          |        </mat-expansion-panel>
          |
          |        <!-- Edit Form Panel -->
          |        <mat-expansion-panel
          |                [expanded]="!!selected${model.entityName.pascalCase}"
          |                [disabled]="!selected${model.entityName.pascalCase}">
          |            <mat-expansion-panel-header>
          |                <mat-panel-title>
          |                    <mat-icon>edit</mat-icon>
          |                    <ng-container>Edit ${model.entityName.pascalCase}</ng-container>
          |                </mat-panel-title>
          |                @if (selected${model.entityName.pascalCase}) {
          |                    <mat-panel-description>${ model.summaryAttributes.joinToString("") { attribute ->  """{{ selected${model.entityName.pascalCase}.${attribute.attributeName.camelCase} }} 
          """ } }
          |                    </mat-panel-description>
          |                }
          |            </mat-expansion-panel-header>
          |
          |            @if (selected${model.entityName.pascalCase}) {
          |                <app-${model.entityName.kebabCase}-form
          |                        [${model.entityName.camelCase}]="selected${model.entityName.pascalCase}"
          |                        (save)="onSave(${"$"}event)"
          |                        (cancel)="onCancel()">
          |                </app-${model.entityName.kebabCase}-form>
          |            }
          |        </mat-expansion-panel>
          |    </mat-accordion>
          |</div> 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-board/${model.entityName.kebabCase}-board.component.html"
    }
}