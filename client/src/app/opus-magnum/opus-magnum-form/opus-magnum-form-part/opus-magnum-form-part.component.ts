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
import {FormUtil} from "@app/shared/form-controls/form.util";
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {OpusMagnumFormPartValidationService} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-validation.service";
import {
    OpusMagnumFormPartFieldName,
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {
    OpusMagnumFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-group";
import {
    LibraryAwardTableComponent
} from "@app/opus-magnum/opus-magnum-form/library-award-table/library-award-table.component";
import {
    LibraryAwardFormPartComponent
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part.component";
import {GenderEnum, GenderEnumValues} from "@app/wto/gender.enum";
import {GenderI18nComponent} from "@app/opus-magnum/gender-i18n/gender-i18n.component";
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";
import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";

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
        DatepickerInputComponent,
        MatSelect,
        MatOption,
        LibraryAwardTableComponent,
        LibraryAwardFormPartComponent,
        GenderI18nComponent,
        BooleanInputComponent,
        SectionSplitterComponent,
        TextBlockComponent,
    ]
})
export class OpusMagnumFormPartComponent implements OnInit {
    @Input({ required: true }) opusMagnumForm!: FormGroup<OpusMagnumFormPartGroup>;

    opusMagnumLibraryAwardUnderEdit: FormGroup<LibraryAwardFormPartGroup> | undefined = undefined;

    protected idControl!: FormControl<number>
    protected firstnameControl!: FormControl<string>
    protected firstnameValidatorNames!: ReadonlyArray<ValidatorTranslation>

    protected nicknameIsNotNullControl!: FormControl<boolean>
    protected nicknameIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected nicknameControl!: FormControl<string | null>
    protected nicknameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected lastnameControl!: FormControl<string>
    protected lastnameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected libraryAwardListFormArray!: FormArray<FormGroup<LibraryAwardFormPartGroup>>
    protected libraryAwardListValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayIsNotNullControl!: FormControl<boolean>
    protected birthdayIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayControl!: FormControl<Date | null>
    protected birthdayValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected vegetarianControl!: FormControl<boolean>
    protected vegetarianValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected genderControl!: FormControl<GenderEnum>
    protected genderValidatorNames!: ReadonlyArray<ValidatorTranslation>

    constructor(private readonly opusMagnumFormValidationService: OpusMagnumFormPartValidationService,) {
    }

    ngOnInit() {
        this.idControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.id);
        this.firstnameControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.firstname)
        this.firstnameValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.firstname)
        this.nicknameIsNotNullControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.nicknameIsNotNull)
        this.nicknameIsNotNullValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.nicknameIsNotNull)
        this.nicknameControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.nickname)
        this.nicknameValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.nickname)
        this.lastnameControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.lastname)
        this.lastnameValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.lastname)
        this.libraryAwardListFormArray = FormUtil.requiredFormArray(this.opusMagnumForm, OpusMagnumFormPartFieldName.libraryAwardList)
        this.libraryAwardListValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.libraryAwardList)
        this.birthdayIsNotNullControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.birthdayIsNotNull)
        this.birthdayIsNotNullValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.birthdayIsNotNull)
        this.birthdayControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.birthday)
        this.birthdayValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.birthday)
        this.vegetarianControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.vegetarian)
        this.vegetarianValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.vegetarian)
        this.genderControl = FormUtil.requiredFormControl(this.opusMagnumForm, OpusMagnumFormPartFieldName.gender)
        this.genderValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.gender)
    }

    protected genderList = GenderEnumValues

    onOpusMagnumLibraryAwardFormGroupEdit(formGroup: FormGroup<LibraryAwardFormPartGroup>): void {
        this.opusMagnumLibraryAwardUnderEdit = formGroup;
    }

    onOpusMagnumLibraryAwardFormGroupDelete(formGroup: FormGroup<LibraryAwardFormPartGroup>): void {
        if(this.opusMagnumLibraryAwardUnderEdit == formGroup) {
            this.opusMagnumLibraryAwardUnderEdit = undefined
        }
        FormUtil.removeControl(this.libraryAwardListFormArray, formGroup)
    }

    closeOpusMagnumLibraryAwardUnderEdit(): void {
        this.opusMagnumLibraryAwardUnderEdit = undefined;
    }
}
