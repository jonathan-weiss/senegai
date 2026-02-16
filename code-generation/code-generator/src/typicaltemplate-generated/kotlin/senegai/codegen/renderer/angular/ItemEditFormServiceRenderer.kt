/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemEditFormServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemEditFormServiceRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {${model.itemName}} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}.model";
          |import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {
          |    ${model.itemName}FormFieldName,
          |    ${model.itemName}FormGroup,
          |} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-field-name";
          |import {${model.itemName}FormValidationService} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-validation.service";
          |import {${model.itemName}FormInitialValueService} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-initial-value.service";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}FormService {
          |
          |    constructor(
          |        private ${model.itemNameLowercase}FormValidationService: ${model.itemName}FormValidationService,
          |        private ${model.itemNameLowercase}FormInitialValueService: ${model.itemName}FormInitialValueService,
          |    ) {}
          |
          |    public createInitial${model.itemName}Form(): FormGroup<${model.itemName}FormGroup> {
          |        return new FormGroup({${ model.attributes.joinToString("") { attribute ->  """
              |            [${model.itemName}FormFieldName.${attribute.attributeName}]: new FormControl<string>(
              |                this.${model.itemNameLowercase}FormInitialValueService.${attribute.attributeName}InitialValue(),
              |                {
              |                    nonNullable: true,
              |                    validators: this.${model.itemNameLowercase}FormValidationService.validatorFunctions(${model.itemName}FormFieldName.${attribute.attributeName})
              |                },
              |            ),
          """ } }        });
          |    }
          |
          |    public patch${model.itemName}Form(form: AbstractControl, ${model.itemNameLowercase}: ${model.itemName}): void {        ${ model.attributes.joinToString("") { attribute ->  """        FormUtil.requiredFormControl(form, ${model.itemName}FormFieldName.${attribute.attributeName}).patchValue(${model.itemNameLowercase}.${attribute.attributeName});
          """ } }    }
          |
          |    public create${model.itemName}FromFormData(form: AbstractControl): ${model.itemName} {
          |        return {
          |                        ${ model.attributes.joinToString("") { attribute ->  """            ${attribute.attributeName}: FormUtil.requiredFormControl(form, ${model.itemName}FormFieldName.${attribute.attributeName}).value as string,
          """ } }        };
          |    }
          |
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form.service.ts"
    }
}