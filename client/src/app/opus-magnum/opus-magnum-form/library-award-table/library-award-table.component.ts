/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityItemTableComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="library-award" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="libraryAward" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="LibraryAward" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {AbstractControl, FormArray, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatDialogModule} from "@angular/material/dialog";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    LibraryAwardTableRow
} from "@app/opus-magnum/opus-magnum-form/library-award-table/library-award-table-row.model";
import {
    LibraryAwardFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-field-name";
import {
    LibraryAwardFormPartService
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part.service";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

@Component({
    selector: 'app-library-award-table',
    templateUrl: './library-award-table.component.html',
    styleUrls: ['./library-award-table.component.scss'],
    imports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatToolbarModule,
        MatTableModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatExpansionModule,
        MatSidenavModule,
        MatListModule,
        MatDialogModule,
    ]
})
export class LibraryAwardTableComponent implements OnInit {
    @Input({ required: true }) libraryAwardFormArray!: FormArray<FormGroup<LibraryAwardFormPartGroup>>;
    @Output() editLibraryAwardFormGroup = new EventEmitter<FormGroup<LibraryAwardFormPartGroup>>();
    @Output() deleteLibraryAwardFormGroup = new EventEmitter<FormGroup<LibraryAwardFormPartGroup>>();

    displayedColumns: string[] = [
        /*
        @tt{{{
            @slbc
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
            [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
            @slac
        }}}@
         */
        'description',
        /* @tt{{{ @slbc  @end-foreach @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        'year',
        'actions',
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    ];
    dataSource: MatTableDataSource<LibraryAwardTableRow> = new MatTableDataSource<LibraryAwardTableRow>();

    selectedFormGroup: FormGroup | undefined = undefined;

    constructor(private readonly libraryAwardFormService: LibraryAwardFormPartService) {
    }

    ngOnInit(): void {
        this.updateFormData()
        this.libraryAwardFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private toTableRow(formGroup: FormGroup<LibraryAwardFormPartGroup>): LibraryAwardTableRow {
        return {
            /*
            @tt{{{
                @slbc
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
                [ searchValue="Description" replaceByExpression="attribute.attributeName.pascalCase" ]
                @slac
            }}}@
             */
            description: FormUtil.requiredFormControl(formGroup, LibraryAwardFormPartFieldName.description).value as string,
            /* @tt{{{ @slbc  @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */

            year: FormUtil.requiredFormControl(formGroup, LibraryAwardFormPartFieldName.year).value as number,
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
            formGroup: formGroup,
        }
    }

    private updateFormData(): void {
        this.dataSource.data = this.libraryAwardFormArray.controls.map((control) => this.toTableRow(control))
    }

    onAdd(): void {
        const newEntry = this.libraryAwardFormService.createInitialLibraryAwardForm()
        const indexOfSelected = this.selectedFormGroup ? this.libraryAwardFormArray.controls.indexOf(this.selectedFormGroup) : -1
        if(indexOfSelected !== -1) {
            this.libraryAwardFormArray.insert(indexOfSelected + 1, newEntry)
        } else {
            this.libraryAwardFormArray.push(newEntry)
        }
        this.editLibraryAwardFormGroup.emit(newEntry);
    }

    onSelect(opusMagnumLibraryAwardFormGroup: FormGroup): void {
        this.selectedFormGroup = opusMagnumLibraryAwardFormGroup
    }

    isSelected(opusMagnumLibraryAwardFormGroup: FormGroup): boolean {
        return this.selectedFormGroup == opusMagnumLibraryAwardFormGroup
    }

    onEdit(opusMagnumLibraryAwardFormGroup: FormGroup): void {
        this.editLibraryAwardFormGroup.emit(opusMagnumLibraryAwardFormGroup);
    }

    onDelete(opusMagnumLibraryAwardFormGroup: FormGroup): void {
        this.deleteLibraryAwardFormGroup.emit(opusMagnumLibraryAwardFormGroup);
    }
}
