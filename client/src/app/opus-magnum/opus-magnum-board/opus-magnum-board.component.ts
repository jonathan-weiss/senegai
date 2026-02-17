/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityBoardComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityNameDashCase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */
import {Component} from '@angular/core';
import {OpusMagnumSearchComponent, OpusMagnumSearchCriteria} from '@app/opus-magnum/opus-magnum-search/opus-magnum-search.component';
import {OpusMagnumResultComponent} from '@app/opus-magnum/opus-magnum-result/opus-magnum-result.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {OpusMagnumConfirmDeleteDialogComponent} from '@app/opus-magnum/opus-magnum-confirm-delete-dialog/opus-magnum-confirm-delete-dialog.component';
import {OpusMagnumService} from '@app/opus-magnum/opus-magnum.service';
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
import {OpusMagnumFormComponent} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component";
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
import {TranslocoPipe} from "@jsverse/transloco";

@Component({
    selector: 'app-opus-magnum-board',
    templateUrl: './opus-magnum-board.component.html',
    styleUrls: ['./opus-magnum-board.component.scss'],
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
        OpusMagnumSearchComponent,
        OpusMagnumResultComponent,
        OpusMagnumFormComponent,
        TranslocoPipe,
    ]
})
export class OpusMagnumBoardComponent {
    currentSearchCriteria: OpusMagnumSearchCriteria = {};
    selectedOpusMagnum: OpusMagnumWTO | null = null;
    refreshKey = 0;

    constructor(private dialog: MatDialog, private opusMagnumService: OpusMagnumService) {
    }

    onSearch(criteria: OpusMagnumSearchCriteria): void {
        this.currentSearchCriteria = criteria;
    }

    onOpusMagnumSelect(opusMagnum: OpusMagnumWTO): void {
        this.selectedOpusMagnum = opusMagnum;
    }

    onDeleteOpusMagnum(opusMagnum: OpusMagnumWTO): void {
        const dialogRef = this.dialog.open(OpusMagnumConfirmDeleteDialogComponent, {
            data: {firstname: opusMagnum.firstname, lastname: opusMagnum.lastname}
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.opusMagnumService.deleteOpusMagnum(opusMagnum.id).subscribe(() => {
                    this.refreshKey++;
                });
            }
        });
    }

    onSave(updatedOpusMagnum: OpusMagnumWTO): void {
        this.opusMagnumService.updateOpusMagnum(updatedOpusMagnum).subscribe(() => {
            this.selectedOpusMagnum = null;
            this.refreshKey++;
        });
    }

    onCancel(): void {
        this.selectedOpusMagnum = null;
    }
} 
