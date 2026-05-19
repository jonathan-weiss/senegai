import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {FormUtil} from "@app/shared/form-controls/form.util";
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {
    OpusMagnumFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-validation.service";
import {
    OpusMagnumFormPartFieldName,
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {
    OpusMagnumFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-group";
import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
import {SilvaOptionumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";
import {SilvaOptionumFormPartComponent} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part.component";

@Component({
    selector: 'app-opus-magnum-form-part',
    templateUrl: './opus-magnum-form-part.component.html',
    styleUrls: ['./opus-magnum-form-part.component.scss'],
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
        FieldWrapperComponent,
        TextInputComponent,
        MatTabGroup,
        MatTab,
        SectionSplitterComponent,
        TextBlockComponent,
        SilvaOptionumFormPartComponent,
    ]
})
export class OpusMagnumFormPartComponent implements OnInit {
    @Input({required: true}) opusMagnumForm!: FormGroup<OpusMagnumFormPartGroup>;

    protected idControl!: FormControl<string>
    protected titleControl!: FormControl<string>
    protected titleValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected silvaOptionumFormGroup!: FormGroup<SilvaOptionumFormPartGroup>
    protected silvaOptionumValidatorNames!: ReadonlyArray<ValidatorTranslation>

    constructor(private readonly opusMagnumFormValidationService: OpusMagnumFormPartValidationService,) {
    }

    ngOnInit() {
        this.idControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.id]
        this.titleControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.title]
        this.titleValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.title)
        this.silvaOptionumFormGroup = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.silvaOptionum]
        this.silvaOptionumValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.silvaOptionum)
    }
}
