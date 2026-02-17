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
          |import {${model.entityName}SearchCriteria} from '@app/${model.entityNameLowercase}/${model.entityNameLowercase}-search/${model.entityNameLowercase}-search.component';
          |import {${model.entityName}Service} from '@app/${model.entityNameLowercase}/${model.entityNameLowercase}.service';
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
          |import {${model.entityName}WTO} from "@app/wto/${model.entityNameLowercase}.wto";
          |
          |@Component({
          |    selector: 'app-${model.entityNameLowercase}-result',
          |    templateUrl: './${model.entityNameLowercase}-result.component.html',
          |    styleUrls: ['./${model.entityNameLowercase}-result.component.scss'],
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
          |export class ${model.entityName}ResultComponent implements OnChanges {
          |    @Input() searchCriteria: ${model.entityName}SearchCriteria = {};
          |    @Input() refreshKey: number = 0;
          |    @Output() select${model.entityName} = new EventEmitter<${model.entityName}WTO>();
          |    @Output() delete${model.entityName} = new EventEmitter<${model.entityName}WTO>();
          |
          |    displayedColumns: string[] = [
          |        ${ model.attributes.joinToString("") { attribute ->  """
              |        '${attribute.attributeName}',
          """ } }        'actions'
          |    ];
          |    dataSource: MatTableDataSource<${model.entityName}WTO> = new MatTableDataSource<${model.entityName}WTO>();
          |    private all${model.entityName}s: ${model.entityName}WTO[] = [];
          |
          |    constructor(private ${model.entityNameLowercase}Service: ${model.entityName}Service) {
          |        this.load${model.entityName}s();
          |    }
          |
          |    ngOnChanges(changes: SimpleChanges): void {
          |        if (changes['refreshKey'] && !changes['refreshKey'].firstChange) {
          |            this.load${model.entityName}s();
          |        } else if (changes['searchCriteria'] && this.all${model.entityName}s.length) {
          |            this.filter${model.entityName}s();
          |        }
          |    }
          |
          |    private load${model.entityName}s(): void {
          |        this.${model.entityNameLowercase}Service.get${model.entityName}s().subscribe(${model.entityNameLowercase}s => {
          |            this.all${model.entityName}s = ${model.entityNameLowercase}s;
          |            this.filter${model.entityName}s();
          |        });
          |    }
          |
          |    onEdit(${model.entityNameLowercase}: ${model.entityName}WTO): void {
          |        this.select${model.entityName}.emit(${model.entityNameLowercase});
          |    }
          |
          |    onDelete(${model.entityNameLowercase}: ${model.entityName}WTO): void {
          |        this.delete${model.entityName}.emit(${model.entityNameLowercase});
          |    }
          |
          |    private filter${model.entityName}s(): void {
          |        const criteria = this.searchCriteria;
          |        this.dataSource.data = this.all${model.entityName}s.filter(${model.entityNameLowercase} => {
          |            return (${ model.attributes.joinToString("") { attribute ->  """                this.isMatching${attribute.typescriptAttributeTypeCapitalizedWithoutNullability}Criteria(criteria.${attribute.attributeName}, ${model.entityNameLowercase}.${attribute.attributeName}) &&
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
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-result/${model.entityNameLowercase}-result.component.ts"
    }
}