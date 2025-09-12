/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormPartComponentHtmlRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartComponentHtmlRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |<div [formGroup]="${model.itemNameLowercase}Form">
          |    <div class="form-row">
          |        <app-field-wrapper label="ID">
          |            <mat-form-field appearance="fill">
          |                <input matInput [formControl]="idControl" readonly>
          |                <mat-hint>ID cannot be modified</mat-hint>
          |            </mat-form-field>
          |        </app-field-wrapper>
          |    </div>
          |    <div class="form-row">
          |        <app-field-wrapper label="First name">
          |            <mat-form-field appearance="fill">
          |                <input matInput [formControl]="firstnameControl" placeholder="Enter first name">
          |                @if (hasError('firstname', 'required')) {
          |                    <mat-error>
          |                        First name is required
          |                    </mat-error>
          |                }
          |                @if (hasError('firstname', 'minlength')) {
          |                    <mat-error>
          |                        First name must be at least 2 characters
          |                    </mat-error>
          |                }
          |            </mat-form-field>
          |        </app-field-wrapper>
          |    </div>
          |
          |    <div class="form-row">
          |        <app-field-wrapper label="Nickname"
          |                           [nullabilityCheckboxFormControl]="nicknameIsNotNullControl"
          |                           [formGroupToDisableIfNullField]="nicknameControl"
          |        >
          |
          |            <mat-form-field appearance="fill">
          |                <input matInput [formControl]="nicknameControl" placeholder="Enter nickname">
          |            </mat-form-field>
          |        </app-field-wrapper>
          |    </div>
          |
          |    <div class="form-row">
          |        <app-field-wrapper label="Last name">
          |            <mat-form-field appearance="fill">
          |                <mat-label>Last Name</mat-label>
          |                <input matInput [formControl]="lastnameControl" placeholder="Enter last name">
          |                @if (hasError('lastname', 'required')) {
          |                    <mat-error>
          |                        Last name is required
          |                    </mat-error>
          |                }
          |                @if (hasError('lastname', 'minlength')) {
          |                    <mat-error>
          |                        Last name must be at least 2 characters
          |                    </mat-error>
          |                }
          |
          |            </mat-form-field>
          |        </app-field-wrapper>
          |    </div>
          |
          |</div>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component.html"
    }
}