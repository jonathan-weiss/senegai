/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceDisplayRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `membrum-relatum-display.ts`
 * - path: `entitas-relata/membrum-relatum-display.ts`
 */
object EntityReferenceDisplayRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {UUID} from "@app/shared/uuid";
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |
          |/**
          | * One reference to an ${model.entityName.pascalCase}, ready to be rendered: the display attributes of the
          | * resolved ${model.entityRootItem.itemName.pascalCase}, already filled with the fallback where the reference could not be
          | * resolved.
          | *
          | * Shared by every place that shows such references, no matter whether the form holds a single
          | * reference or a whole list of them.
          | */
          |export interface ${model.entityRootItem.itemName.pascalCase}DisplayRow {
          |    ${model.idAttribute.attributeName.camelCase}: UUID
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """    ${displayAttribute.attributeName.camelCase}: string
              |""" } }}
          |
          |/**
          | * The display attributes of a ${model.entityRootItem.itemName.pascalCase}: the attributes that identify an instance for a
          | * human reader. A reference to an ${model.entityName.pascalCase} is stored as a bare UUID, which tells the user
          | * nothing, so every place that shows such a reference resolves it to the whole
          | * ${model.entityRootItem.itemName.pascalCase}WTO and renders these attributes instead of the UUID alone.
          | */
          |export const ${model.entityRootItem.itemName.screamingSnakeCase}_DISPLAY_ATTRIBUTE_NAMES: ReadonlyArray<keyof ${model.entityRootItem.itemName.pascalCase}DisplayRow> = [
          |    '${model.idAttribute.attributeName.camelCase}',
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """    '${displayAttribute.attributeName.camelCase}',
              |""" } }];
          |
          |/** Shown for a display attribute whose reference could not be resolved. */
          |export const ${model.entityRootItem.itemName.screamingSnakeCase}_UNRESOLVED_DISPLAY_VALUE = '—';
          |
          |/** Flattens the display attributes of a (possibly unresolved) reference into one row. */
          |export function ${model.entityRootItem.itemName.camelCase}DisplayRow(
          |    ${model.idAttribute.attributeName.camelCase}: UUID,
          |    ${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO | undefined,
          |): ${model.entityRootItem.itemName.pascalCase}DisplayRow {
          |    return {
          |        ${model.idAttribute.attributeName.camelCase}: ${model.idAttribute.attributeName.camelCase},
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """        ${displayAttribute.attributeName.camelCase}: ${model.entityRootItem.itemName.camelCase}?.${displayAttribute.attributeName.camelCase} ?? ${model.entityRootItem.itemName.screamingSnakeCase}_UNRESOLVED_DISPLAY_VALUE,
              |""" } }    }
          |}
          |
          |/**
          | * Joins the display attributes of one reference into a single line, for the places that have
          | * room for one line only (the typeahead suggestions and the search field that shows the picked
          | * entry).
          | */
          |export function ${model.entityRootItem.itemName.camelCase}DisplayRowLabel(displayRow: ${model.entityRootItem.itemName.pascalCase}DisplayRow): string {
          |    return ${model.entityRootItem.itemName.screamingSnakeCase}_DISPLAY_ATTRIBUTE_NAMES
          |        .map(attributeName => displayRow[attributeName])
          |        .join(' — ');
          |}
          |
          |/** The single line label of a whole ${model.entityRootItem.itemName.pascalCase}, whose references are all resolved. */
          |export function ${model.entityRootItem.itemName.camelCase}DisplayLabel(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): string {
          |    return ${model.entityRootItem.itemName.camelCase}DisplayRowLabel(
          |        ${model.entityRootItem.itemName.camelCase}DisplayRow(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}, ${model.entityRootItem.itemName.camelCase})
          |    );
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityRootItem.itemName.kebabCase}-display.ts"
    }
}