/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

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

    @remove-blanks-and-linebreak-after-comment

}}}@ */

import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {SilvaOptionumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-field-name";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";

/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */


export interface SilvaOptionumFormPartGroup {
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string | null" replaceByExpression="attribute.typescriptAttributeFormType" ]

    }}}@  */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalis]: FormControl<string | null>,
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]: FormControl<boolean>,
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    [SilvaOptionumFormPartFieldName.campusTextusObligatorius]: FormControl<string>,
    [SilvaOptionumFormPartFieldName.articulusInteriorList]: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>,
    [SilvaOptionumFormPartFieldName.campusDieiIsNotNull]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.campusDiei]: FormControl<Date | null>,
    [SilvaOptionumFormPartFieldName.campusBivalens]: FormControl<boolean>,
    [SilvaOptionumFormPartFieldName.appellatio]: FormControl<AppellatioEnum>,
    [SilvaOptionumFormPartFieldName.id]: FormControl<string>,
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
}
