/* @tt{{{

    #expand-comment [ expandDirection="backward" strip="linebreak"]

    #move-comment [ direction="backward" ]
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
        [ searchValue="library-award" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="libraryAward" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="LibraryAward" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    #expand-comment [ expandDirection="forward" strip="linebreak"]

}}}@ */

import {FormGroup} from "@angular/forms";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

export interface LibraryAwardTableRow {
    /*
    @tt{{{
        #expand-comment [ expandDirection="backward" strip="linebreak"]
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
        [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="Description" replaceByExpression="attribute.attributeName.pascalCase" ]
        #expand-comment [ expandDirection="forward" strip="linebreak"]
    }}}@
     */
    description: string
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    year: number
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    formGroup: FormGroup<LibraryAwardFormPartGroup>
}
