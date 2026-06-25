/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartValidationServiceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-silva-optionum-form-part-validation.service.ts`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-silva-optionum-form-part/opus-magnum-silva-optionum-form-part-validation.service.ts`
 */
object EntityItemFormPartValidationServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {Inject, Injectable, InjectionToken} from '@angular/core';
          |import {ValidatorFn, Validators} from "@angular/forms";
          |import {${model.entity.entityName.pascalCase}${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.entity.entityName.kebabCase}-${model.item.itemName.kebabCase}-form-part/${model.entity.entityName.kebabCase}-${model.item.itemName.kebabCase}-form-part-field-name";
          |import {NamedValidator} from "@app/shared/form-controls/named-validator";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |
          |${ model.item.attributesWithCustomValidation.joinToString("") { attributeWithCustomValidation ->  """export const ${attributeWithCustomValidation.attributeName.pascalCase}NamedValidator = new InjectionToken<NamedValidator>('${attributeWithCustomValidation.attributeName.pascalCase}NamedValidator');
              |""" } }
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entity.entityName.pascalCase}${model.item.itemName.pascalCase}FormPartValidationService {
          |
          |${ if(model.item.attributesWithCustomValidation.isNotEmpty()) { """    constructor(
              |${ model.item.attributesWithCustomValidation.joinToString("") { attributeWithCustomValidation ->  """        @Inject(${attributeWithCustomValidation.attributeName.pascalCase}NamedValidator) private ${attributeWithCustomValidation.attributeName.camelCase}NamedValidator: NamedValidator,
                  |""" } }    ) {}
              |""" } else { """""" } }
          |    validatorFunctions(field: ${model.entity.entityName.pascalCase}${model.item.itemName.pascalCase}FormPartFieldName): Array<ValidatorFn> {
          |        return this.namedValidators(field).map(namedValidator => namedValidator.validatorFunction)
          |    }
          |
          |    validatorNames(field: ${model.entity.entityName.pascalCase}${model.item.itemName.pascalCase}FormPartFieldName): Array<ValidatorTranslation> {
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
          |    /**
          |     * t(validator.required)
          |     */
          |    namedValidators(field: ${model.entity.entityName.pascalCase}${model.item.itemName.pascalCase}FormPartFieldName): ReadonlyArray<NamedValidator> {
          |        switch(field) {
          |${ model.item.attributes.joinToString("") { attribute ->  """            case ${model.entity.entityName.pascalCase}${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}: return [
              |                {
              |                    validatorName: "required",
              |                    validatorFunction: Validators.required,
              |                    validatorTranslationKey: "validator.required",
              |                },
              |${ if(attribute.hasCustomValidation) { """                this.${attribute.attributeName.camelCase}NamedValidator,
                  |""" } else { """""" } }            ]
              |""" } }            default: return []
          |        }
          |    };
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.entity.entityName.kebabCase}-${model.item.itemName.kebabCase}-form-part/${model.entity.entityName.kebabCase}-${model.item.itemName.kebabCase}-form-part-validation.service.ts"
    }
}