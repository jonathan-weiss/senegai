/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityFormValidationServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityFormValidationServiceRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {ValidatorFn, Validators} from "@angular/forms";
          |import {${model.entityName}FormFieldName} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form-field-name";
          |import {NamedValidator} from "@app/shared/form-controls/named-validator";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName}FormValidationService {
          |
          |    validatorFunctions(field: ${model.entityName}FormFieldName): Array<ValidatorFn> {
          |        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
          |    }
          |
          |    validatorNames(field: ${model.entityName}FormFieldName): Array<ValidatorTranslation> {
          |        return this.namedValidators(field)
          |            .map(namedValidator => this.toValidatorTranslation(namedValidator))
          |    }
          |
          |    private toValidatorTranslation(namedValidator: ValidatorTranslation): ValidatorTranslation {
          |        return {
          |            validatorName: namedValidator.validatorName,
          |            validatorTranslationKey: namedValidator.validatorTranslationKey,
          |        }
          |    }
          |
          |    namedValidators(field: ${model.entityName}FormFieldName): ReadonlyArray<NamedValidator> {
          |        // TODO use mapped types https://www.typescriptlang.org/docs/handbook/2/mapped-types.html
          |        switch(field) {${ model.attributes.joinToString("") { attribute ->  """
              |            case ${model.entityName}FormFieldName.${attribute.attributeName}: return [
              |                {
              |                    validatorName: "required",
              |                    validatorFunction: Validators.required,
              |                    validatorTranslationKey: "validator.required",
              |                },
              |                {
              |                    validatorName: "minlength",
              |                    validatorFunction: Validators.minLength(2),
              |                    validatorTranslationKey: "validator.minlength",
              |                },
              |            ]
          """ } }            default: return []
          |        }
          |    };
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}-form/${model.entityNameLowercase}-form-validation.service.ts"
    }
}