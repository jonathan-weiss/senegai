/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityConfirmDeleteDialogComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object EntityConfirmDeleteDialogComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component, Inject} from '@angular/core';
          |import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
          |import {ReactiveFormsModule} from "@angular/forms";
          |import {MatButtonModule} from "@angular/material/button";
          |import {MatToolbarModule} from "@angular/material/toolbar";
          |import {MatTableModule} from "@angular/material/table";
          |import {MatCardModule} from "@angular/material/card";
          |import {MatFormFieldModule} from "@angular/material/form-field";
          |import {MatInputModule} from "@angular/material/input";
          |import {MatIconModule} from "@angular/material/icon";
          |import {MatExpansionModule} from "@angular/material/expansion";
          |import {MatSidenavModule} from "@angular/material/sidenav";
          |import {MatListModule} from "@angular/material/list";
          |
          |@Component({
          |    selector: 'app-${model.entityNameDashCase}-confirm-delete-dialog',
          |    templateUrl: './${model.entityNameDashCase}-confirm-delete-dialog.component.html',
          |    styleUrls: ['./${model.entityNameDashCase}-confirm-delete-dialog.component.scss'],
          |    imports: [
          |        ReactiveFormsModule,
          |        MatButtonModule,
          |        MatToolbarModule,
          |        MatTableModule,
          |        MatCardModule,
          |        MatFormFieldModule,
          |        MatInputModule,
          |        MatIconModule,
          |        MatExpansionModule,
          |        MatSidenavModule,
          |        MatListModule,
          |        MatDialogModule,
          |    ]
          |})
          |export class ${model.entityName}ConfirmDeleteDialogComponent {
          |    constructor(
          |        public dialogRef: MatDialogRef<${model.entityName}ConfirmDeleteDialogComponent>,
          |        @Inject(MAT_DIALOG_DATA) public data: { firstname: string; lastname: string }
          |    ) {
          |    }
          |
          |    onCancel(): void {
          |        this.dialogRef.close(false);
          |    }
          |
          |    onConfirm(): void {
          |        this.dialogRef.close(true);
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-confirm-delete-dialog/${model.entityNameDashCase}-confirm-delete-dialog.component.ts"
    }
}