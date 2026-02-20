import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {
    LibraryAwardJuryTableComponent
} from "@app/opus-magnum/opus-magnum-form/library-award-jury-table/library-award-jury-table.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {NumberInputComponent} from "@app/shared/form-controls/number-input/number-input.component";
import {
    LibraryAwardFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-field-name";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";
import {
    LibraryAwardFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-validation.service";

@Component({
    selector: 'app-library-award-form-part',
    templateUrl: './library-award-form-part.component.html',
    styleUrls: ['./library-award-form-part.component.scss'],
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
        LibraryAwardJuryTableComponent,
    ]
})
export class LibraryAwardFormPartComponent implements OnInit {
    @Input({required: true}) libraryAwardForm!: FormGroup<LibraryAwardFormPartGroup>;

    constructor(private readonly libraryAwardFormPartValidationService: LibraryAwardFormPartValidationService,) {
    }

    protected libraryAwardDescriptionControl!: FormControl<string>
    protected libraryAwardDescriptionValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected libraryAwardYearControl!: FormControl<number>
    protected libraryAwardYearValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected libraryAwardJuryListFormArray!: FormArray<FormControl<string>>
    protected libraryAwardJuryListValidatorNames!: ReadonlyArray<ValidatorTranslation>

    ngOnInit() {
        this.libraryAwardDescriptionControl = FormUtil.requiredFormControl(this.libraryAwardForm, LibraryAwardFormPartFieldName.description)
        this.libraryAwardDescriptionValidatorNames = this.libraryAwardFormPartValidationService.validatorNames(LibraryAwardFormPartFieldName.description)
        this.libraryAwardYearControl = FormUtil.requiredFormControl(this.libraryAwardForm, LibraryAwardFormPartFieldName.year)
        this.libraryAwardYearValidatorNames = this.libraryAwardFormPartValidationService.validatorNames(LibraryAwardFormPartFieldName.year)
        this.libraryAwardJuryListFormArray = FormUtil.requiredFormArray(this.libraryAwardForm, LibraryAwardFormPartFieldName.juryList)
        this.libraryAwardJuryListValidatorNames = this.libraryAwardFormPartValidationService.validatorNames(LibraryAwardFormPartFieldName.juryList)
    }
}
