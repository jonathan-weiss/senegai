/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityFormComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form.component.ts`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component.ts`
 */
object EntityFormComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
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
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |import {${model.entityRootItem.itemName.pascalCase}FormPartService} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityRootItem.itemName.kebabCase}-form-part/${model.entityRootItem.itemName.kebabCase}-form-part.service";
          |import {${model.entityRootItem.itemName.pascalCase}FormPartComponent} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityRootItem.itemName.kebabCase}-form-part/${model.entityRootItem.itemName.kebabCase}-form-part.component";
          |import {
          |    ${model.entityRootItem.itemName.pascalCase}FormPartGroup
          |} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityRootItem.itemName.kebabCase}-form-part/${model.entityRootItem.itemName.kebabCase}-form-part-group";
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
          |        ${model.entityRootItem.itemName.pascalCase}FormPartComponent,
          |    ]
          |})
          |export class ${model.entityName.pascalCase}FormComponent implements OnInit {
          |    @Input() ${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO | null = null;
          |    @Output() save = new EventEmitter<${model.entityRootItem.itemName.pascalCase}WTO>();
          |    @Output() cancel = new EventEmitter<void>();
          |
          |    ${model.entityRootItem.itemName.camelCase}Form: FormGroup<${model.entityRootItem.itemName.pascalCase}FormPartGroup>;
          |
          |    constructor(private ${model.entityRootItem.itemName.camelCase}FormPartService: ${model.entityRootItem.itemName.pascalCase}FormPartService) {
          |        this.${model.entityRootItem.itemName.camelCase}Form = ${model.entityRootItem.itemName.camelCase}FormPartService.createInitial${model.entityRootItem.itemName.pascalCase}Form();
          |    }
          |
          |    ngOnInit(): void {
          |        if (this.${model.entityRootItem.itemName.camelCase}) {
          |            this.${model.entityRootItem.itemName.camelCase}FormPartService.patch${model.entityRootItem.itemName.pascalCase}Form(this.${model.entityRootItem.itemName.camelCase}Form, this.${model.entityRootItem.itemName.camelCase})
          |        }
          |    }
          |
          |    onSubmit(): void {
          |        if (this.${model.entityRootItem.itemName.camelCase}Form.valid) {
          |            const updated${model.entityRootItem.itemName.pascalCase}: ${model.entityRootItem.itemName.pascalCase}WTO = this.${model.entityRootItem.itemName.camelCase}FormPartService.create${model.entityRootItem.itemName.pascalCase}WTOFromForm(this.${model.entityRootItem.itemName.camelCase}Form)
          |            this.save.emit(updated${model.entityRootItem.itemName.pascalCase});
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