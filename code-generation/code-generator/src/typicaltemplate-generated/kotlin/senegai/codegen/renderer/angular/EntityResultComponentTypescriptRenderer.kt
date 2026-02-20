/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityResultComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object EntityResultComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
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
          |import {${model.entityName.pascalCase}WTO} from "@app/wto/${model.entityName.kebabCase}.wto";
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
          |    @Output() select${model.entityName.pascalCase} = new EventEmitter<${model.entityName.pascalCase}WTO>();
          |    @Output() delete${model.entityName.pascalCase} = new EventEmitter<${model.entityName.pascalCase}WTO>();
          |
          |    displayedColumns: string[] = [        ${ model.searchResultAttributes.joinToString("") { attribute ->  """
              |        '${attribute.attributeName.camelCase}',
          """ } }        'actions'
          |    ];
          |    dataSource: MatTableDataSource<${model.entityName.pascalCase}WTO> = new MatTableDataSource<${model.entityName.pascalCase}WTO>();
          |    private all${model.entityName.pascalCase}s: ${model.entityName.pascalCase}WTO[] = [];
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
          |        this.${model.entityName.camelCase}Service.get${model.entityName.pascalCase}s().subscribe(${model.entityName.camelCase}List => {
          |            this.all${model.entityName.pascalCase}s = ${model.entityName.camelCase}List;
          |            this.filter${model.entityName.pascalCase}s();
          |        });
          |    }
          |
          |    onEdit(${model.entityName.camelCase}: ${model.entityName.pascalCase}WTO): void {
          |        this.select${model.entityName.pascalCase}.emit(${model.entityName.camelCase});
          |    }
          |
          |    onDelete(${model.entityName.camelCase}: ${model.entityName.pascalCase}WTO): void {
          |        this.delete${model.entityName.pascalCase}.emit(${model.entityName.camelCase});
          |    }
          |
          |    private filter${model.entityName.pascalCase}s(): void {
          |        const criteria = this.searchCriteria;
          |        this.dataSource.data = this.all${model.entityName.pascalCase}s.filter(${model.entityName.camelCase} => {
          |            return (${ model.searchResultAttributes.joinToString("") { attribute ->  """                this.isMatching${attribute.typescriptAttributeTypeCapitalizedWithoutNullability}Criteria(criteria.${attribute.attributeName.camelCase}, ${model.entityName.camelCase}.${attribute.attributeName.camelCase}) &&
          """ } }                    true
          |            );
          |        });
          |    }
          |
          |    private isMatchingStringCriteria(searchCriteriaText: string | undefined | null, dataText: string | undefined | null): boolean {
          |        if(searchCriteriaText == undefined) {
          |            return true;
          |        }
          |        if(dataText == undefined) {
          |            return false;
          |        }
          |        return dataText.toLowerCase().trim().includes(searchCriteriaText.toLowerCase().trim())
          |    }
          |
          |    private isMatchingNumberCriteria(searchCriteriaNumber: number | undefined | null, dataNumber: number | undefined | null): boolean {
          |        if(searchCriteriaNumber == undefined) {
          |            return true;
          |        }
          |        if(dataNumber == undefined) {
          |            return false;
          |        }
          |        return searchCriteriaNumber === dataNumber;
          |    }
          |
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-result/${model.entityName.kebabCase}-result.component.ts"
    }
}