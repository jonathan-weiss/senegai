/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartInitialValueServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

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
