/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template EntityItemFormPartServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartServiceRenderer : UiEntityItemRenderer {

    override fun renderTemplate(entity: UiEntityModel, model: UiItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {${model.itemName}WTO} from "@app/wto/${model.itemNameLowercase}.wto";
          |import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {
          |    ${model.itemName}FormPartValidationService
          |} from "@app/${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-validation.service";
          |import {
          |    ${model.itemName}FormPartInitialValueService
          |} from "@app/${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-initial-value.service";
          |import {${model.itemName}FormPartFieldName} from "@app/${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-field-name";
          |import {${model.itemName}FormPartGroup} from "@app/${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part-group";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}FormPartService {
          |
          |    constructor(
          |        private ${model.itemNameLowercase}FormValidationService: ${model.itemName}FormPartValidationService,
          |        private ${model.itemNameLowercase}FormInitialValueService: ${model.itemName}FormPartInitialValueService,    ) {}
          |
          |    public createInitial${model.itemName}Form(): FormGroup<${model.itemName}FormPartGroup> {
          |        return new FormGroup({${ model.attributes.joinToString("") { attribute ->  """
              |            [${model.itemName}FormPartFieldName.${attribute.attributeName}]: new FormControl<string>(
              |                this.${model.itemNameLowercase}FormInitialValueService.${attribute.attributeName}InitialValue(),
              |                {
              |                    nonNullable: true,
              |                    validators: this.${model.itemNameLowercase}FormValidationService.validatorFunctions(${model.itemName}FormPartFieldName.${attribute.attributeName})
              |                },
              |            ),
          """ } }        });
          |    }
          |
          |    public patch${model.itemName}Form(form: FormGroup<${model.itemName}FormPartGroup>, ${model.itemNameLowercase}: ${model.itemName}WTO): void {        ${ model.attributes.joinToString("") { attribute ->  """        FormUtil.requiredFormControl(form, ${model.itemName}FormPartFieldName.${attribute.attributeName}).patchValue(${model.itemNameLowercase}.${attribute.attributeName});
          """ } }    }
          |
          |    public create${model.itemName}FromFormData(form: AbstractControl): ${model.itemName}WTO {
          |        return {
          |                        ${ model.attributes.joinToString("") { attribute ->  """            ${attribute.attributeName}: FormUtil.requiredFormControl(form, ${model.itemName}FormPartFieldName.${attribute.attributeName}).value as string,
          """ } }        };
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(entity: UiEntityModel, model: UiItemModel): String {
      return "${entity.entityNameDashCase}/${entity.entityNameDashCase}-form/${model.itemNameLowercase}-form-part/${model.itemNameLowercase}-form-part.service.ts"
    }
}