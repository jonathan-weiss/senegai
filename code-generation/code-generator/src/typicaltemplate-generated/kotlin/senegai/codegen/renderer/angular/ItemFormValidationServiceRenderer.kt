/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemFormValidationServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemFormValidationServiceRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {ValidatorFn, Validators} from "@angular/forms";
          |import {${model.itemName}FormFieldName} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-field-name";
          |import {NamedValidator} from "@app/shared/form-controls/named-validator";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}FormValidationService {
          |
          |    validatorFunctions(field: ${model.itemName}FormFieldName): Array<ValidatorFn> {
          |        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
          |    }
          |
          |    validatorNames(field: ${model.itemName}FormFieldName): Array<ValidatorTranslation> {
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
          |    namedValidators(field: ${model.itemName}FormFieldName): ReadonlyArray<NamedValidator> {
          |        // TODO use mapped types https://www.typescriptlang.org/docs/handbook/2/mapped-types.html
          |        switch(field) {${ model.attributes.joinToString("") { attribute ->  """
              |            case ${model.itemName}FormFieldName.${attribute.attributeName}: return [
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

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-form-validation.service.ts"
    }
}