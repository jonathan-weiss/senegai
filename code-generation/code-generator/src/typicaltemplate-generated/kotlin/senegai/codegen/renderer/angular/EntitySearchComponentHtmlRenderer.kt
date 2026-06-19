/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntitySearchComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-search.component.html`
 * - path: `opus-magnum/opus-magnum-search/opus-magnum-search.component.html`
 */
object EntitySearchComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |        <form [formGroup]="searchForm" (ngSubmit)="onSubmit()">
          |            <div class="search-fields">
          |                <mat-form-field>
          |                    <mat-label>Search</mat-label>
          |                    <input matInput [formControl]="searchQueryControl" placeholder="Enter search query for ${model.entityName.pascalCase}">
          |                </mat-form-field>
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
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-search/${model.entityName.kebabCase}-search.component.html"
    }
}