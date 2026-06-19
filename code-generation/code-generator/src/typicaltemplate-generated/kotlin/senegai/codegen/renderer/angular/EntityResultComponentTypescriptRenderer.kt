/*
 * This file is generated using typical-template.
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
          |
          |import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
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
          |export class ${model.entityName.pascalCase}ResultComponent implements OnChanges {
          |    @Input() searchCriteria: ${model.entityName.pascalCase}SearchCriteria = {};
          |    @Input() refreshKey: number = 0;
          |    @Output() select${model.entityName.pascalCase} = new EventEmitter<${model.entityRootItem.itemName.pascalCase}WTO>();
          |    @Output() delete${model.entityName.pascalCase} = new EventEmitter<${model.entityRootItem.itemName.pascalCase}WTO>();
          |    @Output() create${model.entityName.pascalCase} = new EventEmitter<void>();
          |
          |    displayedColumns: string[] = [
          |        
          |        ${ model.searchResultAttributes.joinToString("") { attribute ->  """
              |        '${attribute.attributeName.camelCase}',
              |    
          """ } }
          |        'actions'
          |    ];
          |    dataSource: MatTableDataSource<${model.entityRootItem.itemName.pascalCase}WTO> = new MatTableDataSource<${model.entityRootItem.itemName.pascalCase}WTO>();
          |    private all${model.entityName.pascalCase}s: ${model.entityRootItem.itemName.pascalCase}WTO[] = [];
          |
          |    constructor(private ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service) {
          |        this.load${model.entityName.pascalCase}s();
          |    }
          |
          |    ngOnChanges(changes: SimpleChanges): void {
          |        if (changes['refreshKey'] && !changes['refreshKey'].firstChange) {
          |            this.load${model.entityName.pascalCase}s();
          |        } else if (changes['searchCriteria'] && this.all${model.entityName.pascalCase}s.length) {
          |            this.filter${model.entityName.pascalCase}s();
          |        }
          |    }
          |
          |    private load${model.entityName.pascalCase}s(): void {
          |        this.${model.entityName.camelCase}Service.get${model.entityRootItem.itemName.pascalCase}List().subscribe(${model.entityName.camelCase}List => {
          |            this.all${model.entityName.pascalCase}s = ${model.entityName.camelCase}List;
          |            this.filter${model.entityName.pascalCase}s();
          |        });
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
          |
          |    private filter${model.entityName.pascalCase}s(): void {
          |        this.dataSource.data = this.filtered${model.entityName.pascalCase}List(this.searchCriteria, this.all${model.entityName.pascalCase}s);
          |    }
          |
          |    private filtered${model.entityName.pascalCase}List(searchCriteria: ${model.entityName.pascalCase}SearchCriteria, all${model.entityName.pascalCase}: ${model.entityRootItem.itemName.pascalCase}WTO[]): ${model.entityRootItem.itemName.pascalCase}WTO[] {
          |        const searchTokens = searchCriteria?.searchQuery?.split(" ") ?? [];
          |        if(searchTokens.length < 1) {
          |            return all${model.entityName.pascalCase}
          |        } else {
          |            return all${model.entityName.pascalCase}.filter(${model.entityName.camelCase} => {
          |                return searchTokens.some(searchToken => this.isMatchingCriteria(searchToken, ${model.entityName.camelCase}))
          |            });
          |        }
          |    }
          |
          |    private isMatchingCriteria(searchCriteriaText: string | undefined | null, ${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): boolean {
          |        if(searchCriteriaText == undefined) {
          |            return true;
          |        }
          |        // a rather simple implementation to search, but good enough for the moment...
          |        return JSON.stringify(${model.entityName.camelCase}).includes(searchCriteriaText);
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-result/${model.entityName.kebabCase}-result.component.ts"
    }
}