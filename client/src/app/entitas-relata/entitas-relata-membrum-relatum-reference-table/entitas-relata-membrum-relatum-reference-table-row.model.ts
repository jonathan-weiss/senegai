/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityReferenceTableRowRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="EntitasRelata" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="entitasRelata" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="entitas-relata" replaceByExpression="model.entityName.kebabCase" ]
        [ searchValue="MembrumRelatum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="membrumRelatum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="membrum-relatum" replaceByExpression="model.entityRootItem.itemName.kebabCase" ]
        [ searchValue="ClavisPrimaria" replaceByExpression="model.idAttribute.attributeName.pascalCase" ]
        [ searchValue="clavisPrimaria" replaceByExpression="model.idAttribute.attributeName.camelCase" ]

    @modify-provided-filepath-by-replacements

}}}@ */
import {FormControl} from "@angular/forms";
import {MembrumRelatumDisplayRow} from "@app/entitas-relata/membrum-relatum-display";
import {UUID} from "@app/shared/uuid";

/**
 * One row of the MembrumRelatum reference table.
 *
 * A reference is stored as a bare UUID in the form, which is meaningless to the user. The row
 * therefore carries the display attributes of the resolved MembrumRelatum flattened out, plus
 * a back-reference to the FormControl that actually holds the UUID.
 */
export interface EntitasRelataMembrumRelatumReferenceTableRow extends MembrumRelatumDisplayRow {
    formControl: FormControl<UUID>
}
