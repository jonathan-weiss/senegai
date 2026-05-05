/* @tt{{{
    #expand-comment [ direction="backward" strip="linebreak"]

    #move-comment [ direction="backward" ]
    @template-renderer [
        templateRendererClassName="EntityItemFormPartFieldNameRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="author" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    #expand-comment [ direction="forward" strip="linebreak"]

}}}@ */

/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */

export enum AuthorFormPartFieldName {
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]

    }}}@  */
    nickname = "nickname",
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @if [ conditionExpression="attribute.isNullable"] #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
    nicknameIsNotNull = "nicknameIsNotNull",
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-if #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"] @end-foreach #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
    firstname = "firstname",
    lastname = "lastname",
    libraryAwardList = "libraryAwardList",
    birthdayIsNotNull = "birthdayIsNotNull",
    birthday = "birthday",
    vegetarian = "vegetarian",
    gender = "gender",
    id = "id",
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
}

