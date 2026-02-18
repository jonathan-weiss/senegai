import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";

export class FormUtil {
    public static requiredFormControl(form: AbstractControl, formControlName: string): FormControl {
        const formControl = form.get(formControlName)
        if (formControl && formControl instanceof FormControl) {
            return formControl
        }
        throw Error("Form Control " + formControlName + " not found in form group " + form)
    }

    public static requiredFormGroup(form: AbstractControl, formControlName: string): FormGroup {
        const formControl = form.get(formControlName)
        if (formControl && formControl instanceof FormGroup) {
            return formControl
        }
        throw Error("Form Group " + formControlName + " not found in form group " + form)
    }

    public static requiredFormArray<T extends AbstractControl>(form: AbstractControl, formControlName: string): FormArray<T> {
        const formArray = form.get(formControlName)
        if (formArray && formArray instanceof FormArray) {
            return formArray as FormArray<T>
        }
        throw Error("Form Array " + formControlName + " not found in form group " + form)
    }

    public static requiredAbstractForm(form: AbstractControl, formControlName: string): AbstractControl {
        const formControl = form.get(formControlName)
        if (formControl && formControl instanceof AbstractControl) {
            return formControl
        }
        throw Error("Form Control " + formControlName + " not found in form group " + form)
    }

    public static removeControl(formArray: FormArray, control: AbstractControl): void {
        const index = formArray.controls.indexOf(control)
        formArray.removeAt(index, { emitEvent: true })
    }


    public static hasError(form: AbstractControl, controlName: string, errorName: string): boolean {
        const control = FormUtil.requiredAbstractForm(form, controlName);
        return control ? control.hasError(errorName) && control.touched : false;
    }


}
