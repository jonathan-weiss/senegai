/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartFieldNameRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
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

    @slac

}}}@ */

/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

export enum AuthorFormPartFieldName {
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]

    }}}@  */
    nickname = "nickname",
    /* @tt{{{ @slbc  @if [ conditionExpression="attribute.isNullable"] @slac }}}@ */
    nicknameIsNotNull = "nicknameIsNotNull",
    /* @tt{{{ @slbc  @end-if @slac }}}@ */
/* @tt{{{ @slbc @end-foreach @slac }}}@ */
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    firstname = "firstname",
    lastname = "lastname",
    libraryAwardList = "libraryAwardList",
    birthdayIsNotNull = "birthdayIsNotNull",
    birthday = "birthday",
    vegetarian = "vegetarian",
    gender = "gender",
    id = "id",
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}

