/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemEditFormComponentHtml filled up
 * with the content of the passed models.
 */
object ItemEditFormComponentHtml {

    fun renderTemplate(model: ItemModel): String {
        return """
          |<div class="edit-form-container">
          |    <mat-card>
          |        <mat-card-header>
          |            <mat-card-title>{{ ${model.itemNameLowercase} ? 'Edit ${model.itemName}' : 'New ${model.itemName}' }}</mat-card-title>
          |        </mat-card-header>
          |
          |        <mat-card-content>
          |            <form [formGroup]="${model.itemNameLowercase}Form" (ngSubmit)="onSubmit()">
          |                <div class="form-row">
          |                    <mat-form-field appearance="outline">
          |                        <mat-label>ID</mat-label>
          |                        <input matInput formControlName="id" readonly>
          |                        <mat-hint>ID cannot be modified</mat-hint>
          |                    </mat-form-field>
          |                </div>
          |
          |                <div class="form-row">
          |                    <mat-form-field appearance="outline">
          |                        <mat-label>First Name</mat-label>
          |                        <input matInput formControlName="firstname" placeholder="Enter first name">
          |                        @if (hasError('firstname', 'required')) {
          |                            <mat-error>
          |                                First name is required
          |                            </mat-error>
          |                        }
          |                        @if (hasError('firstname', 'minlength')) {
          |                            <mat-error>
          |                                First name must be at least 2 characters
          |                            </mat-error>
          |                        }
          |                    </mat-form-field>
          |                </div>
          |
          |                <div class="form-row">
          |                    <mat-form-field appearance="outline">
          |                        <mat-label>Nickname</mat-label>
          |                        <input matInput formControlName="nickname" placeholder="Enter nickname">
          |                    </mat-form-field>
          |                </div>
          |
          |                <div class="form-row">
          |                    <mat-form-field appearance="outline">
          |                        <mat-label>Last Name</mat-label>
          |                        <input matInput formControlName="lastname" placeholder="Enter last name">
          |                        @if (hasError('lastname', 'required')) {
          |                            <mat-error>
          |                                Last name is required
          |                            </mat-error>
          |                        }
          |                        @if (hasError('lastname', 'minlength')) {
          |                            <mat-error>
          |                                Last name must be at least 2 characters
          |                            </mat-error>
          |                        }
          |
          |                    </mat-form-field>
          |                </div>
          |
          |                <div class="form-actions">
          |                    <button mat-button type="button" (click)="onCancel()">
          |                        Cancel
          |                    </button>
          |                    <button mat-raised-button color="primary" type="submit" [disabled]="!${model.itemNameLowercase}Form.valid">
          |                        Save
          |                    </button>
          |                </div>
          |            </form>
          |        </mat-card-content>
          |    </mat-card>
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }
}