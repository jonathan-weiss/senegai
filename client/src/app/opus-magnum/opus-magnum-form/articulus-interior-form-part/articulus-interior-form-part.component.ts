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
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {NumberInputComponent} from "@app/shared/form-controls/number-input/number-input.component";
import {
    ArticulusInteriorFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-field-name";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
import {
    ArticulusInteriorFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-validation.service";

@Component({
    selector: 'app-articulus-interior-form-part',
    templateUrl: './articulus-interior-form-part.component.html',
    styleUrls: ['./articulus-interior-form-part.component.scss'],
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
        NumberInputComponent,
    ]
})
export class ArticulusInteriorFormPartComponent implements OnInit {
    @Input({required: true}) articulusInteriorForm!: FormGroup<ArticulusInteriorFormPartGroup>;

    constructor(private readonly articulusInteriorFormPartValidationService: ArticulusInteriorFormPartValidationService,) {
    }

    protected articulusInteriorDescriptionControl!: FormControl<string>
    protected articulusInteriorDescriptionValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected articulusInteriorYearControl!: FormControl<number>
    protected articulusInteriorYearValidatorNames!: ReadonlyArray<ValidatorTranslation>

    ngOnInit() {
        this.articulusInteriorDescriptionControl = this.articulusInteriorForm.controls[ArticulusInteriorFormPartFieldName.description]
        this.articulusInteriorDescriptionValidatorNames = this.articulusInteriorFormPartValidationService.validatorNames(ArticulusInteriorFormPartFieldName.description)
        this.articulusInteriorYearControl = this.articulusInteriorForm.controls[ArticulusInteriorFormPartFieldName.year]
        this.articulusInteriorYearValidatorNames = this.articulusInteriorFormPartValidationService.validatorNames(ArticulusInteriorFormPartFieldName.year)
    }
}
