/* @tt{{{

    @remove-blanks-and-linebreak-before-comment

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

    @remove-blanks-and-linebreak-after-comment

}}}@ */

import {FormGroup} from "@angular/forms";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";

export interface ArticulusInteriorTableRow {
    /*
    @tt{{{
        @remove-blanks-and-linebreak-before-comment
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
        [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="Description" replaceByExpression="attribute.attributeName.pascalCase" ]
        @remove-blanks-and-linebreak-after-comment
    }}}@
     */
    description: string
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    year: number
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    formGroup: FormGroup<ArticulusInteriorFormPartGroup>
}
