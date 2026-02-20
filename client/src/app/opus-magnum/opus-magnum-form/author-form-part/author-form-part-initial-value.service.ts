/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartInitialValueServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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

import {Injectable} from '@angular/core';
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
// imports here that are ignored
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */


@Injectable({providedIn: 'root'})
export class AuthorFormPartInitialValueService {
    /* @tt{{{ @ignore-text }}}@ */

    idInitialValue(): string {
        return ''
    }
    /* @tt{{{ @slbc  @end-ignore-text }}}@ */


    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

    }}}@  */
    firstnameInitialValue(): string {
        return ''
    }
    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text }}}@ */

    nicknameInitialValue(): string | null {
        return null
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
    /* @tt{{{ @slbc  @end-ignore-text }}}@ */
}
