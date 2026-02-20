/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartGroupRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {AuthorFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


export interface AuthorFormPartGroup {
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string | null" replaceByExpression="attribute.typescriptAttributeFormType" ]

    }}}@  */
    [AuthorFormPartFieldName.nickname]: FormControl<string | null>,
    /* @tt{{{ @slbc  @if [ conditionExpression="attribute.isNullable"] @slac }}}@ */
    [AuthorFormPartFieldName.nicknameIsNotNull]: FormControl<boolean>,
    /* @tt{{{ @slbc  @end-if @slac }}}@ */
    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    [AuthorFormPartFieldName.firstname]: FormControl<string>,
    [AuthorFormPartFieldName.lastname]: FormControl<string>,
    [AuthorFormPartFieldName.libraryAwardList]: FormArray<FormGroup<LibraryAwardFormPartGroup>>,
    [AuthorFormPartFieldName.birthdayIsNotNull]: FormControl<boolean>,
    [AuthorFormPartFieldName.birthday]: FormControl<Date | null>,
    [AuthorFormPartFieldName.vegetarian]: FormControl<boolean>,
    [AuthorFormPartFieldName.gender]: FormControl<GenderEnum>,
    [AuthorFormPartFieldName.id]: FormControl<string>,
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}
