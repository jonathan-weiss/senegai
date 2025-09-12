import {Injectable} from '@angular/core';
import {AbstractControl, FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthorLibraryAward} from "@app/author/author-library-award.model";
import {FormUtil} from "@app/shared/form-controls/form.util";

@Injectable({providedIn: 'root'})
export class AuthorLibraryAwardEditFormService {

    public createEmptyForm(): FormGroup {
        return new FormGroup({
            description: new FormControl('', [Validators.required]),
            year: new FormControl(null, [Validators.required, Validators.min(1900)]),
            juryList: new FormArray([]),
        });
    }

    public patchForm(form: AbstractControl, authorLibraryAward: AuthorLibraryAward): void {
        FormUtil.requiredFormControl(form, "description").patchValue(authorLibraryAward.description);
        FormUtil.requiredFormControl(form, "year").patchValue(authorLibraryAward.year);
        authorLibraryAward.juryList.forEach((jury: string) => {
            const formGroup = new FormControl(jury)
            FormUtil.requiredFormArray(form, "juryList").push(formGroup);
        })
    }

    public createAuthorFromFormData(form: AbstractControl): AuthorLibraryAward {
        return {
            description: FormUtil.requiredFormControl(form, "description").value as string,
            year: FormUtil.requiredFormControl(form, "year").value as number,
            juryList: FormUtil.requiredFormArray(form, "juryList")
                .controls.map(control => control.value as string),
        };
    }
} 
