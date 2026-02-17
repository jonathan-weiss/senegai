/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template EntityItemFormPartValidationServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartValidationServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(entity: UiEntityModel, model: UiItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {ValidatorFn, Validators} from "@angular/forms";
          |import {${model.itemName}FormPartFieldName} from "@app/${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-field-name";
          |import {NamedValidator} from "@app/shared/form-controls/named-validator";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}FormPartValidationService {
          |
          |    validatorFunctions(field: ${model.itemName}FormPartFieldName): Array<ValidatorFn> {
          |        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
          |    }
          |
          |    validatorNames(field: ${model.itemName}FormPartFieldName): Array<ValidatorTranslation> {
          |        return this.namedValidators(field)
          |            .map(namedValidator => this.toValidatorTranslation(namedValidator))
          |    }
          |
          |    private toValidatorTranslation(namedValidator: NamedValidator): ValidatorTranslation {
          |        return {
          |            validatorName: namedValidator.validatorName,
          |            validatorTranslationKey: namedValidator.validatorTranslationKey,
          |        }
          |    }
          |
          |    namedValidators(field: ${model.itemName}FormPartFieldName): ReadonlyArray<NamedValidator> {
          |        // TODO use mapped types https://www.typescriptlang.org/docs/handbook/2/mapped-types.html
          |        switch(field) {${ model.attributes.joinToString("") { attribute ->  """
              |            case ${model.itemName}FormPartFieldName.${attribute.attributeName}: return [
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

    override fun filePath(entity: UiEntityModel, model: UiItemModel): String {
      return "${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-validation.service.ts"
    }
}