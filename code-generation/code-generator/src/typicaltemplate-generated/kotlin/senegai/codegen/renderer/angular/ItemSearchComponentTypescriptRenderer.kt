/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemSearchComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object ItemSearchComponentTypescriptRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |import {Component, EventEmitter, Output} from '@angular/core';
          |import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
          |import {MatDialogModule} from "@angular/material/dialog";
          |
          |export interface ${model.itemName}SearchCriteria {
          |    id?: number;
          |    firstname?: string;
          |    nickname?: string;
          |    lastname?: string;
          |}
          |
          |@Component({
          |    selector: 'app-${model.itemNameLowercase}-search',
          |    templateUrl: './${model.itemNameLowercase}-search.component.html',
          |    styleUrls: ['./${model.itemNameLowercase}-search.component.scss'],
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
          |export class ${model.itemName}SearchComponent {
          |    @Output() search = new EventEmitter<${model.itemName}SearchCriteria>();
          |
          |    searchForm: FormGroup;
          |
          |    constructor(private fb: FormBuilder) {
          |        this.searchForm = this.fb.group({
          |            id: [''],
          |            firstname: [''],
          |            nickname: [''],
          |            lastname: ['']
          |        });
          |    }
          |
          |    onSubmit(): void {
          |        if (this.searchForm.valid) {
          |            const criteria: ${model.itemName}SearchCriteria = {};
          |            Object.keys(this.searchForm.controls).forEach(key => {
          |                const value = this.searchForm.get(key)?.value;
          |                if (value !== null && value !== '') {
          |                    criteria[key as keyof ${model.itemName}SearchCriteria] = value;
          |                }
          |            });
          |            this.search.emit(criteria);
          |        }
          |    }
          |
          |    onReset(): void {
          |        this.searchForm.reset();
          |        this.search.emit({});
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-search/${model.itemNameLowercase}-search.component.ts"
    }
}