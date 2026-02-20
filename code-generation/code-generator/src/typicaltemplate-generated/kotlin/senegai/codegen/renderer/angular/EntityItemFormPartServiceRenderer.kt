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
          |import {
          |    ${model.item.itemName.pascalCase}FormPartValidationService
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-validation.service";
          |import {
          |    ${model.item.itemName.pascalCase}FormPartInitialValueService
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-initial-value.service";
          |import {${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-field-name";
          |import {${model.item.itemName.pascalCase}FormPartGroup} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-group";${ model.item.attributeItemsFlat.joinToString("") { nestedItem ->  """
              |import {${nestedItem.itemName.pascalCase}WTO} from "@app/wto/${nestedItem.itemName.kebabCase}.wto";
              |import {
              |    ${nestedItem.itemName.pascalCase}FormPartService
              |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${nestedItem.itemName.kebabCase}-form-part/${nestedItem.itemName.kebabCase}-form-part.service";
              |import {
              |    ${nestedItem.itemName.pascalCase}FormPartFieldName
              |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${nestedItem.itemName.kebabCase}-form-part/${nestedItem.itemName.kebabCase}-form-part-field-name";
          """ } }
          |
          |
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.item.itemName.pascalCase}FormPartService {
          |
          |    constructor(
          |        private ${model.item.itemName.camelCase}FormValidationService: ${model.item.itemName.pascalCase}FormPartValidationService,
          |        private ${model.item.itemName.camelCase}FormInitialValueService: ${model.item.itemName.pascalCase}FormPartInitialValueService,${ model.item.attributeItemsFlat.joinToString("") { nestedItem ->  """
              |        private ${nestedItem.itemName.camelCase}FormPartService: ${nestedItem.itemName.pascalCase}FormPartService,
          """ } }    ) {}
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
          |    /**
          |     * patchValue does not create missing FormGroups inside the FormArray.
          |     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
          |     * We need to prefill the FormArray with empty values first
          |     */
          |    public patchPreparation(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>, ${model.item.itemName.camelCase}: ${model.item.itemName.pascalCase}WTO): void {
          |        // TODO Rename libraryAwardList to awardList to bypass the name clash when replacing
          |        // TODO If the attribute is an item attribute, call the formPartService
          |        ${ model.item.attributesWithLists.joinToString("") { attribute ->  """
              |
              |        const ${attribute.attributeName.camelCase}Length = form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].controls.length
              |        if(${attribute.attributeName.camelCase}Length < ${model.item.itemName.camelCase}.${attribute.attributeName.camelCase}.length) {
              |            for (let i = ${attribute.attributeName.camelCase}Length; i < ${model.item.itemName.camelCase}.${attribute.attributeName.camelCase}.length; i++) {
              |                form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].push(this.${attribute.attributeName.pascalCase}FormPartService.createInitial${attribute.attributeName.pascalCase}Form())
              |            }
              |        }
              |        
          """ } }
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
          |                        ${ model.item.attributes.joinToString("") { attribute ->  """${ if(!attribute.isNullable) { """            ${attribute.attributeName.camelCase}: form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].getRawValue(),
          """ } else { """            ${attribute.attributeName.camelCase}: form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull].value
                  |                ? form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].getRawValue()
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