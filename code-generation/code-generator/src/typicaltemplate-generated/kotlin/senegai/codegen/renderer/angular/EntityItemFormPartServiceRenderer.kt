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
          |import {${model.item.itemName}WTO} from "@app/wto/${model.item.itemNameLowercase}.wto";
          |import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {
          |    ${model.item.itemName}FormPartValidationService
          |} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-validation.service";
          |import {
          |    ${model.item.itemName}FormPartInitialValueService
          |} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-initial-value.service";
          |import {${model.item.itemName}FormPartFieldName} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-field-name";
          |import {${model.item.itemName}FormPartGroup} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-group";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.item.itemName}FormPartService {
          |
          |    constructor(
          |        private ${model.item.itemNameLowercase}FormValidationService: ${model.item.itemName}FormPartValidationService,
          |        private ${model.item.itemNameLowercase}FormInitialValueService: ${model.item.itemName}FormPartInitialValueService,    ) {}
          |
          |    public createInitial${model.item.itemName}Form(): FormGroup<${model.item.itemName}FormPartGroup> {
          |        return new FormGroup({${ model.item.attributes.joinToString("") { attribute ->  """
              |            [${model.item.itemName}FormPartFieldName.${attribute.attributeName}]: new FormControl<string>(
              |                this.${model.item.itemNameLowercase}FormInitialValueService.${attribute.attributeName}InitialValue(),
              |                {
              |                    nonNullable: true,
              |                    validators: this.${model.item.itemNameLowercase}FormValidationService.validatorFunctions(${model.item.itemName}FormPartFieldName.${attribute.attributeName})
              |                },
              |            ),
          """ } }        });
          |    }
          |
          |    public patch${model.item.itemName}Form(form: FormGroup<${model.item.itemName}FormPartGroup>, ${model.item.itemNameLowercase}: ${model.item.itemName}WTO): void {        ${ model.item.attributes.joinToString("") { attribute ->  """        FormUtil.requiredFormControl(form, ${model.item.itemName}FormPartFieldName.${attribute.attributeName}).patchValue(${model.item.itemNameLowercase}.${attribute.attributeName});
          """ } }    }
          |
          |    public create${model.item.itemName}FromFormData(form: AbstractControl): ${model.item.itemName}WTO {
          |        return {
          |                        ${ model.item.attributes.joinToString("") { attribute ->  """            ${attribute.attributeName}: FormUtil.requiredFormControl(form, ${model.item.itemName}FormPartFieldName.${attribute.attributeName}).value as string,
          """ } }        };
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part.service.ts"
    }
}