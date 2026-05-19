import {Component, Input, OnInit} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {AbstractControl, FormArray, FormControl, ReactiveFormsModule} from "@angular/forms";
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

@Component({
    selector: 'app-tabula-simplex',
    templateUrl: './tabula-simplex.component.html',
    styleUrls: ['./tabula-simplex.component.scss'],
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
export class TabulaSimplexComponent implements OnInit {
    @Input({ required: true }) articulusInteriorJuryFormArray!: FormArray;

    displayedColumns: string[] = ['jury', 'actions'];
    dataSource: MatTableDataSource<AbstractControl> = new MatTableDataSource<AbstractControl>();

    selectedControl: FormControl | undefined = undefined;

    ngOnInit(): void {
        this.dataSource.data = this.articulusInteriorJuryFormArray.controls
        this.articulusInteriorJuryFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private updateFormData(): void {
        this.dataSource.data = this.articulusInteriorJuryFormArray.controls
    }

    onAdd(): void {
        const newEntry = new FormControl('')
        const indexOfSelected = this.selectedControl ? this.articulusInteriorJuryFormArray.controls.indexOf(this.selectedControl) : -1
        if(indexOfSelected !== -1) {
            this.articulusInteriorJuryFormArray.insert(indexOfSelected + 1, newEntry)
        } else {
            this.articulusInteriorJuryFormArray.push(newEntry)
        }
    }

    onSelect(opusMagnumArticulusInteriorFormControl: FormControl): void {
        this.selectedControl = opusMagnumArticulusInteriorFormControl
    }

    isSelected(opusMagnumArticulusInteriorFormControl: FormControl): boolean {
        return this.selectedControl == opusMagnumArticulusInteriorFormControl
    }

    onDelete(opusMagnumArticulusInteriorFormControl: FormControl): void {
        const indexOfSelected = this.articulusInteriorJuryFormArray.controls.indexOf(opusMagnumArticulusInteriorFormControl)
        if(indexOfSelected !== -1) {
            this.articulusInteriorJuryFormArray.removeAt(indexOfSelected)
        }
    }
}
