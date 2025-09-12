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
    selector: 'app-author-library-award-jury-table',
    templateUrl: './author-library-award-jury-table.component.html',
    styleUrls: ['./author-library-award-jury-table.component.scss'],
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
export class AuthorLibraryAwardJuryTableComponent implements OnInit {
    @Input({ required: true }) authorLibraryAwardJuryFormArray!: FormArray;

    displayedColumns: string[] = ['jury', 'actions'];
    dataSource: MatTableDataSource<AbstractControl> = new MatTableDataSource<AbstractControl>();

    selectedControl: FormControl | undefined = undefined;

    ngOnInit(): void {
        this.dataSource.data = this.authorLibraryAwardJuryFormArray.controls
        this.authorLibraryAwardJuryFormArray.valueChanges.subscribe(() => this.updateFormData())
    }

    private updateFormData(): void {
        this.dataSource.data = this.authorLibraryAwardJuryFormArray.controls
    }

    onAdd(): void {
        const newEntry = new FormControl('')
        const indexOfSelected = this.selectedControl ? this.authorLibraryAwardJuryFormArray.controls.indexOf(this.selectedControl) : -1
        if(indexOfSelected !== -1) {
            this.authorLibraryAwardJuryFormArray.insert(indexOfSelected + 1, newEntry)
        } else {
            this.authorLibraryAwardJuryFormArray.push(newEntry)
        }
    }

    onSelect(authorLibraryAwardFormControl: FormControl): void {
        this.selectedControl = authorLibraryAwardFormControl
    }

    isSelected(authorLibraryAwardFormControl: FormControl): boolean {
        return this.selectedControl == authorLibraryAwardFormControl
    }

    onDelete(authorLibraryAwardFormControl: FormControl): void {
        const indexOfSelected = this.authorLibraryAwardJuryFormArray.controls.indexOf(authorLibraryAwardFormControl)
        if(indexOfSelected !== -1) {
            this.authorLibraryAwardJuryFormArray.removeAt(indexOfSelected)
        }
    }
}
