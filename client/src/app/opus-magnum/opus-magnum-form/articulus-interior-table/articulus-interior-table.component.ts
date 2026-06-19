/* @tt{{{

    

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityItemTableComponentTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="articulus-interior" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="articulusInterior" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="ArticulusInterior" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    

}}}@ */
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {FormArray, FormGroup, ReactiveFormsModule} from "@angular/forms";
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
import {
    ArticulusInteriorTableRow
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-table/articulus-interior-table-row.model";
import {
    ArticulusInteriorFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-field-name";
import {
    ArticulusInteriorFormPartService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part.service";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";

@Component({
    selector: 'app-articulus-interior-table',
    templateUrl: './articulus-interior-table.component.html',
    styleUrls: ['./articulus-interior-table.component.scss'],
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
export class ArticulusInteriorTableComponent implements OnInit {
    @Input({ required: true }) articulusInteriorFormArray!: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>;
    @Output() editArticulusInteriorFormGroup = new EventEmitter<FormGroup<ArticulusInteriorFormPartGroup>>();
    @Output() deleteArticulusInteriorFormGroup = new EventEmitter<FormGroup<ArticulusInteriorFormPartGroup>>();

    displayedColumns: string[] = [
        /*
        @tt{{{
            
            @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
            [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
            
        }}}@
         */
        'description',
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        'year',
        /* @tt{{{   @end-ignore-text  }}}@ */
        'actions',
    ];
    dataSource: MatTableDataSource<ArticulusInteriorTableRow> = new MatTableDataSource<ArticulusInteriorTableRow>();

    selectedFormGroup: FormGroup | undefined = undefined;

    constructor(private readonly articulusInteriorFormService: ArticulusInteriorFormPartService) {
    }

    ngOnInit(): void {
        this.updateFormData()
        this.articulusInteriorFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private toTableRow(formGroup: FormGroup<ArticulusInteriorFormPartGroup>): ArticulusInteriorTableRow {
        return {
            /*
            @tt{{{
                
                @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                [ searchValue="description" replaceByExpression="attribute.attributeName.camelCase" ]
                [ searchValue="Description" replaceByExpression="attribute.attributeName.pascalCase" ]
                
            }}}@
             */
            description: JSON.stringify(formGroup.controls[ArticulusInteriorFormPartFieldName.description].value),
            /* @tt{{{   @end-foreach  }}}@ */
            /* @tt{{{   @ignore-text  }}}@ */

            year: formGroup.controls[ArticulusInteriorFormPartFieldName.year].value,
            /* @tt{{{   @end-ignore-text  }}}@ */
            formGroup: formGroup,
        }
    }

    private updateFormData(): void {
        this.dataSource.data = this.articulusInteriorFormArray.controls.map((control) => this.toTableRow(control))
    }

    onAdd(): void {
        const newEntry = this.articulusInteriorFormService.createInitialArticulusInteriorForm()
        const indexOfSelected = this.selectedFormGroup ? this.articulusInteriorFormArray.controls.indexOf(this.selectedFormGroup) : -1
        if(indexOfSelected !== -1) {
            this.articulusInteriorFormArray.insert(indexOfSelected + 1, newEntry)
        } else {
            this.articulusInteriorFormArray.push(newEntry)
        }
        this.editArticulusInteriorFormGroup.emit(newEntry);
    }

    onSelect(opusMagnumArticulusInteriorFormGroup: FormGroup): void {
        this.selectedFormGroup = opusMagnumArticulusInteriorFormGroup
    }

    isSelected(opusMagnumArticulusInteriorFormGroup: FormGroup): boolean {
        return this.selectedFormGroup == opusMagnumArticulusInteriorFormGroup
    }

    onEdit(opusMagnumArticulusInteriorFormGroup: FormGroup): void {
        this.editArticulusInteriorFormGroup.emit(opusMagnumArticulusInteriorFormGroup);
    }

    onDelete(opusMagnumArticulusInteriorFormGroup: FormGroup): void {
        this.deleteArticulusInteriorFormGroup.emit(opusMagnumArticulusInteriorFormGroup);
    }
}
