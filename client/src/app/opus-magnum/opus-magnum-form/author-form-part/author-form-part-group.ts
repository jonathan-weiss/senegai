/* @tt{{{
    #expand-comment [ expandDirection="backward" strip="linebreak"]

    #move-comment [ direction="backward" ]
    @template-renderer [
        templateRendererClassName="EntityItemFormPartGroupRenderer"
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

    #expand-comment [ expandDirection="forward" strip="linebreak"]

}}}@ */

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {AuthorFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */


export interface AuthorFormPartGroup {
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string | null" replaceByExpression="attribute.typescriptAttributeFormType" ]

    }}}@  */
    [AuthorFormPartFieldName.nickname]: FormControl<string | null>,
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @if [ conditionExpression="attribute.isNullable"] #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    [AuthorFormPartFieldName.nicknameIsNotNull]: FormControl<boolean>,
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-if #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"] @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    [AuthorFormPartFieldName.firstname]: FormControl<string>,
    [AuthorFormPartFieldName.lastname]: FormControl<string>,
    [AuthorFormPartFieldName.libraryAwardList]: FormArray<FormGroup<LibraryAwardFormPartGroup>>,
    [AuthorFormPartFieldName.birthdayIsNotNull]: FormControl<boolean>,
    [AuthorFormPartFieldName.birthday]: FormControl<Date | null>,
    [AuthorFormPartFieldName.vegetarian]: FormControl<boolean>,
    [AuthorFormPartFieldName.gender]: FormControl<GenderEnum>,
    [AuthorFormPartFieldName.id]: FormControl<string>,
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
}
