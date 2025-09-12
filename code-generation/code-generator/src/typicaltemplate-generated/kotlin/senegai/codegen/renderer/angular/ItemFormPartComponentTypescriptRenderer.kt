/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormPartComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object ItemFormPartComponentTypescriptRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |import {Component, Input} from '@angular/core';
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
          |import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
          |import {MatNativeDateModule, MatOption} from "@angular/material/core";
          |import {MatCheckbox} from "@angular/material/checkbox";
          |import {MatSelect} from "@angular/material/select";
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
          |        MatDatepickerInput,
          |        MatDatepickerToggle,
          |        MatDatepicker,
          |        MatNativeDateModule,
          |        MatCheckbox,
          |        MatSelect,
          |        MatOption,    ]
          |})
          |export class ${model.itemName}FormPartComponent {
          |    @Input({ required: true }) ${model.itemNameLowercase}Form!: FormGroup;
          |
          |    get idControl(): FormControl {
          |        return FormUtil.requiredFormControl(this.${model.itemNameLowercase}Form, "id");
          |    }
          |
          |    get nicknameIsNotNullControl(): FormControl {
          |        return FormUtil.requiredFormControl(this.${model.itemNameLowercase}Form, "nicknameIsNotNull");
          |    }
          |
          |    get nicknameControl(): FormControl {
          |        return FormUtil.requiredFormControl(this.${model.itemNameLowercase}Form, "nickname");
          |    }
          |
          |    get firstnameControl(): FormControl {
          |        return FormUtil.requiredFormControl(this.${model.itemNameLowercase}Form, "firstname");
          |    }
          |
          |    get lastnameControl(): FormControl {
          |        return FormUtil.requiredFormControl(this.${model.itemNameLowercase}Form, "lastname");
          |    }
          |
          |    hasError(controlName: string, errorName: string): boolean {
          |        return FormUtil.hasError(this.${model.itemNameLowercase}Form, controlName, errorName)
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.component.ts"
    }
}