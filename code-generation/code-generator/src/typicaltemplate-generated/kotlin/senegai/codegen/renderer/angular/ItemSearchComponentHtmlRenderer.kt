/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemSearchComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemSearchComponentHtmlRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |<mat-card class="search-card">
          |    <mat-card-header>
          |        <mat-card-title>Search ${model.itemName}s</mat-card-title>
          |    </mat-card-header>
          |    <mat-card-content>
          |        <form [formGroup]="searchForm" (ngSubmit)="onSubmit()">
          |            <div class="search-fields">
          |                <mat-form-field appearance="outline">
          |                    <mat-label>ID</mat-label>
          |                    <input matInput type="number" formControlName="id" placeholder="Enter ID">
          |                </mat-form-field>
          |                ${ model.attributes.joinToString("") { attribute ->  """
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

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-search/${model.itemNameLowercase}-search.component.html"
    }
}