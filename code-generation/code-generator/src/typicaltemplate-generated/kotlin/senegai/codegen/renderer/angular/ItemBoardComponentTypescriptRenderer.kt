/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template ItemBoardComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object ItemBoardComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component} from '@angular/core';
          |import {${model.entityName}SearchComponent, ${model.entityName}SearchCriteria} from '@app/${model.entityNameLowercase}/${model.entityNameLowercase}-search/${model.entityNameLowercase}-search.component';
          |import {${model.entityName}ResultComponent} from '@app/${model.entityNameLowercase}/${model.entityNameLowercase}-result/${model.entityNameLowercase}-result.component';
          |import {MatDialog, MatDialogModule} from '@angular/material/dialog';
          |import {${model.entityName}ConfirmDeleteDialogComponent} from '@app/${model.entityNameLowercase}/${model.entityNameLowercase}-confirm-delete-dialog/${model.entityNameLowercase}-confirm-delete-dialog.component';
          |import {${model.entityName}Service} from '@app/${model.entityNameLowercase}/${model.entityNameLowercase}.service';
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
          |import {${model.entityName}FormComponent} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form.component";
          |import {${model.entityName}} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}.model";
          |import {TranslocoPipe} from "@jsverse/transloco";
          |
          |@Component({
          |    selector: 'app-${model.entityNameLowercase}-board',
          |    templateUrl: './${model.entityNameLowercase}-board.component.html',
          |    styleUrls: ['./${model.entityNameLowercase}-board.component.scss'],
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
          |        ${model.entityName}SearchComponent,
          |        ${model.entityName}ResultComponent,
          |        ${model.entityName}FormComponent,
          |        TranslocoPipe,
          |    ]
          |})
          |export class ${model.entityName}BoardComponent {
          |    currentSearchCriteria: ${model.entityName}SearchCriteria = {};
          |    selected${model.entityName}: ${model.entityName} | null = null;
          |    refreshKey = 0;
          |
          |    constructor(private dialog: MatDialog, private ${model.entityNameLowercase}Service: ${model.entityName}Service) {
          |    }
          |
          |    onSearch(criteria: ${model.entityName}SearchCriteria): void {
          |        this.currentSearchCriteria = criteria;
          |    }
          |
          |    on${model.entityName}Select(${model.entityNameLowercase}: ${model.entityName}): void {
          |        this.selected${model.entityName} = ${model.entityNameLowercase};
          |    }
          |
          |    onDelete${model.entityName}(${model.entityNameLowercase}: ${model.entityName}): void {
          |        const dialogRef = this.dialog.open(${model.entityName}ConfirmDeleteDialogComponent, {
          |            data: {firstname: ${model.entityNameLowercase}.firstname, lastname: ${model.entityNameLowercase}.lastname}
          |        });
          |        dialogRef.afterClosed().subscribe(result => {
          |            if (result) {
          |                this.${model.entityNameLowercase}Service.delete${model.entityName}(${model.entityNameLowercase}.id).subscribe(() => {
          |                    this.refreshKey++;
          |                });
          |            }
          |        });
          |    }
          |
          |    onSave(updated${model.entityName}: ${model.entityName}): void {
          |        this.${model.entityNameLowercase}Service.update${model.entityName}(updated${model.entityName}).subscribe(() => {
          |            this.selected${model.entityName} = null;
          |            this.refreshKey++;
          |        });
          |    }
          |
          |    onCancel(): void {
          |        this.selected${model.entityName} = null;
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-board/${model.entityNameLowercase}-board.component.ts"
    }
}