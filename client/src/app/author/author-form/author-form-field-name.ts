/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityFormFieldNameRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
    modelClassName="UiEntityModel"
    modelPackageName="senegai.codegen.renderer.model.ui"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.entityName" ]
        [ searchValue="author" replaceByExpression="model.entityNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {FormArray, FormControl, FormGroup} from "@angular/forms";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

export enum AuthorFormFieldName {
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.chainedFormAttributes" loopVariable="attribute" ]

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


export interface AuthorFormGroup {
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.chainedFormAttributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

    }}}@  */
    [AuthorFormFieldName.firstname]: FormControl<string>,
    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    [AuthorFormFieldName.nicknameIsNotNull]: FormControl<boolean>,
    [AuthorFormFieldName.nickname]: FormControl<string | null>,
    [AuthorFormFieldName.lastname]: FormControl<string>,
    [AuthorFormFieldName.libraryAwardList]: FormArray<FormGroup<AuthorFormLibraryAwardListFormGroup>>,
    [AuthorFormFieldName.birthdayIsNotNull]: FormControl<boolean>,
    [AuthorFormFieldName.birthday]: FormControl<Date | null>,
    [AuthorFormFieldName.vegetarian]: FormControl<boolean>,
    [AuthorFormFieldName.gender]: FormControl<GenderEnum>,
    [AuthorFormFieldName.id]: FormControl<string>,
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}

/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
export interface AuthorFormLibraryAwardListFormGroup {
    [AuthorFormFieldName.libraryAwardListDescription]: FormControl<string>,
    [AuthorFormFieldName.libraryAwardListYear]: FormControl<number>,
    [AuthorFormFieldName.libraryAwardListJuryList]: FormArray<FormControl<string>>,
}

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
