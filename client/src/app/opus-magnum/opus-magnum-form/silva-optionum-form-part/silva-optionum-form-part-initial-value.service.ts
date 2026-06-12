/* @tt{{{
    @rlb

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
      [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
      [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]
      [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {Injectable} from '@angular/core';
import {FormGroup} from "@angular/forms";

/* @tt{{{ @rlb
    @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="directlyNestedItem" ]

    @replace-value-by-expression
        [ searchValue="ArticulusInterior" replaceByExpression="directlyNestedItem.itemName.pascalCase" ]
        [ searchValue="articulus-interior" replaceByExpression="directlyNestedItem.itemName.kebabCase" ]

}}}@  */

import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
/* @tt{{{ @rlb @end-foreach @rla }}}@ */

/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
// imports here that are ignored
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */


@Injectable({providedIn: 'root'})
export class SilvaOptionumFormPartInitialValueService {
    /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributesWithAngularFormInitialValues" loopVariable="attribute" ]
        @replace-value-by-expression
            [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string" replaceByExpression="attribute.angularInitialValueFormType" ]
            [ searchValue="''" replaceByExpression="attribute.angularFormInitialValue" ]

    }}}@  */
    campusTextusObligatoriusInitialValue(): string {
        return ''
    }
    /* @tt{{{ @rlb @end-foreach @rla }}}@ */
    /* @tt{{{ @rlb  @ignore-text }}}@ */

    campusTextusOptionalisInitialValue(): string | null {
        return null
    }
    articulusInteriorListInitialValue(): Array<FormGroup<ArticulusInteriorFormPartGroup>> {
        return []
    }
    campusDieiInitialValue(): Date {
        return new Date();
    }
    campusBivalensInitialValue(): boolean {
        return false;
    }
    campusNumerorumInitialValue(): number {
        return 0;
    }
    appellatioInitialValue(): AppellatioEnum {
        return AppellatioEnum.MATRONA;
    }
    indexUnicusInitialValue(): string {
        return crypto.randomUUID()
    }
    /* @tt{{{ @rlb  @end-ignore-text }}}@ */
}
