/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntitySearchComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object EntitySearchComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<mat-card class="search-card">
          |    <mat-card-header>
          |        <mat-card-title>Search ${model.entityName}s</mat-card-title>
          |    </mat-card-header>
          |    <mat-card-content>
          |        <form [formGroup]="searchForm" (ngSubmit)="onSubmit()">
          |            <div class="search-fields">
          |                ${ model.searchCriteriaAttributes.joinToString("") { attribute ->  """
              |                <mat-form-field appearance="outline">
              |                    <mat-label>${attribute.attributeName}</mat-label>
              |                    <input matInput formControlName="${attribute.attributeName}" placeholder="Enter ${attribute.attributeName}">
              |                </mat-form-field>
          """ } }            </div>
          |
          |            <div class="search-actions">
          |                <button mat-raised-button color="primary" type="submit">
          |                    <mat-icon>search</mat-icon>
          |                    Search
          |                </button>
          |                <button mat-button type="button" (click)="onReset()">
          |                    <mat-icon>clear</mat-icon>
          |                    Reset
          |                </button>
          |            </div>
          |        </form>
          |    </mat-card-content>
          |</mat-card> 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "opus-magnum/opus-magnum-search/opus-magnum-search.component.html"
    }
}