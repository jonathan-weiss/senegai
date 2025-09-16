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
    birthdayIsNotNull = "birthdayIsNotNull",
    birthday = "birthday",
    vegetarian = "vegetarian",
    gender = "gender",
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}
