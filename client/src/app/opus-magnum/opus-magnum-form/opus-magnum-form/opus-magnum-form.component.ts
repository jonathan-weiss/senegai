/* @tt{{{

    

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityFormComponentTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.entityRootItem.itemName.kebabCase" ]

    @modify-provided-filepath-by-replacements

    

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
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
import {OpusMagnumSilvaOptionumFormPartService} from "@app/opus-magnum/opus-magnum-form/opus-magnum-silva-optionum-form-part/opus-magnum-silva-optionum-form-part.service";
import {OpusMagnumSilvaOptionumFormPartComponent} from "@app/opus-magnum/opus-magnum-form/opus-magnum-silva-optionum-form-part/opus-magnum-silva-optionum-form-part.component";
import {
    OpusMagnumSilvaOptionumFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-silva-optionum-form-part/opus-magnum-silva-optionum-form-part-group";

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
        OpusMagnumSilvaOptionumFormPartComponent,
    ]
})
export class OpusMagnumFormComponent implements OnInit {
    @Input() silvaOptionum: SilvaOptionumWTO | null = null;
    @Output() save = new EventEmitter<SilvaOptionumWTO>();
    @Output() cancel = new EventEmitter<void>();

    silvaOptionumForm: FormGroup<OpusMagnumSilvaOptionumFormPartGroup>;

    constructor(private silvaOptionumFormPartService: OpusMagnumSilvaOptionumFormPartService) {
        this.silvaOptionumForm = silvaOptionumFormPartService.createInitialSilvaOptionumForm();
    }

    ngOnInit(): void {
        if (this.silvaOptionum) {
            this.silvaOptionumFormPartService.patchSilvaOptionumForm(this.silvaOptionumForm, this.silvaOptionum)
        }
    }

    onSubmit(): void {
        if (this.silvaOptionumForm.valid) {
            const updatedSilvaOptionum: SilvaOptionumWTO = this.silvaOptionumFormPartService.createSilvaOptionumWTOFromForm(this.silvaOptionumForm)
            this.save.emit(updatedSilvaOptionum);
        }
    }

    onCancel(): void {
        this.cancel.emit();
    }
}
