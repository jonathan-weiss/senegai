/* @tt{{{
    #expand-comment [ direction="backward" strip="linebreak"]

    @template-renderer [
        templateRendererClassName="EntityItemFormPartInitialValueServiceRenderer"
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

import {Injectable} from '@angular/core';
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
// imports here that are ignored
import {GenderEnum} from "@app/wto/gender.enum";
import {FormGroup} from "@angular/forms";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormPartInitialValueService {
    /* @tt{{{ @ignore-text }}}@ */

    idInitialValue(): string {
        return ''
    }
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text }}}@ */


    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string" replaceByExpression="attribute.typescriptAttributeFormType" ]
            [ searchValue="''" replaceByExpression="attribute.formInitialValue" ]

    }}}@  */
    firstnameInitialValue(): string {
        return ''
    }
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"] @end-foreach #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text }}}@ */

    nicknameInitialValue(): string | null {
        return null
    }
    libraryAwardListInitialValue(): Array<FormGroup<LibraryAwardFormPartGroup>> {
        return []
    }
    lastnameInitialValue(): string {
        return ''
    }
    birthdayInitialValue(): Date {
        return new Date();
    }
    vegetarianInitialValue(): boolean {
        return false;
    }
    genderInitialValue(): GenderEnum {
        return GenderEnum.FEMALE;
    }
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text }}}@ */
}
