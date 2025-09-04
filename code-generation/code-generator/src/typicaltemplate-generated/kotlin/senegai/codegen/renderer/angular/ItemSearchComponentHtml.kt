/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemSearchComponentHtml filled up
 * with the content of the passed models.
 */
object ItemSearchComponentHtml {

    fun renderTemplate(model: ItemModel): String {
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
          |
          |                <mat-form-field appearance="outline">
          |                    <mat-label>First Name</mat-label>
          |                    <input matInput formControlName="firstname" placeholder="Enter first name">
          |                </mat-form-field>
          |
          |                <mat-form-field appearance="outline">
          |                    <mat-label>Nickname</mat-label>
          |                    <input matInput formControlName="nickname" placeholder="Enter nickname">
          |                </mat-form-field>
          |
          |                <mat-form-field appearance="outline">
          |                    <mat-label>Last Name</mat-label>
          |                    <input matInput formControlName="lastname" placeholder="Enter last name">
          |                </mat-form-field>
          |            </div>
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
}