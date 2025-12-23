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
          |import {AbstractControl, FormArray, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {${model.itemName}FormFieldName} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-field-name";
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
          |    public createEmptyForm(): FormGroup {${ model.attributes.joinToString("") { attribute ->  """
              |        const ${attribute.attributeName}InitialValue = this.${model.itemNameLowercase}FormInitialValueService.${attribute.attributeName}InitialValue()
              |        const ${attribute.attributeName}ValidatorFunctions = this.${model.itemNameLowercase}FormValidationService.validatorFunctions(${model.itemName}FormFieldName.${attribute.attributeName})
          """ } }
          |        return new FormGroup({
          |            id: new FormControl<number>({value: 0, disabled: true}), // ID is readonly${ model.attributes.joinToString("") { attribute ->  """
              |            [${model.itemName}FormFieldName.${attribute.attributeName}]: new FormControl<string>(${attribute.attributeName}InitialValue, ${attribute.attributeName}ValidatorFunctions),
          """ } }        });
          |    }
          |
          |    public patchForm(form: AbstractControl, ${model.itemNameLowercase}: ${model.itemName}): void {
          |        FormUtil.requiredFormControl(form, "id").patchValue(${model.itemNameLowercase}.id);${ model.attributes.joinToString("") { attribute ->  """        FormUtil.requiredFormControl(form, "${attribute.attributeName}").patchValue(${model.itemNameLowercase}.${attribute.attributeName});
          """ } }    }
          |
          |    public create${model.itemName}FromFormData(form: AbstractControl): ${model.itemName} {
          |        return {
          |            id: FormUtil.requiredFormControl(form, "id").value as number,${ model.attributes.joinToString("") { attribute ->  """            ${attribute.attributeName}: FormUtil.requiredFormControl(form, "${attribute.attributeName}").value as ${attribute.typescriptAttributeType},
          """ } }        };
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form.service.ts"
    }
}