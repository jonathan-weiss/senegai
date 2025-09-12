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

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {Author} from "@app/author/author.model";
import {AbstractControl, FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthorLibraryAward} from "@app/author/author-library-award.model";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    AuthorLibraryAwardEditFormService
} from "@app/author/author-form/author-library-award-form-part/author-library-award-edit-form.service";
import {GenderEnum} from "@app/author/gender.enum";

@Injectable({providedIn: 'root'})
export class AuthorEditFormService {

    constructor(private authorLibraryAwardEditFormService: AuthorLibraryAwardEditFormService) {}

    public createEmptyForm(): FormGroup {
        return new FormGroup({
            id: new FormControl<number>({value: 0, disabled: true}), // ID is readonly
            firstname: new FormControl<string>('', [Validators.required, Validators.minLength(2)]),
            lastname: new FormControl<string>('', [Validators.required, Validators.minLength(2)]),
            nicknameIsNotNull: new FormControl<boolean>(true),
            nickname: new FormControl<string | null>(null),
            libraryAwardList: new FormArray([]),
            birthdayIsNotNull: new FormControl<boolean>(true),
            birthday: new FormControl<Date | null>(null),
            vegetarian: new FormControl<boolean>(false),
            gender: new FormControl<GenderEnum>(GenderEnum.FEMALE),
        });
    }

    public patchForm(form: AbstractControl, author: Author): void {
        FormUtil.requiredFormControl(form, "id").patchValue(author.id);
        FormUtil.requiredFormControl(form, "firstname").patchValue(author.firstname);
        if(!author.nickname) {
            FormUtil.requiredFormControl(form, "nicknameIsNotNull").patchValue(false);
            FormUtil.requiredFormControl(form, "nickname").patchValue(null);
        } else {
            FormUtil.requiredFormControl(form, "nicknameIsNotNull").patchValue(true);
            FormUtil.requiredFormControl(form, "nickname").patchValue(author.nickname);
        }
        FormUtil.requiredFormControl(form, "lastname").patchValue(author.lastname);

        const libraryAwardList = FormUtil.requiredFormArray(form, "libraryAwardList");
        author.libraryAwardList.forEach((libraryAward: AuthorLibraryAward) => {
            const formGroup = this.authorLibraryAwardEditFormService.createEmptyForm()
            this.authorLibraryAwardEditFormService.patchForm(formGroup, libraryAward);
            libraryAwardList.push(formGroup);
        })

        if(!author.birthday) {
            FormUtil.requiredFormControl(form, "birthdayIsNotNull").patchValue(false);
            FormUtil.requiredFormControl(form, "birthday").patchValue(null);
        } else {
            FormUtil.requiredFormControl(form, "birthdayIsNotNull").patchValue(true);
            FormUtil.requiredFormControl(form, "birthday").patchValue(author.birthday);
        }
        FormUtil.requiredFormControl(form, "vegetarian").patchValue(author.vegetarian);
        FormUtil.requiredFormControl(form, "gender").patchValue(author.gender);

    }

    public createAuthorFromFormData(form: AbstractControl): Author {
        return {
            id: FormUtil.requiredFormControl(form, "id").value as number,
            firstname: FormUtil.requiredFormControl(form, "firstname").value as string,
            nickname: FormUtil.requiredFormControl(form, "nicknameIsNotNull").value ? FormUtil.requiredFormControl(form, "nickname").value as string : null,
            lastname: FormUtil.requiredFormControl(form, "lastname").value as string,
            libraryAwardList: FormUtil.requiredFormArray(form, "libraryAwardList")
                .controls.map(control => this.authorLibraryAwardEditFormService.createAuthorFromFormData(control)),
            birthday: FormUtil.requiredFormControl(form, "birthdayIsNotNull").value ? FormUtil.requiredFormControl(form, "birthday").value as Date : null,
            vegetarian: FormUtil.requiredFormControl(form, "vegetarian").value as boolean,
            gender: FormUtil.requiredFormControl(form, "gender").value as GenderEnum,
        };
    }
} 
