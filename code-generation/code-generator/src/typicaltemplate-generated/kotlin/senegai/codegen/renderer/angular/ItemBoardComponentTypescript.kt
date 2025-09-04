/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemBoardComponentTypescript filled up
 * with the content of the passed models.
 */
object ItemBoardComponentTypescript {

    fun renderTemplate(model: ItemModel): String {
        return """
          |import {Component} from '@angular/core';
          |import {${model.itemName}SearchComponent, ${model.itemName}SearchCriteria} from '@app/${model.itemNameLowercase}/${model.itemNameLowercase}-search/${model.itemNameLowercase}-search.component';
          |import {${model.itemName}ResultComponent} from '@app/${model.itemNameLowercase}/${model.itemNameLowercase}-result/${model.itemNameLowercase}-result.component';
          |import {MatDialog, MatDialogModule} from '@angular/material/dialog';
          |import {${model.itemName}ConfirmDeleteDialogComponent} from '@app/${model.itemNameLowercase}/${model.itemNameLowercase}-confirm-delete-dialog/${model.itemNameLowercase}-confirm-delete-dialog.component';
          |import {${model.itemName}Service} from '@app/${model.itemNameLowercase}/${model.itemNameLowercase}.service';
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
          |import {${model.itemName}EditFormComponent} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-edit-form/${model.itemNameLowercase}-edit-form.component";
          |import {${model.itemName}} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}.model";
          |
          |@Component({
          |    selector: 'app-${model.itemNameLowercase}-board',
          |    templateUrl: './${model.itemNameLowercase}-board.component.html',
          |    styleUrls: ['./${model.itemNameLowercase}-board.component.scss'],
          |    standalone: true,
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
          |        ${model.itemName}SearchComponent,
          |        ${model.itemName}ResultComponent,
          |        ${model.itemName}EditFormComponent,
          |    ],
          |})
          |export class ${model.itemName}BoardComponent {
          |    currentSearchCriteria: ${model.itemName}SearchCriteria = {};
          |    selected${model.itemName}: ${model.itemName} | null = null;
          |    refreshKey = 0;
          |
          |    constructor(private dialog: MatDialog, private ${model.itemNameLowercase}Service: ${model.itemName}Service) {
          |    }
          |
          |    onSearch(criteria: ${model.itemName}SearchCriteria): void {
          |        this.currentSearchCriteria = criteria;
          |    }
          |
          |    on${model.itemName}Select(${model.itemNameLowercase}: ${model.itemName}): void {
          |        this.selected${model.itemName} = ${model.itemNameLowercase};
          |    }
          |
          |    onDelete${model.itemName}(${model.itemNameLowercase}: ${model.itemName}): void {
          |        const dialogRef = this.dialog.open(${model.itemName}ConfirmDeleteDialogComponent, {
          |            data: {firstname: ${model.itemNameLowercase}.firstname, lastname: ${model.itemNameLowercase}.lastname}
          |        });
          |        dialogRef.afterClosed().subscribe(result => {
          |            if (result) {
          |                this.${model.itemNameLowercase}Service.delete${model.itemName}(${model.itemNameLowercase}.id).subscribe(() => {
          |                    this.refreshKey++;
          |                });
          |            }
          |        });
          |    }
          |
          |    onSave(updated${model.itemName}: ${model.itemName}): void {
          |        this.${model.itemNameLowercase}Service.update${model.itemName}(updated${model.itemName}).subscribe(() => {
          |            this.selected${model.itemName} = null;
          |            this.refreshKey++;
          |        });
          |    }
          |
          |    onCancel(): void {
          |        this.selected${model.itemName} = null;
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }
}