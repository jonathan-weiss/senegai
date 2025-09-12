/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemEditFormServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemEditFormServiceRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {${model.itemName}} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}.model";
          |import {AbstractControl, FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}EditFormService {
          |
          |    constructor(    ) {}
          |
          |    public createEmptyForm(): FormGroup {
          |        return new FormGroup({
          |            id: new FormControl<number>({value: 0, disabled: true}), // ID is readonly
          |            firstname: new FormControl<string>('', [Validators.required, Validators.minLength(2)]),
          |            lastname: new FormControl<string>('', [Validators.required, Validators.minLength(2)]),
          |            nicknameIsNotNull: new FormControl<boolean>(true),
          |            nickname: new FormControl<string | null>(null),        });
          |    }
          |
          |    public patchForm(form: AbstractControl, ${model.itemNameLowercase}: ${model.itemName}): void {
          |        FormUtil.requiredFormControl(form, "id").patchValue(${model.itemNameLowercase}.id);
          |        FormUtil.requiredFormControl(form, "firstname").patchValue(${model.itemNameLowercase}.firstname);
          |        if(!${model.itemNameLowercase}.nickname) {
          |            FormUtil.requiredFormControl(form, "nicknameIsNotNull").patchValue(false);
          |            FormUtil.requiredFormControl(form, "nickname").patchValue(null);
          |        } else {
          |            FormUtil.requiredFormControl(form, "nicknameIsNotNull").patchValue(true);
          |            FormUtil.requiredFormControl(form, "nickname").patchValue(${model.itemNameLowercase}.nickname);
          |        }
          |        FormUtil.requiredFormControl(form, "lastname").patchValue(${model.itemNameLowercase}.lastname);    }
          |
          |    public create${model.itemName}FromFormData(form: AbstractControl): ${model.itemName} {
          |        return {
          |            id: FormUtil.requiredFormControl(form, "id").value as number,
          |            firstname: FormUtil.requiredFormControl(form, "firstname").value as string,
          |            nickname: FormUtil.requiredFormControl(form, "nicknameIsNotNull").value ? FormUtil.requiredFormControl(form, "nickname").value as string : null,
          |            lastname: FormUtil.requiredFormControl(form, "lastname").value as string,        };
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}-form/${model.itemNameLowercase}-edit-form.service.ts"
    }
}