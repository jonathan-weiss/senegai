/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {${model.item.itemName.pascalCase}WTO} from "@app/wto/${model.item.itemName.camelCase}.wto";
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {
          |    ${model.item.itemName.pascalCase}FormPartValidationService
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-validation.service";
          |import {
          |    ${model.item.itemName.pascalCase}FormPartInitialValueService
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-initial-value.service";
          |import {${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-field-name";
          |import {${model.item.itemName.pascalCase}FormPartGroup} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-group";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.item.itemName.pascalCase}FormPartService {
          |
          |    constructor(
          |        private ${model.item.itemName.camelCase}FormValidationService: ${model.item.itemName.pascalCase}FormPartValidationService,
          |        private ${model.item.itemName.camelCase}FormInitialValueService: ${model.item.itemName.pascalCase}FormPartInitialValueService,    ) {}
          |
          |    public createInitial${model.item.itemName.pascalCase}Form(): FormGroup<${model.item.itemName.pascalCase}FormPartGroup> {
          |        return new FormGroup({${ model.item.attributes.joinToString("") { attribute ->  """${ if(attribute.isNullable) { """            [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull]: new FormControl<boolean>(
                  |                this.${model.item.itemName.camelCase}FormInitialValueService.${attribute.attributeName.camelCase}InitialValue() != null,
                  |                {
                  |                    nonNullable: true,
                  |                    validators: this.${model.item.itemName.camelCase}FormValidationService.validatorFunctions(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull)
                  |                },
                  |            ),
          """ } else { """
          """ } }            [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}]: new ${attribute.typescriptAttributeFormControlType}(
              |                this.${model.item.itemName.camelCase}FormInitialValueService.${attribute.attributeName.camelCase}InitialValue(),
              |                {
              |                    nonNullable: true,
              |                    validators: this.${model.item.itemName.camelCase}FormValidationService.validatorFunctions(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase})
              |                },
              |            ),
          """ } }        });
          |    }
          |
          |    public patch${model.item.itemName.pascalCase}Form(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>, ${model.item.itemName.camelCase}: ${model.item.itemName.pascalCase}WTO): void {
          |        ${ model.item.attributes.joinToString("") { attribute ->  """${ if(attribute.isNullable) { """        form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull].patchValue(!${model.item.itemName.camelCase}.${attribute.attributeName.camelCase});
          """ } else { """
          """ } }        form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].patchValue(${model.item.itemName.camelCase}.${attribute.attributeName.camelCase} ?? null);
          """ } }    }
          |
          |    public create${model.item.itemName.pascalCase}FromFormData(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>): ${model.item.itemName.pascalCase}WTO {
          |        return {
          |                        ${ model.item.attributes.joinToString("") { attribute ->  """${ if(!attribute.isNullable) { """            ${attribute.attributeName.camelCase}: form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].value,
          """ } else { """            ${attribute.attributeName.camelCase}: form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull].value
                  |                ? form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].value
                  |                : null,
          """ } }
          """ } }        };
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part.service.ts"
    }
}