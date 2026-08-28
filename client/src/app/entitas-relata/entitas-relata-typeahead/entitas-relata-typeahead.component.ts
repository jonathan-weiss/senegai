import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {Observable, Subject, debounceTime, distinctUntilChanged, of, switchMap} from "rxjs";
import {EntitasRelataService} from "@app/entitas-relata/entitas-relata.service";
import {membrumRelatumDisplayLabel} from "@app/entitas-relata/membrum-relatum-display";
import {UUID} from "@app/shared/uuid";
import {MembrumRelatumSearchResultWTO} from "@app/wto/membrum-relatum-search-result.wto";
import {MembrumRelatumWTO} from "@app/wto/membrum-relatum.wto";

/** Idle time after the last keystroke before the search is sent to the backend. */
const SEARCH_DEBOUNCE_IN_MILLISECONDS = 250;

/**
 * Typeahead to pick a single MembrumRelatum (i.e. an EntitasRelata instance).
 *
 * The suggestions come from the already existing search of the EntitasRelataService
 * (`POST /api/entitas-relata/search`), debounced so that not every keystroke hits the backend.
 * Each suggestion is labelled with the display attributes of the MembrumRelatum, never with
 * the bare clavisPrimaria.
 *
 * The whole MembrumRelatumWTO is emitted, not just its clavisPrimaria, so the caller can show
 * the display attributes of the new entry without another backend call.
 *
 * The search field is deliberately not bound to a form control: MatAutocompleteTrigger writes
 * the raw value of the selected option into the bound control (displayWith only changes the
 * text shown in the input), which would push a whole MembrumRelatumWTO through the search
 * query. Instead the typed text is read straight off the input element.
 */
@Component({
    selector: 'app-entitas-relata-typeahead',
    templateUrl: './entitas-relata-typeahead.component.html',
    styleUrls: ['./entitas-relata-typeahead.component.scss'],
    imports: [
        MatAutocompleteModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
    ]
})
export class EntitasRelataTypeaheadComponent implements OnInit {
    @Input() label: string = 'Search EntitasRelata';
    @Input() placeholder: string = 'Enter search query for EntitasRelata';
    /** clavisPrimaria that are already referenced and must therefore not be suggested again. */
    @Input() excludedClavisPrimariaList: ReadonlyArray<UUID> = [];
    @Input() disabled: boolean = false;

    @Output() membrumRelatumSelected = new EventEmitter<MembrumRelatumWTO>();

    private readonly query = new Subject<string>();
    private membrumRelatumSuggestionList: ReadonlyArray<MembrumRelatumWTO> = [];

    constructor(private readonly entitasRelataService: EntitasRelataService) {}

    ngOnInit(): void {
        this.query
            .pipe(
                debounceTime(SEARCH_DEBOUNCE_IN_MILLISECONDS),
                distinctUntilChanged(),
                switchMap(query => this.searchMembrumRelatumList(query)),
            )
            .subscribe(searchResult => this.membrumRelatumSuggestionList = searchResult.membrumRelatumList);
    }

    protected onQueryChanged(query: string): void {
        this.query.next(query);
    }

    /** An empty query is not sent to the backend, it simply clears the suggestions. */
    private searchMembrumRelatumList(query: string): Observable<MembrumRelatumSearchResultWTO> {
        if (query.length === 0) {
            return of({membrumRelatumList: []});
        }
        return this.entitasRelataService.searchMembrumRelatumList({query: query});
    }

    protected suggestionList(): ReadonlyArray<MembrumRelatumWTO> {
        return this.membrumRelatumSuggestionList
            .filter(membrumRelatum => !this.excludedClavisPrimariaList.includes(membrumRelatum.clavisPrimaria));
    }

    protected displayLabel(membrumRelatum: MembrumRelatumWTO): string {
        return membrumRelatumDisplayLabel(membrumRelatum);
    }

    /**
     * Empties the search field after a selection instead of writing the selected option into
     * it: the field is a search field, not the representation of the picked value.
     */
    protected readonly clearSearchField = (): string => '';

    protected onOptionSelected(membrumRelatum: MembrumRelatumWTO): void {
        this.membrumRelatumSuggestionList = [];
        // Keeps distinctUntilChanged from swallowing the same query when it is typed again.
        this.query.next('');
        this.membrumRelatumSelected.emit(membrumRelatum);
    }
}
