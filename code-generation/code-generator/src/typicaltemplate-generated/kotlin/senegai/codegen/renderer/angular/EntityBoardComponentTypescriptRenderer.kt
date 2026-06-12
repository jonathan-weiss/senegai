/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityBoardComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-board.component.ts`
 * - path: `opus-magnum/opus-magnum-board/opus-magnum-board.component.ts`
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
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
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
          |    selected${model.entityName.pascalCase}: ${model.entityRootItem.itemName.pascalCase}WTO | null = null;
          |    creating = false;
          |    refreshKey = 0;
          |
          |    constructor(private dialog: MatDialog, private ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service) {
          |    }
          |
          |    onSearch(criteria: ${model.entityName.pascalCase}SearchCriteria): void {
          |        this.currentSearchCriteria = criteria;
          |    }
          |
          |    onCreate${model.entityName.pascalCase}(): void {
          |        this.selected${model.entityName.pascalCase} = null;
          |        this.creating = true;
          |    }
          |
          |    on${model.entityName.pascalCase}Select(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): void {
          |        this.creating = false;
          |        this.selected${model.entityName.pascalCase} = ${model.entityName.camelCase};
          |    }
          |
          |    onDelete${model.entityName.pascalCase}(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): void {
          |        const dialogRef = this.dialog.open(${model.entityName.pascalCase}ConfirmDeleteDialogComponent, {
          |            data: {
          |                entity: ${model.entityName.camelCase},
          |            }
          |        });
          |        dialogRef.afterClosed().subscribe(result => {
          |            if (result) {
          |                this.${model.entityName.camelCase}Service.delete${model.entityRootItem.itemName.pascalCase}(${model.entityName.camelCase}.${model.idAttribute.attributeName.camelCase}).subscribe(() => {
          |                    this.refreshKey++;
          |                });
          |            }
          |        });
          |    }
          |
          |    onSave(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): void {
          |        const save${"$"} = this.creating
          |            ? this.${model.entityName.camelCase}Service.create${model.entityRootItem.itemName.pascalCase}(${model.entityName.camelCase})
          |            : this.${model.entityName.camelCase}Service.update${model.entityRootItem.itemName.pascalCase}(${model.entityName.camelCase});
          |        save${"$"}.subscribe(() => {
          |            this.selected${model.entityName.pascalCase} = null;
          |            this.creating = false;
          |            this.refreshKey++;
          |        });
          |    }
          |
          |    onCancel(): void {
          |        this.selected${model.entityName.pascalCase} = null;
          |        this.creating = false;
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-board/${model.entityName.kebabCase}-board.component.ts"
    }
}