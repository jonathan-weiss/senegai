/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEnumModel

/**
 * Generate the content for the template `EnumSelectorComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `appellatio-selector.component.ts`
 * - path: `enum/appellatio-input-selection/appellatio-selector.component.ts`
 */
object EnumSelectorComponentTypescriptRenderer : UiEnumRenderer {

    override fun renderTemplate(model: UiEnumModel): String {
        return """
          |import {Component, Input} from '@angular/core';
          |import {MatFormFieldModule} from '@angular/material/form-field';
          |import {MatInputModule} from '@angular/material/input';
          |import {CommonModule} from '@angular/common';
          |import {FormControl, ReactiveFormsModule} from '@angular/forms';
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |import {TranslocoPipe} from "@jsverse/transloco";
          |import {${model.enumName.pascalCase}I18nComponent} from "@app/enum/${model.enumName.camelCase}-i18n/${model.enumName.camelCase}-i18n.component";
          |import {MatOption} from "@angular/material/core";
          |import {MatSelect} from "@angular/material/select";
          |import {${model.enumName.pascalCase}EnumValues} from "@app/wto/${model.enumName.camelCase}.enum";
          |
          |@Component({
          |    selector: 'app-${model.enumName.camelCase}-selector',
          |    templateUrl: './${model.enumName.camelCase}-selector.component.html',
          |    styleUrls: ['./${model.enumName.camelCase}-selector.component.scss'],
          |    standalone: true,
          |    imports: [
          |        CommonModule,
          |        ReactiveFormsModule,
          |        MatFormFieldModule,
          |        MatInputModule,
          |        TranslocoPipe,
          |        ${model.enumName.pascalCase}I18nComponent,
          |        MatOption,
          |        MatSelect,
          |    ]
          |})
          |export class ${model.enumName.pascalCase}SelectorComponent {
          |    @Input() label: string = '';
          |    @Input({required: true}) enumFormControl!: FormControl;
          |    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];
          |
          |    hasError(errorName: string): boolean {
          |        return this.enumFormControl.hasError(errorName) && this.enumFormControl.touched;
          |    }
          |
          |
          |    protected readonly ${model.enumName.camelCase}List = ${model.enumName.pascalCase}EnumValues;
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEnumModel): String {
      return "enum/${model.enumName.camelCase}-input-selection/${model.enumName.camelCase}-selector.component.ts"
    }
}