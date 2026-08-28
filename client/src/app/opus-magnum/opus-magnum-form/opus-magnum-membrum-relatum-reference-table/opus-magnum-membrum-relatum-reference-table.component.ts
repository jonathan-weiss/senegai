import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormControl, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {
    EntitasRelataTypeaheadComponent
} from "@app/entitas-relata/entitas-relata-typeahead/entitas-relata-typeahead.component";
import {MEMBRUM_RELATUM_UNRESOLVED_DISPLAY_VALUE} from "@app/entitas-relata/membrum-relatum-display";
import {EntitasRelataService} from "@app/entitas-relata/entitas-relata.service";
import {
    OpusMagnumMembrumRelatumReferenceTableRow
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-membrum-relatum-reference-table/opus-magnum-membrum-relatum-reference-table-row.model";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {UUID} from "@app/shared/uuid";
import {MembrumRelatumWTO} from "@app/wto/membrum-relatum.wto";

/**
 * Edits a list of references to EntitasRelata, held in the form as a FormArray of UUIDs.
 *
 * New rows are only ever created by picking an existing EntitasRelata in the typeahead, so
 * there is no "add empty row" button and the generic SingleFormFieldTableComponent does not
 * fit here.
 *
 * The UUIDs already stored in the form say nothing to the user, so they are resolved to whole
 * MembrumRelatumWTOs through a separate backend call (`POST /api/entitas-relata/by-ids`) and
 * shown by their display attributes. Resolved objects are cached, so a UUID is fetched once.
 */
@Component({
    selector: 'app-opus-magnum-membrum-relatum-reference-table',
    templateUrl: './opus-magnum-membrum-relatum-reference-table.component.html',
    styleUrls: ['./opus-magnum-membrum-relatum-reference-table.component.scss'],
    imports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatIconModule,
        MatTableModule,
        EntitasRelataTypeaheadComponent,
    ]
})
export class OpusMagnumMembrumRelatumReferenceTableComponent implements OnInit {
    @Input({required: true}) membrumRelatumReferenceFormArray!: FormArray<FormControl<UUID>>;
    @Input() columnHeaderClavisPrimaria: string = 'Clavis Primaria';
    @Input() columnHeaderDescriptioExDistanti: string = 'Descriptio Ex Distanti';

    protected readonly displayedColumns: string[] = ['clavisPrimaria', 'descriptioExDistanti', 'actions'];
    protected readonly dataSource = new MatTableDataSource<OpusMagnumMembrumRelatumReferenceTableRow>();

    private readonly resolvedByClavisPrimaria = new Map<UUID, MembrumRelatumWTO>();

    constructor(private readonly entitasRelataService: EntitasRelataService) {}

    ngOnInit(): void {
        this.refresh();
        // The form array is often patched only after the first render, so react to every change.
        this.membrumRelatumReferenceFormArray.valueChanges.subscribe(() => this.refresh());
    }

    protected referencedClavisPrimariaList(): ReadonlyArray<UUID> {
        return this.membrumRelatumReferenceFormArray.controls.map(control => control.getRawValue());
    }

    protected onMembrumRelatumSelected(membrumRelatum: MembrumRelatumWTO): void {
        if (this.referencedClavisPrimariaList().includes(membrumRelatum.clavisPrimaria)) {
            return;
        }
        this.resolvedByClavisPrimaria.set(membrumRelatum.clavisPrimaria, membrumRelatum);
        this.membrumRelatumReferenceFormArray.push(
            new FormControl<UUID>(membrumRelatum.clavisPrimaria, {nonNullable: true})
        );
    }

    protected onDelete(tableRow: OpusMagnumMembrumRelatumReferenceTableRow): void {
        FormUtil.removeControl(this.membrumRelatumReferenceFormArray, tableRow.formControl);
    }

    private refresh(): void {
        this.updateFormData();
        this.resolveMissingMembrumRelatum();
    }

    /** The separate backend call that resolves the UUIDs which are not yet in the cache. */
    private resolveMissingMembrumRelatum(): void {
        const unresolvedClavisPrimariaList = this.referencedClavisPrimariaList()
            .filter(clavisPrimaria => !this.resolvedByClavisPrimaria.has(clavisPrimaria));
        if (unresolvedClavisPrimariaList.length === 0) {
            return;
        }
        this.entitasRelataService.getMembrumRelatumListByIds({clavisPrimariaList: [...unresolvedClavisPrimariaList]})
            .subscribe(byIdsResult => {
                byIdsResult.membrumRelatumList.forEach(
                    membrumRelatum => this.resolvedByClavisPrimaria.set(membrumRelatum.clavisPrimaria, membrumRelatum)
                );
                this.updateFormData();
            });
    }

    private updateFormData(): void {
        this.dataSource.data = this.membrumRelatumReferenceFormArray.controls.map((control) => this.toTableRow(control))
    }

    private toTableRow(formControl: FormControl<UUID>): OpusMagnumMembrumRelatumReferenceTableRow {
        const clavisPrimaria = formControl.getRawValue();
        const membrumRelatum = this.resolvedByClavisPrimaria.get(clavisPrimaria);
        return {
            clavisPrimaria: clavisPrimaria,
            descriptioExDistanti: membrumRelatum?.descriptioExDistanti ?? MEMBRUM_RELATUM_UNRESOLVED_DISPLAY_VALUE,
            formControl: formControl,
        }
    }
}
