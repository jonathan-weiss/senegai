/* @tt{{{
    @rlb

    @move-comment-backward
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
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {SilvaOptionumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-field-name";
/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";

/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */


export interface SilvaOptionumFormPartGroup {
    /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string | null" replaceByExpression="attribute.typescriptAttributeFormType" ]

    }}}@  */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: FormControl<string | null>,
    /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: FormControl<boolean>,
    /* @tt{{{ @rlb  @end-if @rla }}}@ */
    /* @tt{{{ @rlb @end-foreach @rla }}}@ */
    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusObligatorius]: FormControl<string>,
    [SilvaOptionumFormPartFieldName.articulusInteriorSingularis]: FormGroup<ArticulusInteriorFormPartGroup>,
    [SilvaOptionumFormPartFieldName.articulusInteriorList]: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>,
    [SilvaOptionumFormPartFieldName.campusDieiIsNotNull]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.campusDiei]: FormControl<Date | null>,
    [SilvaOptionumFormPartFieldName.campusBivalens]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.appellatio]: FormControl<AppellatioEnum>,
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
}
