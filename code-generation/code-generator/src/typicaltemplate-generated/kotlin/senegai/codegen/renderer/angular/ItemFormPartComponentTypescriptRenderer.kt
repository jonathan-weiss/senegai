/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template ItemFormPartComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartComponentTypescriptRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |import {Component, Input, OnInit} from '@angular/core';
          |import {FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
          |import {MatOption} from "@angular/material/core";
          |import {MatSelect} from "@angular/material/select";
          |import {${model.itemName}FormPartValidationService} from "@app/opus-magnum/opus-magnum-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-validation.service";
          |import {
          |    ${model.itemName}FormPartFieldName,
          |} from "@app/opus-magnum/opus-magnum-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-field-name";
          |import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
          |import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |import {MatTab, MatTabGroup} from "@angular/material/tabs";
          |import {
          |    ${model.itemName}FormPartGroup
          |} from "@app/opus-magnum/opus-magnum-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-group";
          |
          |@Component({
          |    selector: 'app-${model.itemNameLowercase}-form-part',
          |    templateUrl: './${model.itemNameLowercase}-form-part.component.html',
          |    styleUrls: ['./${model.itemNameLowercase}-form-part.component.scss'],
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
          |        FieldWrapperComponent,
          |        TextInputComponent,
          |        MatTabGroup,
          |        MatTab,
          |    ]
          |})
          |export class ${model.itemName}FormPartComponent implements OnInit {
          |    @Input({ required: true }) ${model.itemNameLowercase}Form!: FormGroup<${model.itemName}FormPartGroup>;
          |    ${ model.attributes.joinToString("") { attribute ->  """    protected ${attribute.attributeName}Control!: FormControl<string>
              |    protected ${attribute.attributeName}ValidatorNames!: ReadonlyArray<ValidatorTranslation>
              |
          """ } }
          |    constructor(private readonly ${model.itemNameLowercase}FormValidationService: ${model.itemName}FormPartValidationService,) {
          |    }
          |
          |    ngOnInit() {
          |        ${ model.attributes.joinToString("") { attribute ->  """
              |        this.${attribute.attributeName}Control = FormUtil.requiredFormControl(this.${model.itemNameLowercase}Form, ${model.itemName}FormPartFieldName.${attribute.attributeName})
              |        this.${attribute.attributeName}ValidatorNames = this.${model.itemNameLowercase}FormValidationService.validatorNames(${model.itemName}FormPartFieldName.${attribute.attributeName})
              |
          """ } }    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "opus-magnum/opus-magnum-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component.ts"
    }
}