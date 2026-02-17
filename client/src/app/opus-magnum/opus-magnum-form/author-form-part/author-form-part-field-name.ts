/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartFieldNameRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="entity"
    ]

    @template-model [
        modelClassName="UiItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="entity.entityNameDashCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="entity.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="entity.entityNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

export enum AuthorFormPartFieldName {
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
    id = "id",
    libraryAwardListDescription = "libraryAwardListDescription",
    libraryAwardListYear = "libraryAwardListYear",
    libraryAwardListJuryList = "libraryAwardListJuryList",
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}

