/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityConfirmDeleteDialogComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityName.kebabCase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */
import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
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

@Component({
    selector: 'app-opus-magnum-confirm-delete-dialog',
    templateUrl: './opus-magnum-confirm-delete-dialog.component.html',
    styleUrls: ['./opus-magnum-confirm-delete-dialog.component.scss'],
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
export class OpusMagnumConfirmDeleteDialogComponent {
    constructor(
        public dialogRef: MatDialogRef<OpusMagnumConfirmDeleteDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: {
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.summaryAttributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="title" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="string" replaceByExpression="attribute.typescriptAttributeTypeWithoutNullability" ]
                @slac
            }}}@  */
            title: string,
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
        }
    ) {
    }

    onCancel(): void {
        this.dialogRef.close(false);
    }

    onConfirm(): void {
        this.dialogRef.close(true);
    }
} 
