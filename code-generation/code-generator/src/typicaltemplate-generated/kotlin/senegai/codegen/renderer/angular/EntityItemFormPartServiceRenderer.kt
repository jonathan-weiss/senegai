/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template `EntityItemFormPartServiceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `silva-optionum-form-part.service.ts`
 * - path: `opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part.service.ts`
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
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-validation.service";
          |import {
          |    ${model.item.itemName.pascalCase}FormPartInitialValueService
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-initial-value.service";
          |import {${model.item.itemName.pascalCase}FormPartGroup} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-group";
          |import {${model.item.itemName.pascalCase}FormPartFieldName} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part-field-name";
          |
          |
          |${ model.item.directlyNestedItems.joinToString("") { nestedItem ->  """
              |import {
              |    ${nestedItem.itemName.pascalCase}FormPartService
              |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${nestedItem.itemName.kebabCase}-form-part/${nestedItem.itemName.kebabCase}-form-part.service";
          """ } }
          |@Injectable({providedIn: 'root'})
          |export class ${model.item.itemName.pascalCase}FormPartService {
          |
          |    constructor(
          |        private ${model.item.itemName.camelCase}FormValidationService: ${model.item.itemName.pascalCase}FormPartValidationService,
          |        private ${model.item.itemName.camelCase}FormInitialValueService: ${model.item.itemName.pascalCase}FormPartInitialValueService,
          |        ${ model.item.directlyNestedItems.joinToString("") { nestedItem ->  """
              |        private ${nestedItem.itemName.camelCase}FormPartService: ${nestedItem.itemName.pascalCase}FormPartService,
          """ } }    ) {}
          |
          |    public createInitial${model.item.itemName.pascalCase}Form(): FormGroup<${model.item.itemName.pascalCase}FormPartGroup> {
          |        return new FormGroup({
          |                        ${ model.item.attributes.joinToString("") { attribute ->  """${ if(!attribute.isItem) { """            [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}]: new ${attribute.angularFormControlType}(
                  |                this.${model.item.itemName.camelCase}FormInitialValueService.${attribute.attributeName.camelCase}InitialValue(),
                  |                {
                  |                    nonNullable: true,
                  |                    validators: this.${model.item.itemName.camelCase}FormValidationService.validatorFunctions(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase})
                  |                },
                  |            ),
                  |            
          """ } else { """
          """ } }            
          """ } }
          |            ${ model.item.attributesWithItem.filter { !it.attribute.isList }.joinToString("") { attributeWithItem ->  """
              |
              |            [${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}]: this.${attributeWithItem.type.item.itemName.camelCase}FormPartService.createInitial${attributeWithItem.type.item.itemName.pascalCase}Form(),
          """ } }            ${ model.item.attributesWithItem.filter { it.attribute.isList }.joinToString("") { attributeWithItem ->  """
              |            [${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}]: new FormArray(
              |                this.${model.item.itemName.camelCase}FormInitialValueService.${attributeWithItem.attribute.attributeName.camelCase}InitialValue(),
              |                {
              |                    validators: this.${model.item.itemName.camelCase}FormValidationService.validatorFunctions(${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase})
              |                },
              |            ),
          """ } }            
          |            // ------------------------
          |            // All isNotNull controls
          |            // ------------------------
          |
          |            ${ model.item.attributes.joinToString("") { attribute ->  """${ if(attribute.isNullable) { """            [${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull]: new FormControl<boolean>(
                  |                false,
                  |                {
                  |                    nonNullable: true,
                  |                    validators: this.${model.item.itemName.camelCase}FormValidationService.validatorFunctions(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull)
                  |                },
                  |            ),
                  |            
          """ } else { """
          """ } }            
          """ } }        });
          |    }
          |
          |    public patch${model.item.itemName.pascalCase}Form(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>, ${model.item.itemName.camelCase}: ${model.item.itemName.pascalCase}WTO): void {
          |        this.patchPreparation(form, ${model.item.itemName.camelCase});
          |
          |        ${ model.item.attributes.joinToString("") { attribute ->  """        ${ if(!attribute.isNullable) { """        form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].patchValue(${model.item.itemName.camelCase}.${attribute.attributeName.camelCase});
                  |        
          """ } else { """
          """ } }        ${ if(attribute.isNullable) { """        if(${model.item.itemName.camelCase}.${attribute.attributeName.camelCase} != null) {
                  |            form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull].patchValue(true);
                  |            form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].patchValue(${model.item.itemName.camelCase}.${attribute.attributeName.camelCase});
                  |        } else {
                  |            form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull].patchValue(false);
                  |        }
                  |        
          """ } else { """
          """ } }        
          """ } }        
          |        this.patchNestedItems(form, ${model.item.itemName.camelCase});
          |    }
          |
          |    /**
          |     * patchValue does not create missing FormGroups inside the FormArray.
          |     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
          |     * We need to prefill the FormArray with empty values first
          |     */
          |    private patchPreparation(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>, ${model.item.itemName.camelCase}: ${model.item.itemName.pascalCase}WTO): void {
          |        ${ model.item.attributesWithItem.filter { it.attribute.isList }.joinToString("") { attributeWithItem ->  """
              |        if(${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase} != null) {
              |            const ${attributeWithItem.attribute.attributeName.camelCase}Length = form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}].controls.length
              |            if (${attributeWithItem.attribute.attributeName.camelCase}Length < ${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase}.length) {
              |                for (let i = ${attributeWithItem.attribute.attributeName.camelCase}Length; i < ${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase}.length; i++) {
              |                    form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}].push(this.${attributeWithItem.type.item.itemName.camelCase}FormPartService.createInitial${attributeWithItem.type.item.itemName.pascalCase}Form())
              |                }
              |            }
              |        }
              |        
          """ } }    }
          |
          |
          |    private patchNestedItems(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>, ${model.item.itemName.camelCase}: ${model.item.itemName.pascalCase}WTO): void {
          |        ${ model.item.attributesWithItem.filter { it.attribute.isList }.joinToString("") { attributeWithItem ->  """
              |
              |        if(${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase} != null) {
              |            const ${attributeWithItem.attribute.attributeName.camelCase}Length = form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}].controls.length
              |            if(${attributeWithItem.attribute.attributeName.camelCase}Length < ${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase}.length) {
              |                for (let i = ${attributeWithItem.attribute.attributeName.camelCase}Length; i < ${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase}.length; i++) {
              |                    this.${attributeWithItem.type.item.itemName.camelCase}FormPartService.patch${attributeWithItem.type.item.itemName.pascalCase}Form(
              |                        form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}].at(i),
              |                        ${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase}[i]
              |                    )
              |                }
              |            }
              |        }
              |        
          """ } }
          |
          |        ${ model.item.attributesWithItem.filter { !it.attribute.isList }.joinToString("") { attributeWithItem ->  """
              |        ${ if(attributeWithItem.attribute.isNullable) { """
                  |        if(${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase} != null) {
                  |            this.${attributeWithItem.type.item.itemName.camelCase}FormPartService.patch${attributeWithItem.type.item.itemName.pascalCase}Form(form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}], ${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase})
                  |        }
                  |        
          """ } else { """
                  |        this.${attributeWithItem.type.item.itemName.camelCase}FormPartService.patch${attributeWithItem.type.item.itemName.pascalCase}Form(form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attributeWithItem.attribute.attributeName.camelCase}], ${model.item.itemName.camelCase}.${attributeWithItem.attribute.attributeName.camelCase})
                  |        
          """ } }
              |        
          """ } }    }
          |
          |    public create${model.item.itemName.pascalCase}WTOFromForm(form: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>): ${model.item.itemName.pascalCase}WTO {
          |        return {
          |            ${ model.item.attributes.joinToString("") { attribute ->  """            ${ if(!attribute.isNullable) { """            ${attribute.attributeName.camelCase}: form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].getRawValue(),
                  |            
          """ } else { """            ${attribute.attributeName.camelCase}: form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull].value
                  |                ? form.controls[${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}].getRawValue()
                  |                : null,
                  |            
          """ } }            
          """ } }                    };
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.kebabCase}-form-part/${model.item.itemName.kebabCase}-form-part.service.ts"
    }
}