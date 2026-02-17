import {Injectable} from '@angular/core';
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
import {AbstractControl, FormArray, FormControl, FormGroup} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    OpusMagnumFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-validation.service";
import {
    OpusMagnumFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-initial-value.service";
import {OpusMagnumFormPartFieldName} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {OpusMagnumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-group";
import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {GenderEnum} from "@app/wto/gender.enum";
import {
    LibraryAwardFormPartService
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part.service";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";


@Injectable({providedIn: 'root'})
export class OpusMagnumFormPartService {

    constructor(
        private opusMagnumFormValidationService: OpusMagnumFormPartValidationService,
        private opusMagnumFormInitialValueService: OpusMagnumFormPartInitialValueService,
        private libraryAwardFormPartService: LibraryAwardFormPartService,
    ) {}

    public createInitialOpusMagnumForm(): FormGroup<OpusMagnumFormPartGroup> {
        return new FormGroup({
            [OpusMagnumFormPartFieldName.id]: new FormControl<string>(
                {
                    value: this.opusMagnumFormInitialValueService.idInitialValue(),
                    disabled: true, // ID is readonly
                }, {
                    nonNullable: true,
                },
            ),
            [OpusMagnumFormPartFieldName.firstname]: new FormControl<string>(
                this.opusMagnumFormInitialValueService.firstnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.firstname)
                },
            ),
            [OpusMagnumFormPartFieldName.lastname]: new FormControl<string>(
                this.opusMagnumFormInitialValueService.lastnameInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.lastname)
                },
            ),
            [OpusMagnumFormPartFieldName.nicknameIsNotNull]: new FormControl<boolean>(
                this.opusMagnumFormInitialValueService.nicknameInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.nicknameIsNotNull)
                },
            ),
            [OpusMagnumFormPartFieldName.nickname]: new FormControl<string | null>(
                this.opusMagnumFormInitialValueService.nicknameInitialValue(),
                {
                    nonNullable: false,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.nickname)
                },
            ),
            [OpusMagnumFormPartFieldName.libraryAwardList]: new FormArray(
                [] as Array<FormGroup<LibraryAwardFormPartGroup>>,
                {
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.libraryAwardList)
                },
            ),
            [OpusMagnumFormPartFieldName.birthdayIsNotNull]: new FormControl<boolean>(
                this.opusMagnumFormInitialValueService.birthdayInitialValue() != null,
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.birthdayIsNotNull)
                },
            ),
            [OpusMagnumFormPartFieldName.birthday]: new FormControl<Date>(
                this.opusMagnumFormInitialValueService.birthdayInitialValue(),
                {
                    nonNullable: false,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.birthday)
                },
            ),
            [OpusMagnumFormPartFieldName.vegetarian]: new FormControl<boolean>(
                this.opusMagnumFormInitialValueService.vegetarianInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.vegetarian)
                },
            ),
            [OpusMagnumFormPartFieldName.gender]: new FormControl<GenderEnum>(
                this.opusMagnumFormInitialValueService.genderInitialValue(),
                {
                    nonNullable: true,
                    validators: this.opusMagnumFormValidationService.validatorFunctions(OpusMagnumFormPartFieldName.gender)
                },
            ),
        });
    }

    public patchOpusMagnumForm(form: AbstractControl, opusMagnum: OpusMagnumWTO): void {
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.id).patchValue(opusMagnum.id);
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.firstname).patchValue(opusMagnum.firstname);
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.nicknameIsNotNull).patchValue(!opusMagnum.nickname);
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.nickname).patchValue(opusMagnum.nickname ?? null);
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.lastname).patchValue(opusMagnum.lastname);
        const libraryAwardList = FormUtil.requiredFormArray(form, OpusMagnumFormPartFieldName.libraryAwardList);
        opusMagnum.libraryAwardList.forEach((libraryAward: LibraryAwardWTO) => {
            const formGroup = this.libraryAwardFormPartService.createInitialLibraryAwardForm()
            this.libraryAwardFormPartService.patchLibraryAwardForm(formGroup, libraryAward);
            libraryAwardList.push(formGroup);
        })
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.birthdayIsNotNull).patchValue(!opusMagnum.birthday);
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.birthday).patchValue(opusMagnum.birthday ?? null);
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.vegetarian).patchValue(opusMagnum.vegetarian);
        FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.gender).patchValue(opusMagnum.gender);
    }

    public createOpusMagnumFromFormData(form: AbstractControl): OpusMagnumWTO {
        return {
            id: FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.id).value as string,
            firstname: FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.firstname).value as string,
            nickname: FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.nicknameIsNotNull).value
                ? FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.nickname).value as string
                : null,
            lastname: FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.lastname).value as string,
            libraryAwardList: FormUtil.requiredFormArray<FormGroup<LibraryAwardFormPartGroup>>(form, OpusMagnumFormPartFieldName.libraryAwardList)
                .controls.map(control => this.libraryAwardFormPartService.createLibraryAwardFromFormData(control)),
            birthday: FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.birthdayIsNotNull).value ? FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.birthday).value as Date : null,
            vegetarian: FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.vegetarian).value as boolean,
            gender: FormUtil.requiredFormControl(form, OpusMagnumFormPartFieldName.gender).value as GenderEnum,
        };
    }
}
