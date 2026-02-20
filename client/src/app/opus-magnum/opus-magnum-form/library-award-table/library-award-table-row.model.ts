/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityItemTableRowComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="library-award" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="libraryAward" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="LibraryAward" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {FormGroup} from "@angular/forms";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

export interface LibraryAwardTableRow {
    /*
    @tt{{{
        @slbc
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
        [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="Description" replaceByExpression="attribute.attributeName.pascalCase" ]
        @slac
    }}}@
     */
    description: string
    /* @tt{{{ @slbc  @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    year: number
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    formGroup: FormGroup<LibraryAwardFormPartGroup>
}
