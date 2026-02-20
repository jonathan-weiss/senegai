/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityBoardComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object EntityBoardComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component} from '@angular/core';
          |import {${model.entityName.pascalCase}SearchComponent, ${model.entityName.pascalCase}SearchCriteria} from '@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-search/${model.entityName.kebabCase}-search.component';
          |import {${model.entityName.pascalCase}ResultComponent} from '@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-result/${model.entityName.kebabCase}-result.component';
          |import {MatDialog, MatDialogModule} from '@angular/material/dialog';
          |import {${model.entityName.pascalCase}ConfirmDeleteDialogComponent} from '@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-confirm-delete-dialog/${model.entityName.kebabCase}-confirm-delete-dialog.component';
          |import {${model.entityName.pascalCase}Service} from '@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}.service';
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
          |import {${model.entityName.pascalCase}FormComponent} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form.component";
          |import {${model.entityName.pascalCase}WTO} from "@app/wto/${model.entityName.kebabCase}.wto";
          |import {TranslocoPipe} from "@jsverse/transloco";
          |
          |@Component({
          |    selector: 'app-${model.entityName.kebabCase}-board',
          |    templateUrl: './${model.entityName.kebabCase}-board.component.html',
          |    styleUrls: ['./${model.entityName.kebabCase}-board.component.scss'],
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
          |        ${model.entityName.pascalCase}SearchComponent,
          |        ${model.entityName.pascalCase}ResultComponent,
          |        ${model.entityName.pascalCase}FormComponent,
          |        TranslocoPipe,
          |    ]
          |})
          |export class ${model.entityName.pascalCase}BoardComponent {
          |    currentSearchCriteria: ${model.entityName.pascalCase}SearchCriteria = {};
          |    selected${model.entityName.pascalCase}: ${model.entityName.pascalCase}WTO | null = null;
          |    refreshKey = 0;
          |
          |    constructor(private dialog: MatDialog, private ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service) {
          |    }
          |
          |    onSearch(criteria: ${model.entityName.pascalCase}SearchCriteria): void {
          |        this.currentSearchCriteria = criteria;
          |    }
          |
          |    on${model.entityName.pascalCase}Select(${model.entityName.camelCase}: ${model.entityName.pascalCase}WTO): void {
          |        this.selected${model.entityName.pascalCase} = ${model.entityName.camelCase};
          |    }
          |
          |    onDelete${model.entityName.pascalCase}(${model.entityName.camelCase}: ${model.entityName.pascalCase}WTO): void {
          |        const dialogRef = this.dialog.open(${model.entityName.pascalCase}ConfirmDeleteDialogComponent, {
          |            data: {${ model.summaryAttributes.joinToString("") { attribute ->  """                ${attribute.attributeName.camelCase}: ${model.entityName.camelCase}.${attribute.attributeName.camelCase},
          """ } }            }
          |        });
          |        dialogRef.afterClosed().subscribe(result => {
          |            if (result) {
          |                this.${model.entityName.camelCase}Service.delete${model.entityName.pascalCase}(${model.entityName.camelCase}.id).subscribe(() => {
          |                    this.refreshKey++;
          |                });
          |            }
          |        });
          |    }
          |
          |    onSave(updated${model.entityName.pascalCase}: ${model.entityName.pascalCase}WTO): void {
          |        this.${model.entityName.camelCase}Service.update${model.entityName.pascalCase}(updated${model.entityName.pascalCase}).subscribe(() => {
          |            this.selected${model.entityName.pascalCase} = null;
          |            this.refreshKey++;
          |        });
          |    }
          |
          |    onCancel(): void {
          |        this.selected${model.entityName.pascalCase} = null;
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-board/${model.entityName.kebabCase}-board.component.ts"
    }
}