/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="ItemReferenceTableRowRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="MembrumRelatum" replaceByExpression="model.itemName.pascalCase" ]
        [ searchValue="membrumRelatum" replaceByExpression="model.itemName.camelCase" ]
        [ searchValue="membrum-relatum" replaceByExpression="model.itemName.kebabCase" ]
        [ searchValue="ClavisPrimaria" replaceByExpression="model.primaryKeyAttribute.attributeName.pascalCase" ]
        [ searchValue="clavisPrimaria" replaceByExpression="model.primaryKeyAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
import {FormControl} from "@angular/forms";
import {MembrumRelatumDisplayRow} from "@app/reference/membrum-relatum-display";
import {UUID} from "@app/shared/uuid";

/**
 * One row of the MembrumRelatum reference table.
 *
 * A reference is stored as a bare UUID in the form, which is meaningless to the user. The row
 * therefore carries the display attributes of the resolved MembrumRelatum flattened out, plus
 * a back-reference to the FormControl that actually holds the UUID.
 */
export interface MembrumRelatumReferenceTableRow extends MembrumRelatumDisplayRow {
    formControl: FormControl<UUID>
}
