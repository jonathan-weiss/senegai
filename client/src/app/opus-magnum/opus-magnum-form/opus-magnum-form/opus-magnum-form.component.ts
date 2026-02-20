/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityFormComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {MatDialogModule} from "@angular/material/dialog";
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
import {OpusMagnumFormPartService} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part.service";
import {OpusMagnumFormPartComponent} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part.component";

@Component({
    selector: 'app-opus-magnum-form',
    templateUrl: './opus-magnum-form.component.html',
    styleUrls: ['./opus-magnum-form.component.scss'],
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
        OpusMagnumFormPartComponent,
    ]
})
export class OpusMagnumFormComponent implements OnInit {
    @Input() opusMagnum: OpusMagnumWTO | null = null;
    @Output() save = new EventEmitter<OpusMagnumWTO>();
    @Output() cancel = new EventEmitter<void>();

    opusMagnumForm: FormGroup;

    constructor(private opusMagnumFormPartService: OpusMagnumFormPartService) {
        this.opusMagnumForm = opusMagnumFormPartService.createInitialOpusMagnumForm();
    }

    ngOnInit(): void {
        if (this.opusMagnum) {
            this.opusMagnumFormPartService.patchOpusMagnumForm(this.opusMagnumForm, this.opusMagnum)
        }
    }

    onSubmit(): void {
        if (this.opusMagnumForm.valid) {
            const updatedOpusMagnum: OpusMagnumWTO = this.opusMagnumFormPartService.createOpusMagnumFromFormData(this.opusMagnumForm)
            this.save.emit(updatedOpusMagnum);
        }
    }

    onCancel(): void {
        this.cancel.emit();
    }
}
