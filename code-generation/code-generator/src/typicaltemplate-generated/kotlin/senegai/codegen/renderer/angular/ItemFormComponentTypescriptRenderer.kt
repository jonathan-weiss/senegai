/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object ItemFormComponentTypescriptRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
          |import {FormGroup, ReactiveFormsModule} from '@angular/forms';
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
          |import {${model.itemName}FormService} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form.service";
          |import {${model.itemName}FormPartComponent} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component";
          |
          |@Component({
          |    selector: 'app-${model.itemNameLowercase}-form',
          |    templateUrl: './${model.itemNameLowercase}-form.component.html',
          |    styleUrls: ['./${model.itemNameLowercase}-form.component.scss'],
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
          |        ${model.itemName}FormPartComponent,
          |    ]
          |})
          |export class ${model.itemName}FormComponent implements OnInit {
          |    @Input() ${model.itemNameLowercase}: ${model.itemName} | null = null;
          |    @Output() save = new EventEmitter<${model.itemName}>();
          |    @Output() cancel = new EventEmitter<void>();
          |
          |    ${model.itemNameLowercase}Form: FormGroup;
          |
          |    constructor(private ${model.itemNameLowercase}EditFormService: ${model.itemName}FormService) {
          |        this.${model.itemNameLowercase}Form = ${model.itemNameLowercase}EditFormService.createInitial${model.itemName}Form();
          |    }
          |
          |    ngOnInit(): void {
          |        if (this.${model.itemNameLowercase}) {
          |            this.${model.itemNameLowercase}EditFormService.patch${model.itemName}Form(this.${model.itemNameLowercase}Form, this.${model.itemNameLowercase})
          |        }
          |    }
          |
          |    onSubmit(): void {
          |        if (this.${model.itemNameLowercase}Form.valid) {
          |            const updated${model.itemName}: ${model.itemName} = this.${model.itemNameLowercase}EditFormService.create${model.itemName}FromFormData(this.${model.itemNameLowercase}Form)
          |            this.save.emit(updated${model.itemName});
          |        }
          |    }
          |
          |    onCancel(): void {
          |        this.cancel.emit();
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form.component.ts"
    }
}