import {Component} from '@angular/core';
import {EntitasRelataResultComponent} from '@app/entitas-relata/entitas-relata-result/entitas-relata-result.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {EntitasRelataConfirmDeleteDialogComponent} from '@app/entitas-relata/entitas-relata-confirm-delete-dialog/entitas-relata-confirm-delete-dialog.component';
import {EntitasRelataService} from '@app/entitas-relata/entitas-relata.service';
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {EntitasRelataFormComponent} from "@app/entitas-relata/entitas-relata-form/entitas-relata-form/entitas-relata-form.component";
import {MembrumRelatumWTO} from "@app/wto/membrum-relatum.wto";
import {TranslocoPipe} from "@jsverse/transloco";

@Component({
    selector: 'app-entitas-relata-board',
    templateUrl: './entitas-relata-board.component.html',
    styleUrls: ['./entitas-relata-board.component.scss'],
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
        EntitasRelataResultComponent,
        EntitasRelataFormComponent,
        TranslocoPipe,
    ]
})
export class EntitasRelataBoardComponent {
    selectedEntitasRelata: MembrumRelatumWTO | null = null;
    creating = false;
    refreshKey = 0;

    constructor(private dialog: MatDialog, private entitasRelataService: EntitasRelataService) {
    }

    onCreateEntitasRelata(): void {
        this.selectedEntitasRelata = null;
        this.creating = true;
    }

    onEntitasRelataSelect(entitasRelata: MembrumRelatumWTO): void {
        this.creating = false;
        this.selectedEntitasRelata = entitasRelata;
    }

    onDeleteEntitasRelata(entitasRelata: MembrumRelatumWTO): void {
        const dialogRef = this.dialog.open(EntitasRelataConfirmDeleteDialogComponent, {
            data: {
                entity: entitasRelata,
            }
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.entitasRelataService.deleteMembrumRelatum(entitasRelata.clavisPrimaria).subscribe(() => {
                    this.refreshKey++;
                });
            }
        });
    }

    onSave(entitasRelata: MembrumRelatumWTO): void {
        const save$ = this.creating
            ? this.entitasRelataService.createMembrumRelatum(entitasRelata)
            : this.entitasRelataService.updateMembrumRelatum(entitasRelata);
        save$.subscribe(() => {
            this.selectedEntitasRelata = null;
            this.creating = false;
            this.refreshKey++;
        });
    }

    onCancel(): void {
        this.selectedEntitasRelata = null;
        this.creating = false;
    }
}
