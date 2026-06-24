/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumListFormFieldTableComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `single-appellatio-comis-form-field-table.component.ts`
 * - path: `enum/appellatio-comis-input-selection/single-appellatio-comis-form-field-table.component.ts`
 */
object EnumListFormFieldTableComponentTypescriptRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |import {Component, Input} from '@angular/core';
          |import {FormArray, FormControl} from "@angular/forms";
          |import {SingleFormFieldTableComponent} from "@app/shared/form-controls/single-form-field-table/single-form-field-table.component";
          |import {${model.enumName.pascalCase}SelectorComponent} from "@app/enum/${model.enumName.kebabCase}-input-selection/${model.enumName.kebabCase}-selector.component";
          |import {${model.enumName.pascalCase}Enum, ${model.enumName.pascalCase}EnumValues} from "@app/wto/${model.enumName.kebabCase}.enum";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |
          |@Component({
          |    selector: 'app-single-${model.enumName.kebabCase}-form-field-table',
          |    templateUrl: './single-${model.enumName.kebabCase}-form-field-table.component.html',
          |    standalone: true,
          |    imports: [
          |        SingleFormFieldTableComponent,
          |        ${model.enumName.pascalCase}SelectorComponent,
          |    ]
          |})
          |export class Single${model.enumName.pascalCase}FormFieldTableComponent {
          |    @Input({required: true}) formArray!: FormArray;
          |    @Input() columnHeader: string = '';
          |    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];
          |
          |    createControl = (): FormControl => new FormControl<${model.enumName.pascalCase}Enum>(${model.enumName.pascalCase}EnumValues[0], {nonNullable: true});
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.kebabCase}-input-selection/single-${model.enumName.kebabCase}-form-field-table.component.ts"
    }
}