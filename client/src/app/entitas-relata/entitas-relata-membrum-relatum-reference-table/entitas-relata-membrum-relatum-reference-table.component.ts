/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityReferenceTableComponentTypescriptRenderer"
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
import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormControl, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {
    EntitasRelataTypeaheadComponent
} from "@app/entitas-relata/entitas-relata-typeahead/entitas-relata-typeahead.component";
import {membrumRelatumDisplayRow} from "@app/entitas-relata/membrum-relatum-display";
import {EntitasRelataService} from "@app/entitas-relata/entitas-relata.service";
import {
    EntitasRelataMembrumRelatumReferenceTableRow
} from "@app/entitas-relata/entitas-relata-membrum-relatum-reference-table/entitas-relata-membrum-relatum-reference-table-row.model";
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
    selector: 'app-entitas-relata-membrum-relatum-reference-table',
    templateUrl: './entitas-relata-membrum-relatum-reference-table.component.html',
    styleUrls: ['./entitas-relata-membrum-relatum-reference-table.component.scss'],
    imports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatIconModule,
        MatTableModule,
        EntitasRelataTypeaheadComponent,
    ]
})
export class EntitasRelataMembrumRelatumReferenceTableComponent implements OnInit {
    @Input({required: true}) membrumRelatumReferenceFormArray!: FormArray<FormControl<UUID>>;
    @Input() columnHeaderClavisPrimaria: string = 'ClavisPrimaria';
    /* @tt{{{
        @foreach [ iteratorExpression="model.displayAttributes" loopVariable="displayAttribute" ]
        @replace-value-by-expression
            [ searchValue="DescriptioExDistanti" replaceByExpression="displayAttribute.attributeName.pascalCase" ]
    }}}@ */
    @Input() columnHeaderDescriptioExDistanti: string = 'DescriptioExDistanti';
    /* @tt{{{   @end-foreach  }}}@ */

    protected readonly displayedColumns: string[] = [
        'clavisPrimaria',
        /* @tt{{{
            @foreach [ iteratorExpression="model.displayAttributes" loopVariable="displayAttribute" ]
            @replace-value-by-expression
                [ searchValue="descriptioExDistanti" replaceByExpression="displayAttribute.attributeName.camelCase" ]
        }}}@ */
        'descriptioExDistanti',
        /* @tt{{{   @end-foreach  }}}@ */
        'actions',
    ];
    protected readonly dataSource = new MatTableDataSource<EntitasRelataMembrumRelatumReferenceTableRow>();

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

    protected onDelete(tableRow: EntitasRelataMembrumRelatumReferenceTableRow): void {
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

    private toTableRow(formControl: FormControl<UUID>): EntitasRelataMembrumRelatumReferenceTableRow {
        const clavisPrimaria = formControl.getRawValue();
        return {
            ...membrumRelatumDisplayRow(clavisPrimaria, this.resolvedByClavisPrimaria.get(clavisPrimaria)),
            formControl: formControl,
        }
    }
}
