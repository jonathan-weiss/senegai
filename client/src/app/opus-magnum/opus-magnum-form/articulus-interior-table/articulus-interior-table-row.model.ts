/* @tt{{{

    @rlb

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityItemTableRowComponentTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="articulus-interior" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="articulusInterior" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="ArticulusInterior" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {FormGroup} from "@angular/forms";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";

export interface ArticulusInteriorTableRow {
    /*
    @tt{{{
        @rlb
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
        [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="Description" replaceByExpression="attribute.attributeName.pascalCase" ]
        @rla
    }}}@
     */
    description: string
    /* @tt{{{ @rlb  @end-foreach @rla }}}@ */
    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    year: number
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
    formGroup: FormGroup<ArticulusInteriorFormPartGroup>
}
