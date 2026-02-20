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
          |import {${model.entityName.pascalCase}WTO} from "@app/wto/${model.entityName.kebabCase}.wto";
          |import {${model.entityName.pascalCase}FormPartService} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form-part/${model.entityName.kebabCase}-form-part.service";
          |import {${model.entityName.pascalCase}FormPartComponent} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form-part/${model.entityName.kebabCase}-form-part.component";
          |
          |@Component({
          |    selector: 'app-${model.entityName.kebabCase}-form',
          |    templateUrl: './${model.entityName.kebabCase}-form.component.html',
          |    styleUrls: ['./${model.entityName.kebabCase}-form.component.scss'],
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
          |        ${model.entityName.pascalCase}FormPartComponent,
          |    ]
          |})
          |export class ${model.entityName.pascalCase}FormComponent implements OnInit {
          |    @Input() ${model.entityName.camelCase}: ${model.entityName.pascalCase}WTO | null = null;
          |    @Output() save = new EventEmitter<${model.entityName.pascalCase}WTO>();
          |    @Output() cancel = new EventEmitter<void>();
          |
          |    ${model.entityName.camelCase}Form: FormGroup;
          |
          |    constructor(private ${model.entityName.camelCase}FormPartService: ${model.entityName.pascalCase}FormPartService) {
          |        this.${model.entityName.camelCase}Form = ${model.entityName.camelCase}FormPartService.createInitial${model.entityName.pascalCase}Form();
          |    }
          |
          |    ngOnInit(): void {
          |        if (this.${model.entityName.camelCase}) {
          |            this.${model.entityName.camelCase}FormPartService.patch${model.entityName.pascalCase}Form(this.${model.entityName.camelCase}Form, this.${model.entityName.camelCase})
          |        }
          |    }
          |
          |    onSubmit(): void {
          |        if (this.${model.entityName.camelCase}Form.valid) {
          |            const updated${model.entityName.pascalCase}: ${model.entityName.pascalCase}WTO = this.${model.entityName.camelCase}FormPartService.create${model.entityName.pascalCase}FromFormData(this.${model.entityName.camelCase}Form)
          |            this.save.emit(updated${model.entityName.pascalCase});
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
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form.component.ts"
    }
}