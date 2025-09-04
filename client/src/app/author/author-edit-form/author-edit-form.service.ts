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
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {Author} from "@app/author/author.model";
import {AbstractControl, FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthorLibraryAward} from "@app/author/author-items/author-library-award/author-library-award.model";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    AuthorLibraryAwardEditFormService
} from "@app/author/author-items/author-library-award/author-library-award-form-part/author-library-award-edit-form.service";

@Injectable({providedIn: 'root'})
export class AuthorEditFormService {

    constructor(private authorLibraryAwardEditFormService: AuthorLibraryAwardEditFormService) {}

    public createEmptyForm(): FormGroup {
        return new FormGroup({
            id: new FormControl({value: '', disabled: true}), // ID is readonly
            firstname: new FormControl('', [Validators.required, Validators.minLength(2)]),
            lastname: new FormControl('', [Validators.required, Validators.minLength(2)]),
            nicknameIsNotNull: new FormControl(true),
            nickname: new FormControl(''),
            libraryAwardList: new FormArray([]),
        });
    }

    public patchForm(form: AbstractControl, author: Author): void {
        FormUtil.requiredFormControl(form, "id").patchValue(author.id);
        FormUtil.requiredFormControl(form, "firstname").patchValue(author.firstname);
        if(!author.nickname) {
            FormUtil.requiredFormControl(form, "nicknameIsNotNull").patchValue(false);
            FormUtil.requiredFormControl(form, "nickname").patchValue("");
        } else {
            FormUtil.requiredFormControl(form, "nickname").patchValue(author.nickname);
        }
        FormUtil.requiredFormControl(form, "lastname").patchValue(author.lastname);

        const libraryAwardList = FormUtil.requiredFormArray(form, "libraryAwardList");
        author.libraryAwardList.forEach((libraryAward: AuthorLibraryAward) => {
            const formGroup = this.authorLibraryAwardEditFormService.createEmptyForm()
            this.authorLibraryAwardEditFormService.patchForm(formGroup, libraryAward);
            libraryAwardList.push(formGroup);
        })
    }

    public createAuthorFromFormData(form: AbstractControl): Author {
        return {
            id: FormUtil.requiredFormControl(form, "id").value as number,
            firstname: FormUtil.requiredFormControl(form, "firstname").value as string,
            nickname: FormUtil.requiredFormControl(form, "nicknameIsNotNull").value ? FormUtil.requiredFormControl(form, "nickname").value as string : null,
            lastname: FormUtil.requiredFormControl(form, "lastname").value as string,
            libraryAwardList: FormUtil.requiredFormArray(form, "libraryAwardList")
                .controls.map(control => this.authorLibraryAwardEditFormService.createAuthorFromFormData(control)),
        };
    }
} 
