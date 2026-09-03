/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemTypeaheadComponentHtmlRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `membrum-relatum-typeahead.component.html`
 * - path: `reference/membrum-relatum-typeahead/membrum-relatum-typeahead.component.html`
 */
object ItemTypeaheadComponentHtmlRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |<mat-form-field appearance="fill" class="typeahead-field">
          |    @if (label !== '') {
          |        <mat-label>{{ label }}</mat-label>
          |    }
          |    <input matInput
          |           #queryInput
          |           [placeholder]="placeholderTranslationKey | transloco"
          |           [disabled]="disabled"
          |           [value]="selectionLabel"
          |           [matAutocomplete]="${model.itemName.camelCase}Autocomplete"
          |           (input)="onQueryChanged(queryInput.value)"
          |           (blur)="onSearchFieldBlurred(queryInput)">
          |    <mat-icon matSuffix>search</mat-icon>
          |    <mat-autocomplete #${model.itemName.camelCase}Autocomplete="matAutocomplete"
          |                      [displayWith]="clearSearchField"
          |                      (optionSelected)="onOptionSelected(${"$"}event.option.value, queryInput)">
          |        @for (${model.itemName.camelCase} of suggestionList(); track ${model.itemName.camelCase}.${model.primaryKeyAttribute.attributeName.camelCase}) {
          |            <mat-option [value]="${model.itemName.camelCase}">{{ displayLabel(${model.itemName.camelCase}) }}</mat-option>
          |        }
          |    </mat-autocomplete>
          |</mat-form-field>
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "reference/${model.itemName.kebabCase}-typeahead/${model.itemName.kebabCase}-typeahead.component.html"
    }
}