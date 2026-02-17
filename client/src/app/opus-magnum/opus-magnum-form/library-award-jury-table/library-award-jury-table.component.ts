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
    selector: 'app-library-award-jury-table',
    templateUrl: './library-award-jury-table.component.html',
    styleUrls: ['./library-award-jury-table.component.scss'],
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
export class LibraryAwardJuryTableComponent implements OnInit {
    @Input({ required: true }) libraryAwardJuryFormArray!: FormArray;

    displayedColumns: string[] = ['jury', 'actions'];
    dataSource: MatTableDataSource<AbstractControl> = new MatTableDataSource<AbstractControl>();

    selectedControl: FormControl | undefined = undefined;

    ngOnInit(): void {
        this.dataSource.data = this.libraryAwardJuryFormArray.controls
        this.libraryAwardJuryFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private updateFormData(): void {
        this.dataSource.data = this.libraryAwardJuryFormArray.controls
    }

    onAdd(): void {
        const newEntry = new FormControl('')
        const indexOfSelected = this.selectedControl ? this.libraryAwardJuryFormArray.controls.indexOf(this.selectedControl) : -1
        if(indexOfSelected !== -1) {
            this.libraryAwardJuryFormArray.insert(indexOfSelected + 1, newEntry)
        } else {
            this.libraryAwardJuryFormArray.push(newEntry)
        }
    }

    onSelect(authorLibraryAwardFormControl: FormControl): void {
        this.selectedControl = authorLibraryAwardFormControl
    }

    isSelected(authorLibraryAwardFormControl: FormControl): boolean {
        return this.selectedControl == authorLibraryAwardFormControl
    }

    onDelete(authorLibraryAwardFormControl: FormControl): void {
        const indexOfSelected = this.libraryAwardJuryFormArray.controls.indexOf(authorLibraryAwardFormControl)
        if(indexOfSelected !== -1) {
            this.libraryAwardJuryFormArray.removeAt(indexOfSelected)
        }
    }
}
