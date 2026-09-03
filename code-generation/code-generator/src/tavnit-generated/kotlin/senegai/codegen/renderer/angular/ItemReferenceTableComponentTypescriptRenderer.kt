/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemReferenceTableComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `membrum-relatum-reference-table.component.ts`
 * - path: `reference/membrum-relatum-reference-table/membrum-relatum-reference-table.component.ts`
 */
object ItemReferenceTableComponentTypescriptRenderer : UiItemRenderer {

    override fun renderTemplate(model: UiItemModel): String {
        return """
          |import {Component, Input, OnInit} from '@angular/core';
          |import {FormArray, FormControl, ReactiveFormsModule} from "@angular/forms";
          |import {MatButtonModule} from "@angular/material/button";
          |import {MatIconModule} from "@angular/material/icon";
          |import {MatTableDataSource, MatTableModule} from "@angular/material/table";
          |import {
          |    ${model.itemName.pascalCase}TypeaheadComponent
          |} from "@app/reference/${model.itemName.kebabCase}-typeahead/${model.itemName.kebabCase}-typeahead.component";
          |import {${model.itemName.camelCase}DisplayRow} from "@app/reference/${model.itemName.kebabCase}-display";
          |import {${model.itemName.pascalCase}Service} from "@app/service/${model.itemName.kebabCase}.service";
          |import {
          |    ${model.itemName.pascalCase}ReferenceTableRow
          |} from "@app/reference/${model.itemName.kebabCase}-reference-table/${model.itemName.kebabCase}-reference-table-row.model";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {UUID} from "@app/shared/uuid";
          |import {${model.itemName.pascalCase}WTO} from "@app/wto/${model.itemName.kebabCase}.wto";
          |
          |/**
          | * Edits a list of references to ${model.itemName.pascalCase}, held in the form as a FormArray of UUIDs.
          | *
          | * New rows are only ever created by picking an existing ${model.itemName.pascalCase} in the typeahead, so
          | * there is no "add empty row" button and the generic SingleFormFieldTableComponent does not
          | * fit here.
          | *
          | * The UUIDs already stored in the form say nothing to the user, so they are resolved to whole
          | * ${model.itemName.pascalCase}WTOs through a separate backend call (`POST /api/${model.itemName.kebabCase}/by-ids`) and
          | * shown by their display attributes. Resolved objects are cached, so a UUID is fetched once.
          | */
          |@Component({
          |    selector: 'app-${model.itemName.kebabCase}-reference-table',
          |    templateUrl: './${model.itemName.kebabCase}-reference-table.component.html',
          |    styleUrls: ['./${model.itemName.kebabCase}-reference-table.component.scss'],
          |    imports: [
          |        ReactiveFormsModule,
          |        MatButtonModule,
          |        MatIconModule,
          |        MatTableModule,
          |        ${model.itemName.pascalCase}TypeaheadComponent,
          |    ]
          |})
          |export class ${model.itemName.pascalCase}ReferenceTableComponent implements OnInit {
          |    @Input({required: true}) ${model.itemName.camelCase}ReferenceFormArray!: FormArray<FormControl<UUID>>;
          |    @Input() columnHeader${model.primaryKeyAttribute.attributeName.pascalCase}: string = '${model.primaryKeyAttribute.attributeName.pascalCase}';
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """    @Input() columnHeader${displayAttribute.attributeName.pascalCase}: string = '${displayAttribute.attributeName.pascalCase}';
              |""" } }
          |    protected readonly displayedColumns: string[] = [
          |        '${model.primaryKeyAttribute.attributeName.camelCase}',
          |${ model.displayAttributes.joinToString("") { displayAttribute ->  """        '${displayAttribute.attributeName.camelCase}',
              |""" } }        'actions',
          |    ];
          |    protected readonly dataSource = new MatTableDataSource<${model.itemName.pascalCase}ReferenceTableRow>();
          |
          |    private readonly resolvedBy${model.primaryKeyAttribute.attributeName.pascalCase} = new Map<UUID, ${model.itemName.pascalCase}WTO>();
          |
          |    constructor(private readonly ${model.itemName.camelCase}Service: ${model.itemName.pascalCase}Service) {}
          |
          |    ngOnInit(): void {
          |        this.refresh();
          |        // The form array is often patched only after the first render, so react to every change.
          |        this.${model.itemName.camelCase}ReferenceFormArray.valueChanges.subscribe(() => this.refresh());
          |    }
          |
          |    protected referenced${model.primaryKeyAttribute.attributeName.pascalCase}List(): ReadonlyArray<UUID> {
          |        return this.${model.itemName.camelCase}ReferenceFormArray.controls.map(control => control.getRawValue());
          |    }
          |
          |    protected on${model.itemName.pascalCase}Selected(${model.itemName.camelCase}: ${model.itemName.pascalCase}WTO): void {
          |        if (this.referenced${model.primaryKeyAttribute.attributeName.pascalCase}List().includes(${model.itemName.camelCase}.${model.primaryKeyAttribute.attributeName.camelCase})) {
          |            return;
          |        }
          |        this.resolvedBy${model.primaryKeyAttribute.attributeName.pascalCase}.set(${model.itemName.camelCase}.${model.primaryKeyAttribute.attributeName.camelCase}, ${model.itemName.camelCase});
          |        this.${model.itemName.camelCase}ReferenceFormArray.push(
          |            new FormControl<UUID>(${model.itemName.camelCase}.${model.primaryKeyAttribute.attributeName.camelCase}, {nonNullable: true})
          |        );
          |    }
          |
          |    protected onDelete(tableRow: ${model.itemName.pascalCase}ReferenceTableRow): void {
          |        FormUtil.removeControl(this.${model.itemName.camelCase}ReferenceFormArray, tableRow.formControl);
          |    }
          |
          |    private refresh(): void {
          |        this.updateFormData();
          |        this.resolveMissing${model.itemName.pascalCase}();
          |    }
          |
          |    /** The separate backend call that resolves the UUIDs which are not yet in the cache. */
          |    private resolveMissing${model.itemName.pascalCase}(): void {
          |        const unresolved${model.primaryKeyAttribute.attributeName.pascalCase}List = this.referenced${model.primaryKeyAttribute.attributeName.pascalCase}List()
          |            .filter(${model.primaryKeyAttribute.attributeName.camelCase} => !this.resolvedBy${model.primaryKeyAttribute.attributeName.pascalCase}.has(${model.primaryKeyAttribute.attributeName.camelCase}));
          |        if (unresolved${model.primaryKeyAttribute.attributeName.pascalCase}List.length === 0) {
          |            return;
          |        }
          |        this.${model.itemName.camelCase}Service.get${model.itemName.pascalCase}ListByIds({${model.primaryKeyAttribute.attributeName.camelCase}List: [...unresolved${model.primaryKeyAttribute.attributeName.pascalCase}List]})
          |            .subscribe(byIdsResult => {
          |                byIdsResult.${model.itemName.camelCase}List.forEach(
          |                    ${model.itemName.camelCase} => this.resolvedBy${model.primaryKeyAttribute.attributeName.pascalCase}.set(${model.itemName.camelCase}.${model.primaryKeyAttribute.attributeName.camelCase}, ${model.itemName.camelCase})
          |                );
          |                this.updateFormData();
          |            });
          |    }
          |
          |    private updateFormData(): void {
          |        this.dataSource.data = this.${model.itemName.camelCase}ReferenceFormArray.controls.map((control) => this.toTableRow(control))
          |    }
          |
          |    private toTableRow(formControl: FormControl<UUID>): ${model.itemName.pascalCase}ReferenceTableRow {
          |        const ${model.primaryKeyAttribute.attributeName.camelCase} = formControl.getRawValue();
          |        return {
          |            ...${model.itemName.camelCase}DisplayRow(${model.primaryKeyAttribute.attributeName.camelCase}, this.resolvedBy${model.primaryKeyAttribute.attributeName.pascalCase}.get(${model.primaryKeyAttribute.attributeName.camelCase})),
          |            formControl: formControl,
          |        }
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiItemModel): String {
      return "reference/${model.itemName.kebabCase}-reference-table/${model.itemName.kebabCase}-reference-table.component.ts"
    }
}