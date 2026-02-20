/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartValidationServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartValidationServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {ValidatorFn, Validators} from "@angular/forms";
          |import {${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-field-name";
          |import {NamedValidator} from "@app/shared/form-controls/named-validator";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.item.itemName.pascalCase}FormPartValidationService {
          |
          |    validatorFunctions(field: ${model.item.itemName.pascalCase}FormPartFieldName): Array<ValidatorFn> {
          |        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
          |    }
          |
          |    validatorNames(field: ${model.item.itemName.pascalCase}FormPartFieldName): Array<ValidatorTranslation> {
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
          |    namedValidators(field: ${model.item.itemName.pascalCase}FormPartFieldName): ReadonlyArray<NamedValidator> {
          |        // TODO use mapped types https://www.typescriptlang.org/docs/handbook/2/mapped-types.html
          |        switch(field) {${ model.item.attributes.joinToString("") { attribute ->  """
              |            case ${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}: return [
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

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-validation.service.ts"
    }
}