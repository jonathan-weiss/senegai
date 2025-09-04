/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemService" templateRendererPackageName="senegai.codegen.renderer.angular" ]

    @template-model [
    modelClassName="ItemModel"
    modelPackageName="senegai.codegen.renderer.model"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthorLibraryAward} from "@app/author/author-items/author-library-award/author-library-award.model";
import {FormUtil} from "@app/shared/form-controls/form.util";

@Injectable({providedIn: 'root'})
export class AuthorLibraryAwardEditFormService {

    public createEmptyForm(): FormGroup {
        return new FormGroup({
            description: new FormControl('', [Validators.required]),
            year: new FormControl(null, [Validators.required, Validators.min(1900)]),
        });
    }

    public patchForm(form: AbstractControl, authorLibraryAward: AuthorLibraryAward): void {
        FormUtil.requiredFormControl(form, "description").patchValue(authorLibraryAward.description);
        FormUtil.requiredFormControl(form, "year").patchValue(authorLibraryAward.year);
    }

    public createAuthorFromFormData(form: AbstractControl): AuthorLibraryAward {
        return {
            description: FormUtil.requiredFormControl(form, "description").value as string,
            year: FormUtil.requiredFormControl(form, "year").value as number,
        };
    }
} 
