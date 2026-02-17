/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template ItemEditFormServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemEditFormServiceRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {${model.entityName}} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}.model";
          |import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {
          |    ${model.entityName}FormFieldName,
          |    ${model.entityName}FormGroup,
          |} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form-field-name";
          |import {${model.entityName}FormValidationService} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form-validation.service";
          |import {${model.entityName}FormInitialValueService} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form-initial-value.service";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName}FormService {
          |
          |    constructor(
          |        private ${model.entityNameLowercase}FormValidationService: ${model.entityName}FormValidationService,
          |        private ${model.entityNameLowercase}FormInitialValueService: ${model.entityName}FormInitialValueService,
          |    ) {}
          |
          |    public createInitial${model.entityName}Form(): FormGroup<${model.entityName}FormGroup> {
          |        return new FormGroup({${ model.attributes.joinToString("") { attribute ->  """
              |            [${model.entityName}FormFieldName.${attribute.attributeName}]: new FormControl<string>(
              |                this.${model.entityNameLowercase}FormInitialValueService.${attribute.attributeName}InitialValue(),
              |                {
              |                    nonNullable: true,
              |                    validators: this.${model.entityNameLowercase}FormValidationService.validatorFunctions(${model.entityName}FormFieldName.${attribute.attributeName})
              |                },
              |            ),
          """ } }        });
          |    }
          |
          |    public patch${model.entityName}Form(form: AbstractControl, ${model.entityNameLowercase}: ${model.entityName}): void {        ${ model.attributes.joinToString("") { attribute ->  """        FormUtil.requiredFormControl(form, ${model.entityName}FormFieldName.${attribute.attributeName}).patchValue(${model.entityNameLowercase}.${attribute.attributeName});
          """ } }    }
          |
          |    public create${model.entityName}FromFormData(form: AbstractControl): ${model.entityName} {
          |        return {
          |                        ${ model.attributes.joinToString("") { attribute ->  """            ${attribute.attributeName}: FormUtil.requiredFormControl(form, ${model.entityName}FormFieldName.${attribute.attributeName}).value as string,
          """ } }        };
          |    }
          |
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form.service.ts"
    }
}