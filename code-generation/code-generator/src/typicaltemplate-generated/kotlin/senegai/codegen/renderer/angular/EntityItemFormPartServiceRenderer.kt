/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartServiceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-form-part.service.ts`
 * - path: `opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part.service.ts`
 */
object EntityItemFormPartServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {${model.item.itemName.pascalCase}WTO} from "@app/wto/${model.item.itemName.kebabCase}.wto";
          |import {FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {
          |    ${model.item.itemName.pascalCase}FormPartValidationService
          |} from "@app/${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-validation.service";
          |import {
          |    ${model.item.itemName.pascalCase}FormPartInitialValueService
          |} from "@app/${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-initial-value.service";
          |import {${model.item.itemName.pascalCase}FormPartGroup} from "@app/${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-group";
          |import {${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-field-name";
          |
          |
          |${ model.item.directlyNestedItems.joinToString("") { directlyNestedItem ->  """
              |import {
              |    ${directlyNestedItem.itemName.pascalCase}FormPartService
              |} from "@app/${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${directlyNestedItem.itemName.kebabCase}-form-part/${directlyNestedItem.itemName.kebabCase}-form-part.service";
          """ } }
          |@Injectable({providedIn: 'root'})
          |export class ${model.item.itemName.pascalCase}FormPartService {
          |
          |    constructor(
          |        private ${model.item.itemName.camelCase}FormValidationService: ${model.item.itemName.pascalCase}FormPartValidationService,
          |        private ${model.item.itemName.camelCase}FormInitialValueService: ${model.item.itemName.pascalCase}FormPartInitialValueService,
          |        ${ model.item.attributeItemsFlat.joinToString("") { nestedItem ->  """
              |        private ${nestedItem.itemName.camelCase}FormPartService: ${nestedItem.itemName.pascalCase}FormPartService,
          """ } }    ) {}
          |
          |    public createInitial${model.item.itemName.pascalCase}Form(): FormGroup<${model.item.itemName.pascalCase}FormPartGroup> {
          |        return new FormGroup({
          |                        ${ model.item.attributes.joinToString("") { attribute ->  """${ if(attribute.isNullable) { """            [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull]: new FormControl<boolean>(
                  |                this.${model.item.itemName.camelCase}FormInitialValueService.${attribute.attributeName.camelCase}InitialValue() != null,
                  |                {
                  |                    nonNullable: true,
                  |                    validators: this.${model.item.itemName.camelCase}FormValidationService.validatorFunctions(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull)
                  |                },
                  |            ),
                  |            
          """ } else { """
          """ } }            [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}]: new ${attribute.angularFormControlType}(
              |                this.${model.item.itemName.camelCase}FormInitialValueService.${attribute.attributeName.camelCase}InitialValue(),
              |                {
              |                    nonNullable: true,
              |                    validators: this.${model.item.itemName.camelCase}FormValidationService.validatorFunctions(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase})
              |                },
              |            ),
              |            
          """ } }                    });
          |    }
          |
          |    /**
          |     * patchValue does not create missing FormGroups inside the FormArray.
          |     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
          |     * We need to prefill the FormArray with empty values first
          |     */
          |    public patchPreparation(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>, ${model.item.itemName.camelCase}: ${model.item.itemName.pascalCase}WTO): void {
          |        ${ model.item.attributesWithLists.joinToString("") { attribute ->  """
              |
              |        const ${attribute.attributeName.camelCase}Length = form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].controls.length
              |        if(${attribute.attributeName.camelCase}Length < ${model.item.itemName.camelCase}.${attribute.attributeName.camelCase}.length) {
              |            for (let i = ${attribute.attributeName.camelCase}Length; i < ${model.item.itemName.camelCase}.${attribute.attributeName.camelCase}.length; i++) {
              |                const ${attribute.attributeName.pascalCase}Control = this.${attribute.attributeName.pascalCase}FormPartService.createInitial${attribute.attributeName.pascalCase}Form()
              |                const articulurInterior = ${model.item.itemName.camelCase}.${attribute.attributeName.camelCase}[i]
              |                form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].push(${attribute.attributeName.pascalCase}Control)
              |                this.${attribute.attributeName.pascalCase}FormPartService.patchPreparation(${attribute.attributeName.pascalCase}Control, articulurInterior)
              |            }
              |        }
              |        
          """ } }
          |
          |        ${ model.item.attributesWithItems.joinToString("") { attribute ->  """
              |        this.${attribute.attributeName.pascalCase}FormPartService.patchPreparation(form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}], ${model.item.itemName.camelCase}.${attribute.attributeName.camelCase})
              |        
          """ } }
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.item.itemName.kebabCase}/${model.item.itemName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part.service.ts"
    }
}