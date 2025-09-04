/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemResultComponentTypescript filled up
 * with the content of the passed models.
 */
object ItemResultComponentTypescript {

    fun renderTemplate(model: ItemModel): String {
        return """
          |import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
          |import {MatTableDataSource, MatTableModule} from '@angular/material/table';
          |import {${model.itemName}SearchCriteria} from '@app/${model.itemNameLowercase}/${model.itemNameLowercase}-search/${model.itemNameLowercase}-search.component';
          |import {${model.itemName}Service} from '@app/${model.itemNameLowercase}/${model.itemNameLowercase}.service';
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
          |import {${model.itemName}} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}.model";
          |
          |@Component({
          |    selector: 'app-${model.itemNameLowercase}-result',
          |    templateUrl: './${model.itemNameLowercase}-result.component.html',
          |    styleUrls: ['./${model.itemNameLowercase}-result.component.scss'],
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
          |    ],
          |
          |})
          |export class ${model.itemName}ResultComponent implements OnChanges {
          |    @Input() searchCriteria: ${model.itemName}SearchCriteria = {};
          |    @Input() refreshKey: number = 0;
          |    @Output() select${model.itemName} = new EventEmitter<${model.itemName}>();
          |    @Output() delete${model.itemName} = new EventEmitter<${model.itemName}>();
          |
          |    displayedColumns: string[] = ['id', 'firstname', 'nickname', 'lastname', 'actions'];
          |    dataSource: MatTableDataSource<${model.itemName}> = new MatTableDataSource<${model.itemName}>();
          |    private all${model.itemName}s: ${model.itemName}[] = [];
          |
          |    constructor(private ${model.itemNameLowercase}Service: ${model.itemName}Service) {
          |        this.load${model.itemName}s();
          |    }
          |
          |    ngOnChanges(changes: SimpleChanges): void {
          |        if (changes['refreshKey'] && !changes['refreshKey'].firstChange) {
          |            this.load${model.itemName}s();
          |        } else if (changes['searchCriteria'] && this.all${model.itemName}s.length) {
          |            this.filter${model.itemName}s();
          |        }
          |    }
          |
          |    private load${model.itemName}s(): void {
          |        this.${model.itemNameLowercase}Service.get${model.itemName}s().subscribe(${model.itemNameLowercase}s => {
          |            this.all${model.itemName}s = ${model.itemNameLowercase}s;
          |            this.filter${model.itemName}s();
          |        });
          |    }
          |
          |    onEdit(${model.itemNameLowercase}: ${model.itemName}): void {
          |        this.select${model.itemName}.emit(${model.itemNameLowercase});
          |    }
          |
          |    onDelete(${model.itemNameLowercase}: ${model.itemName}): void {
          |        this.delete${model.itemName}.emit(${model.itemNameLowercase});
          |    }
          |
          |    private filter${model.itemName}s(): void {
          |        const criteria = this.searchCriteria;
          |        this.dataSource.data = this.all${model.itemName}s.filter(${model.itemNameLowercase} => {
          |            return (
          |                (!criteria.id || ${model.itemNameLowercase}.id === criteria.id) &&
          |                (!criteria.firstname || ${model.itemNameLowercase}.firstname.toLowerCase().includes(criteria.firstname.toLowerCase())) &&
          |                (!criteria.nickname || ${model.itemNameLowercase}.nickname.toLowerCase().includes(criteria.nickname.toLowerCase())) &&
          |                (!criteria.lastname || ${model.itemNameLowercase}.lastname.toLowerCase().includes(criteria.lastname.toLowerCase()))
          |            );
          |        });
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }
}