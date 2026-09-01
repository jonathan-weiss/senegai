/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceTableComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-membrum-relatum-reference-table.component.ts`
 * - path: `entitas-relata/entitas-relata-membrum-relatum-reference-table/entitas-relata-membrum-relatum-reference-table.component.ts`
 */
object EntityReferenceTableComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component, Input, OnInit} from '@angular/core';
          |import {FormArray, FormControl, ReactiveFormsModule} from "@angular/forms";
          |import {MatButtonModule} from "@angular/material/button";
          |import {MatIconModule} from "@angular/material/icon";
          |import {MatTableDataSource, MatTableModule} from "@angular/material/table";
          |import {
          |    ${model.entityName.pascalCase}TypeaheadComponent
          |} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-typeahead/${model.entityName.kebabCase}-typeahead.component";
          |import {${model.entityRootItem.itemName.camelCase}DisplayRow} from "@app/${model.entityName.kebabCase}/${model.entityRootItem.itemName.kebabCase}-display";
          |import {${model.entityName.pascalCase}Service} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}.service";
          |import {
          |    ${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceTableRow
          |} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table-row.model";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {UUID} from "@app/shared/uuid";
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |
          |/**
          | * Edits a list of references to ${model.entityName.pascalCase}, held in the form as a FormArray of UUIDs.
          | *
          | * New rows are only ever created by picking an existing ${model.entityName.pascalCase} in the typeahead, so
          | * there is no "add empty row" button and the generic SingleFormFieldTableComponent does not
          | * fit here.
          | *
          | * The UUIDs already stored in the form say nothing to the user, so they are resolved to whole
          | * ${model.entityRootItem.itemName.pascalCase}WTOs through a separate backend call (`POST /api/${model.entityName.kebabCase}/by-ids`) and
          | * shown by their display attributes. Resolved objects are cached, so a UUID is fetched once.
          | */
          |@Component({
          |    selector: 'app-${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table',
          |    templateUrl: './${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table.component.html',
          |    styleUrls: ['./${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table.component.scss'],
          |    imports: [
          |        ReactiveFormsModule,
          |        MatButtonModule,
          |        MatIconModule,
          |        MatTableModule,
          |        ${model.entityName.pascalCase}TypeaheadComponent,
          |    ]
          |})
          |export class ${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceTableComponent implements OnInit {
          |    @Input({required: true}) ${model.entityRootItem.itemName.camelCase}ReferenceFormArray!: FormArray<FormControl<UUID>>;
          |    @Input() columnHeader${model.idAttribute.attributeName.pascalCase}: string = '${model.idAttribute.attributeName.pascalCase}';
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """    @Input() columnHeader${displayAttribute.attributeName.pascalCase}: string = '${displayAttribute.attributeName.pascalCase}';
              |""" } }
          |    protected readonly displayedColumns: string[] = [
          |        '${model.idAttribute.attributeName.camelCase}',
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """        '${displayAttribute.attributeName.camelCase}',
              |""" } }        'actions',
          |    ];
          |    protected readonly dataSource = new MatTableDataSource<${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceTableRow>();
          |
          |    private readonly resolvedBy${model.idAttribute.attributeName.pascalCase} = new Map<UUID, ${model.entityRootItem.itemName.pascalCase}WTO>();
          |
          |    constructor(private readonly ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service) {}
          |
          |    ngOnInit(): void {
          |        this.refresh();
          |        // The form array is often patched only after the first render, so react to every change.
          |        this.${model.entityRootItem.itemName.camelCase}ReferenceFormArray.valueChanges.subscribe(() => this.refresh());
          |    }
          |
          |    protected referenced${model.idAttribute.attributeName.pascalCase}List(): ReadonlyArray<UUID> {
          |        return this.${model.entityRootItem.itemName.camelCase}ReferenceFormArray.controls.map(control => control.getRawValue());
          |    }
          |
          |    protected on${model.entityRootItem.itemName.pascalCase}Selected(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): void {
          |        if (this.referenced${model.idAttribute.attributeName.pascalCase}List().includes(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase})) {
          |            return;
          |        }
          |        this.resolvedBy${model.idAttribute.attributeName.pascalCase}.set(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}, ${model.entityRootItem.itemName.camelCase});
          |        this.${model.entityRootItem.itemName.camelCase}ReferenceFormArray.push(
          |            new FormControl<UUID>(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}, {nonNullable: true})
          |        );
          |    }
          |
          |    protected onDelete(tableRow: ${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceTableRow): void {
          |        FormUtil.removeControl(this.${model.entityRootItem.itemName.camelCase}ReferenceFormArray, tableRow.formControl);
          |    }
          |
          |    private refresh(): void {
          |        this.updateFormData();
          |        this.resolveMissing${model.entityRootItem.itemName.pascalCase}();
          |    }
          |
          |    /** The separate backend call that resolves the UUIDs which are not yet in the cache. */
          |    private resolveMissing${model.entityRootItem.itemName.pascalCase}(): void {
          |        const unresolved${model.idAttribute.attributeName.pascalCase}List = this.referenced${model.idAttribute.attributeName.pascalCase}List()
          |            .filter(${model.idAttribute.attributeName.camelCase} => !this.resolvedBy${model.idAttribute.attributeName.pascalCase}.has(${model.idAttribute.attributeName.camelCase}));
          |        if (unresolved${model.idAttribute.attributeName.pascalCase}List.length === 0) {
          |            return;
          |        }
          |        this.${model.entityName.camelCase}Service.get${model.entityRootItem.itemName.pascalCase}ListByIds({${model.idAttribute.attributeName.camelCase}List: [...unresolved${model.idAttribute.attributeName.pascalCase}List]})
          |            .subscribe(byIdsResult => {
          |                byIdsResult.${model.entityRootItem.itemName.camelCase}List.forEach(
          |                    ${model.entityRootItem.itemName.camelCase} => this.resolvedBy${model.idAttribute.attributeName.pascalCase}.set(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}, ${model.entityRootItem.itemName.camelCase})
          |                );
          |                this.updateFormData();
          |            });
          |    }
          |
          |    private updateFormData(): void {
          |        this.dataSource.data = this.${model.entityRootItem.itemName.camelCase}ReferenceFormArray.controls.map((control) => this.toTableRow(control))
          |    }
          |
          |    private toTableRow(formControl: FormControl<UUID>): ${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceTableRow {
          |        const ${model.idAttribute.attributeName.camelCase} = formControl.getRawValue();
          |        return {
          |            ...${model.entityRootItem.itemName.camelCase}DisplayRow(${model.idAttribute.attributeName.camelCase}, this.resolvedBy${model.idAttribute.attributeName.pascalCase}.get(${model.idAttribute.attributeName.camelCase})),
          |            formControl: formControl,
          |        }
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-table.component.ts"
    }
}