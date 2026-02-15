/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemFormFieldNameRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {GenderEnum} from "@app/author/gender.enum";

export enum AuthorFormFieldName {
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

    }}}@  */
    firstname = "firstname",
/* @tt{{{ @slbc @end-foreach @slac }}}@ */
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    nicknameIsNotNull = "nicknameIsNotNull",
    nickname = "nickname",
    lastname = "lastname",
    libraryAwardList = "libraryAwardList",
    libraryAwardListDescription = "libraryAwardListDescription",
    libraryAwardListYear = "libraryAwardListYear",
    libraryAwardListJuryList = "libraryAwardListJuryList",
    birthdayIsNotNull = "birthdayIsNotNull",
    birthday = "birthday",
    vegetarian = "vegetarian",
    gender = "gender",
    id = "id",
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}

/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
export interface AuthorFormGroup {
    [AuthorFormFieldName.id]: FormControl<number>,
    [AuthorFormFieldName.firstname]: FormControl<string>,
    [AuthorFormFieldName.nicknameIsNotNull]: FormControl<boolean>,
    [AuthorFormFieldName.nickname]: FormControl<string | null>,
    [AuthorFormFieldName.lastname]: FormControl<string>,
    [AuthorFormFieldName.libraryAwardList]: FormArray<FormGroup<AuthorFormLibraryAwardListFormGroup>>,
    [AuthorFormFieldName.birthdayIsNotNull]: FormControl<boolean>,
    [AuthorFormFieldName.birthday]: FormControl<Date | null>,
    [AuthorFormFieldName.vegetarian]: FormControl<boolean>,
    [AuthorFormFieldName.gender]: FormControl<GenderEnum>,
}

export interface AuthorFormLibraryAwardListFormGroup {
    [AuthorFormFieldName.libraryAwardListDescription]: FormControl<string>,
    [AuthorFormFieldName.libraryAwardListYear]: FormControl<number>,
    [AuthorFormFieldName.libraryAwardListJuryList]: FormArray<FormControl<string>>,
}

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
