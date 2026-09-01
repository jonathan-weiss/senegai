/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityTypeaheadComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-typeahead.component.html`
 * - path: `entitas-relata/entitas-relata-typeahead/entitas-relata-typeahead.component.html`
 */
object EntityTypeaheadComponentHtmlRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |<mat-form-field appearance="fill" class="typeahead-field">
          |    <mat-label>{{ label }}</mat-label>
          |    <input matInput
          |           #queryInput
          |           [placeholder]="placeholder"
          |           [disabled]="disabled"
          |           [value]="selectionLabel"
          |           [matAutocomplete]="${model.entityRootItem.itemName.camelCase}Autocomplete"
          |           (input)="onQueryChanged(queryInput.value)"
          |           (blur)="onSearchFieldBlurred(queryInput)">
          |    <mat-icon matSuffix>search</mat-icon>
          |    <mat-autocomplete #${model.entityRootItem.itemName.camelCase}Autocomplete="matAutocomplete"
          |                      [displayWith]="clearSearchField"
          |                      (optionSelected)="onOptionSelected(${"$"}event.option.value, queryInput)">
          |        @for (${model.entityRootItem.itemName.camelCase} of suggestionList(); track ${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}) {
          |            <mat-option [value]="${model.entityRootItem.itemName.camelCase}">{{ displayLabel(${model.entityRootItem.itemName.camelCase}) }}</mat-option>
          |        }
          |    </mat-autocomplete>
          |</mat-form-field>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-typeahead/${model.entityName.kebabCase}-typeahead.component.html"
    }
}