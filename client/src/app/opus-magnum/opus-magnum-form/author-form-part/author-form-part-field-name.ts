/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartFieldNameRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.item.itemName" ]
        [ searchValue="author" replaceByExpression="model.item.itemNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityNameDashCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

export enum AuthorFormPartFieldName {
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

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
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}

