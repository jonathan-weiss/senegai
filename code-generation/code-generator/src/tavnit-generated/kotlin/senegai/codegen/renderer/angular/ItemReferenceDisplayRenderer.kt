/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemReferenceDisplayRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `membrum-relatum-display.ts`
 * - path: `reference/membrum-relatum-display.ts`
 */
object ItemReferenceDisplayRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |import {UUID} from "@app/shared/uuid";
          |import {${model.itemName.pascalCase}WTO} from "@app/wto/${model.itemName.kebabCase}.wto";
          |
          |/**
          | * One reference to a ${model.itemName.pascalCase}, ready to be rendered: the display attributes of the
          | * resolved ${model.itemName.pascalCase}, already filled with the fallback where the reference could not be
          | * resolved.
          | *
          | * Shared by every place that shows such references, no matter whether the form holds a single
          | * reference or a whole list of them.
          | */
          |export interface ${model.itemName.pascalCase}DisplayRow {
          |    ${model.primaryKeyAttribute.attributeName.camelCase}: UUID
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """    ${displayAttribute.attributeName.camelCase}: string
              |""" } }}
          |
          |/**
          | * The display attributes of a ${model.itemName.pascalCase}: the attributes that identify an instance for a
          | * human reader. A reference to a ${model.itemName.pascalCase} is stored as a bare UUID, which tells the user
          | * nothing, so every place that shows such a reference resolves it to the whole
          | * ${model.itemName.pascalCase}WTO and renders these attributes instead of the UUID alone.
          | */
          |export const ${model.itemName.screamingSnakeCase}_DISPLAY_ATTRIBUTE_NAMES: ReadonlyArray<keyof ${model.itemName.pascalCase}DisplayRow> = [
          |    '${model.primaryKeyAttribute.attributeName.camelCase}',
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """    '${displayAttribute.attributeName.camelCase}',
              |""" } }];
          |
          |/** Shown for a display attribute whose reference could not be resolved. */
          |export const ${model.itemName.screamingSnakeCase}_UNRESOLVED_DISPLAY_VALUE = '—';
          |
          |/** Flattens the display attributes of a (possibly unresolved) reference into one row. */
          |export function ${model.itemName.camelCase}DisplayRow(
          |    ${model.primaryKeyAttribute.attributeName.camelCase}: UUID,
          |    ${model.itemName.camelCase}: ${model.itemName.pascalCase}WTO | undefined,
          |): ${model.itemName.pascalCase}DisplayRow {
          |    return {
          |        ${model.primaryKeyAttribute.attributeName.camelCase}: ${model.primaryKeyAttribute.attributeName.camelCase},
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """        ${displayAttribute.attributeName.camelCase}: ${model.itemName.camelCase}?.${displayAttribute.attributeName.camelCase} ?? ${model.itemName.screamingSnakeCase}_UNRESOLVED_DISPLAY_VALUE,
              |""" } }    }
          |}
          |
          |/**
          | * Joins the display attributes of one reference into a single line, for the places that have
          | * room for one line only (the typeahead suggestions and the search field that shows the picked
          | * entry).
          | */
          |export function ${model.itemName.camelCase}DisplayRowLabel(displayRow: ${model.itemName.pascalCase}DisplayRow): string {
          |    return ${model.itemName.screamingSnakeCase}_DISPLAY_ATTRIBUTE_NAMES
          |        .map(attributeName => displayRow[attributeName])
          |        .join(' — ');
          |}
          |
          |/** The single line label of a whole ${model.itemName.pascalCase}, whose references are all resolved. */
          |export function ${model.itemName.camelCase}DisplayLabel(${model.itemName.camelCase}: ${model.itemName.pascalCase}WTO): string {
          |    return ${model.itemName.camelCase}DisplayRowLabel(
          |        ${model.itemName.camelCase}DisplayRow(${model.itemName.camelCase}.${model.primaryKeyAttribute.attributeName.camelCase}, ${model.itemName.camelCase})
          |    );
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "reference/${model.itemName.kebabCase}-display.ts"
    }
}