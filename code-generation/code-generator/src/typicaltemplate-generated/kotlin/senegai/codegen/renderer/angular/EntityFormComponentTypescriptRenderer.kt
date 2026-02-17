/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityFormComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object EntityFormComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
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
          |import {${model.entityName}WTO} from "@app/wto/${model.entityNameDashCase}.wto";
          |import {${model.entityName}FormPartService} from "@app/${model.entityNameDashCase}/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form-part/${model.entityNameDashCase}-form-part.service";
          |import {${model.entityName}FormPartComponent} from "@app/${model.entityNameDashCase}/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form-part/${model.entityNameDashCase}-form-part.component";
          |
          |@Component({
          |    selector: 'app-${model.entityNameDashCase}-form',
          |    templateUrl: './${model.entityNameDashCase}-form.component.html',
          |    styleUrls: ['./${model.entityNameDashCase}-form.component.scss'],
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
          |        ${model.entityName}FormPartComponent,
          |    ]
          |})
          |export class ${model.entityName}FormComponent implements OnInit {
          |    @Input() ${model.entityNameLowercase}: ${model.entityName}WTO | null = null;
          |    @Output() save = new EventEmitter<${model.entityName}WTO>();
          |    @Output() cancel = new EventEmitter<void>();
          |
          |    ${model.entityNameLowercase}Form: FormGroup;
          |
          |    constructor(private ${model.entityNameLowercase}FormPartService: ${model.entityName}FormPartService) {
          |        this.${model.entityNameLowercase}Form = ${model.entityNameLowercase}FormPartService.createInitial${model.entityName}Form();
          |    }
          |
          |    ngOnInit(): void {
          |        if (this.${model.entityNameLowercase}) {
          |            this.${model.entityNameLowercase}FormPartService.patch${model.entityName}Form(this.${model.entityNameLowercase}Form, this.${model.entityNameLowercase})
          |        }
          |    }
          |
          |    onSubmit(): void {
          |        if (this.${model.entityNameLowercase}Form.valid) {
          |            const updated${model.entityName}: ${model.entityName}WTO = this.${model.entityNameLowercase}FormPartService.create${model.entityName}FromFormData(this.${model.entityNameLowercase}Form)
          |            this.save.emit(updated${model.entityName});
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

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form.component.ts"
    }
}