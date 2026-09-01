/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityTypeaheadComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-typeahead.component.ts`
 * - path: `entitas-relata/entitas-relata-typeahead/entitas-relata-typeahead.component.ts`
 */
object EntityTypeaheadComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
          |import {MatAutocompleteModule} from "@angular/material/autocomplete";
          |import {MatFormFieldModule} from "@angular/material/form-field";
          |import {MatIconModule} from "@angular/material/icon";
          |import {MatInputModule} from "@angular/material/input";
          |import {Observable, Subject, debounceTime, distinctUntilChanged, of, switchMap} from "rxjs";
          |import {${model.entityName.pascalCase}Service} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}.service";
          |import {${model.entityRootItem.itemName.camelCase}DisplayLabel} from "@app/${model.entityName.kebabCase}/${model.entityRootItem.itemName.kebabCase}-display";
          |import {UUID} from "@app/shared/uuid";
          |import {${model.entityRootItem.itemName.pascalCase}SearchResultWTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}-search-result.wto";
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |
          |/** Idle time after the last keystroke before the search is sent to the backend. */
          |const SEARCH_DEBOUNCE_IN_MILLISECONDS = 250;
          |
          |/**
          | * Typeahead to pick a single ${model.entityRootItem.itemName.pascalCase} (i.e. an ${model.entityName.pascalCase} instance).
          | *
          | * The suggestions come from the already existing search of the ${model.entityName.pascalCase}Service
          | * (`POST /api/${model.entityName.kebabCase}/search`), debounced so that not every keystroke hits the backend.
          | * Each suggestion is labelled with the display attributes of the ${model.entityRootItem.itemName.pascalCase}, never with
          | * the bare ${model.idAttribute.attributeName.camelCase}.
          | *
          | * The whole ${model.entityRootItem.itemName.pascalCase}WTO is emitted, not just its ${model.idAttribute.attributeName.camelCase}, so the caller can show
          | * the display attributes of the new entry without another backend call.
          | *
          | * What the field shows when nothing is being typed is up to the caller, through
          | * [selectionLabel]: where the typeahead stands for a single reference the field shows the
          | * picked entry, so the selection is never deleted, only overwritten. Where it only appends to a
          | * list of references the label stays empty and the field is a pure search field that clears
          | * itself after every pick.
          | *
          | * The search field is deliberately not bound to a form control: MatAutocompleteTrigger writes
          | * the raw value of the selected option into the bound control (displayWith only changes the
          | * text shown in the input), which would push a whole ${model.entityRootItem.itemName.pascalCase}WTO through the search
          | * query. Instead the typed text is read straight off the input element.
          | */
          |@Component({
          |    selector: 'app-${model.entityName.kebabCase}-typeahead',
          |    templateUrl: './${model.entityName.kebabCase}-typeahead.component.html',
          |    styleUrls: ['./${model.entityName.kebabCase}-typeahead.component.scss'],
          |    imports: [
          |        MatAutocompleteModule,
          |        MatFormFieldModule,
          |        MatIconModule,
          |        MatInputModule,
          |    ]
          |})
          |export class ${model.entityName.pascalCase}TypeaheadComponent implements OnInit {
          |    @Input() label: string = 'Search ${model.entityName.pascalCase}';
          |    @Input() placeholder: string = 'Enter search query for ${model.entityName.pascalCase}';
          |    /**
          |     * The display attributes of the entry the typeahead itself stands for, shown in the search
          |     * field whenever the user is not typing. Empty where the typeahead does not represent a
          |     * selection of its own.
          |     */
          |    @Input() selectionLabel: string = '';
          |    /** ${model.idAttribute.attributeName.camelCase} that are already referenced and must therefore not be suggested again. */
          |    @Input() excluded${model.idAttribute.attributeName.pascalCase}List: ReadonlyArray<UUID> = [];
          |    @Input() disabled: boolean = false;
          |
          |    @Output() ${model.entityRootItem.itemName.camelCase}Selected = new EventEmitter<${model.entityRootItem.itemName.pascalCase}WTO>();
          |
          |    private readonly query = new Subject<string>();
          |    private ${model.entityRootItem.itemName.camelCase}SuggestionList: ReadonlyArray<${model.entityRootItem.itemName.pascalCase}WTO> = [];
          |
          |    constructor(private readonly ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service) {}
          |
          |    ngOnInit(): void {
          |        this.query
          |            .pipe(
          |                debounceTime(SEARCH_DEBOUNCE_IN_MILLISECONDS),
          |                distinctUntilChanged(),
          |                switchMap(query => this.search${model.entityRootItem.itemName.pascalCase}List(query)),
          |            )
          |            .subscribe(searchResult => this.${model.entityRootItem.itemName.camelCase}SuggestionList = searchResult.${model.entityRootItem.itemName.camelCase}List);
          |    }
          |
          |    protected onQueryChanged(query: string): void {
          |        this.query.next(query);
          |    }
          |
          |    /** An empty query is not sent to the backend, it simply clears the suggestions. */
          |    private search${model.entityRootItem.itemName.pascalCase}List(query: string): Observable<${model.entityRootItem.itemName.pascalCase}SearchResultWTO> {
          |        if (query.length === 0) {
          |            return of({${model.entityRootItem.itemName.camelCase}List: []});
          |        }
          |        return this.${model.entityName.camelCase}Service.search${model.entityRootItem.itemName.pascalCase}List({query: query});
          |    }
          |
          |    protected suggestionList(): ReadonlyArray<${model.entityRootItem.itemName.pascalCase}WTO> {
          |        return this.${model.entityRootItem.itemName.camelCase}SuggestionList
          |            .filter(${model.entityRootItem.itemName.camelCase} => !this.excluded${model.idAttribute.attributeName.pascalCase}List.includes(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}));
          |    }
          |
          |    protected displayLabel(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): string {
          |        return ${model.entityRootItem.itemName.camelCase}DisplayLabel(${model.entityRootItem.itemName.camelCase});
          |    }
          |
          |    /**
          |     * Keeps MatAutocompleteTrigger from writing the raw option object into the search field. What
          |     * the field shows after a pick is decided in [onOptionSelected] instead.
          |     */
          |    protected readonly clearSearchField = (): string => '';
          |
          |    protected onOptionSelected(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO, searchField: HTMLInputElement): void {
          |        this.${model.entityRootItem.itemName.camelCase}SuggestionList = [];
          |        // Keeps distinctUntilChanged from swallowing the same query when it is typed again.
          |        this.query.next('');
          |        this.${model.entityRootItem.itemName.camelCase}Selected.emit(${model.entityRootItem.itemName.camelCase});
          |        // The caller reacts to the pick by updating selectionLabel, which the [value] binding
          |        // writes into the field. Picking the entry that is already selected leaves the label
          |        // unchanged, so the field is restored here as well.
          |        searchField.value = this.selectionLabel;
          |    }
          |
          |    /**
          |     * Leaving the field without picking anything must not leave a half typed query behind where
          |     * the field shows a selection: it goes back to the picked entry. A pure search field keeps
          |     * what was typed.
          |     */
          |    protected onSearchFieldBlurred(searchField: HTMLInputElement): void {
          |        if (this.selectionLabel !== '') {
          |            searchField.value = this.selectionLabel;
          |        }
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-typeahead/${model.entityName.kebabCase}-typeahead.component.ts"
    }
}