/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

    @move-comment-backward
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
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @remove-blanks-and-linebreak-after-comment

}}}@ */

import {Injectable} from '@angular/core';
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
// imports here that are ignored
import {GenderEnum} from "@app/wto/gender.enum";
import {FormGroup} from "@angular/forms";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */


@Injectable({providedIn: 'root'})
export class SilvaOptionumFormPartInitialValueService {
    /* @tt{{{ @ignore-text }}}@ */

    idInitialValue(): string {
        return ''
    }
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text }}}@ */


    /* @tt{{{ @remove-blanks-and-linebreak-before-comment
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string" replaceByExpression="attribute.typescriptAttributeFormType" ]
            [ searchValue="''" replaceByExpression="attribute.formInitialValue" ]

    }}}@  */
    firstnameInitialValue(): string {
        return ''
    }
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text }}}@ */

    nicknameInitialValue(): string | null {
        return null
    }
    articulusInteriorListInitialValue(): Array<FormGroup<ArticulusInteriorFormPartGroup>> {
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
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text }}}@ */
}
