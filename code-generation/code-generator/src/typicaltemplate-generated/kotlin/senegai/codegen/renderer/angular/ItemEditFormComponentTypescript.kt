/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemEditFormComponentTypescript filled up
 * with the content of the passed models.
 */
object ItemEditFormComponentTypescript {

    fun renderTemplate(model: ItemModel): String {
        return """
          |import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
          |import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
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
          |import {${model.itemName}} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}.model";
          |
          |@Component({
          |    selector: 'app-${model.itemNameLowercase}-edit-form',
          |    templateUrl: './${model.itemNameLowercase}-edit-form.component.html',
          |    styleUrls: ['./${model.itemNameLowercase}-edit-form.component.scss'],
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
          |})
          |export class ${model.itemName}EditFormComponent implements OnInit {
          |    @Input() ${model.itemNameLowercase}: ${model.itemName} | null = null;
          |    @Output() save = new EventEmitter<${model.itemName}>();
          |    @Output() cancel = new EventEmitter<void>();
          |
          |    ${model.itemNameLowercase}Form: FormGroup;
          |
          |    constructor(private fb: FormBuilder) {
          |        this.${model.itemNameLowercase}Form = this.fb.group({
          |            id: [{value: '', disabled: true}], // ID is readonly
          |            firstname: ['', [Validators.required, Validators.minLength(2)]],
          |            nickname: [''],
          |            lastname: ['', [Validators.required, Validators.minLength(2)]]
          |        });
          |    }
          |
          |    ngOnInit(): void {
          |        if (this.${model.itemNameLowercase}) {
          |            this.${model.itemNameLowercase}Form.patchValue(this.${model.itemNameLowercase});
          |        }
          |    }
          |
          |    onSubmit(): void {
          |        if (this.${model.itemNameLowercase}Form.valid) {
          |            const updated${model.itemName}: ${model.itemName} = {
          |                id: this.${model.itemNameLowercase}?.id || 0,
          |                ...this.${model.itemNameLowercase}Form.getRawValue()
          |            };
          |            this.save.emit(updated${model.itemName});
          |        }
          |    }
          |
          |    onCancel(): void {
          |        this.cancel.emit();
          |    }
          |
          |    hasError(controlName: string, errorName: string): boolean {
          |        const control = this.${model.itemNameLowercase}Form.get(controlName);
          |        return control ? control.hasError(errorName) && control.touched : false;
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }
}