/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityResultComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-result.component.ts`
 * - path: `opus-magnum/opus-magnum-result/opus-magnum-result.component.ts`
 */
object EntityResultComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
          |import {MatTableDataSource, MatTableModule} from '@angular/material/table';
          |import {${model.entityName.pascalCase}SearchCriteria} from '@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-search/${model.entityName.kebabCase}-search.component';
          |import {${model.entityName.pascalCase}Service} from '@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}.service';
          |import {ReactiveFormsModule} from "@angular/forms";
          |import {MatButtonModule} from "@angular/material/button";
          |import {MatToolbarModule} from "@angular/material/toolbar";
          |import {MatCardModule} from "@angular/material/card";
          |import {MatFormFieldModule} from "@angular/material/form-field";
          |import {MatInputModule} from "@angular/material/input";
          |import {MatIconModule} from "@angular/material/icon";
          |import {MatExpansionModule} from "@angular/material/expansion";
          |import {MatSidenavModule} from "@angular/material/sidenav";
          |import {MatListModule} from "@angular/material/list";
          |import {MatDialogModule} from "@angular/material/dialog";
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |import {${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}-search-criteria.wto";
          |
          |@Component({
          |    selector: 'app-${model.entityName.kebabCase}-result',
          |    templateUrl: './${model.entityName.kebabCase}-result.component.html',
          |    styleUrls: ['./${model.entityName.kebabCase}-result.component.scss'],
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
          |export class ${model.entityName.pascalCase}ResultComponent implements OnInit, OnChanges {
          |    @Input() searchCriteria: ${model.entityName.pascalCase}SearchCriteria = {};
          |    @Input() refreshKey: number = 0;
          |    @Output() select${model.entityName.pascalCase} = new EventEmitter<${model.entityRootItem.itemName.pascalCase}WTO>();
          |    @Output() delete${model.entityName.pascalCase} = new EventEmitter<${model.entityRootItem.itemName.pascalCase}WTO>();
          |    @Output() create${model.entityName.pascalCase} = new EventEmitter<void>();
          |
          |    displayedColumns: string[] = [
          |${ model.searchResultAttributes.joinToString("") { attribute ->  """        '${attribute.attributeName.camelCase}',
              |""" } }        'actions'
          |    ];
          |    dataSource: MatTableDataSource<${model.entityRootItem.itemName.pascalCase}WTO> = new MatTableDataSource<${model.entityRootItem.itemName.pascalCase}WTO>();
          |
          |    constructor(private ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service) {
          |    }
          |
          |    ngOnInit(): void {
          |        this.load${model.entityName.pascalCase}s();
          |    }
          |
          |    ngOnChanges(changes: SimpleChanges): void {
          |        const refreshed = changes['refreshKey'] && !changes['refreshKey'].firstChange;
          |        const searchCriteriaChanged = changes['searchCriteria'] && !changes['searchCriteria'].firstChange;
          |        if (refreshed || searchCriteriaChanged) {
          |            this.load${model.entityName.pascalCase}s();
          |        }
          |    }
          |
          |    private load${model.entityName.pascalCase}s(): void {
          |        const searchCriteria: ${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO = {
          |            query: this.searchCriteria?.searchQuery ?? '',
          |        };
          |        this.${model.entityName.camelCase}Service.search${model.entityRootItem.itemName.pascalCase}List(searchCriteria)
          |            .subscribe(searchResult => {
          |                this.dataSource.data = searchResult.${model.entityRootItem.itemName.camelCase}List;
          |            });
          |    }
          |
          |    onCreate(): void {
          |        this.create${model.entityName.pascalCase}.emit();
          |    }
          |
          |    onEdit(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): void {
          |        this.select${model.entityName.pascalCase}.emit(${model.entityName.camelCase});
          |    }
          |
          |    onDelete(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): void {
          |        this.delete${model.entityName.pascalCase}.emit(${model.entityName.camelCase});
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-result/${model.entityName.kebabCase}-result.component.ts"
    }
}