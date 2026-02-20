import {Injectable} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {LibraryAwardWTO} from "@app/wto/library-award.wto";
import {
    LibraryAwardFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-validation.service";
import {
    LibraryAwardFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-initial-value.service";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";
import {
    LibraryAwardFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-field-name";


@Injectable({providedIn: 'root'})
export class LibraryAwardFormPartService {

    constructor(
        private libraryAwardFormValidationService: LibraryAwardFormPartValidationService,
        private libraryAwardFormInitialValueService: LibraryAwardFormPartInitialValueService,
    ) {}

    public createInitialLibraryAwardForm(): FormGroup<LibraryAwardFormPartGroup> {
        return new FormGroup({
            [LibraryAwardFormPartFieldName.description]: new FormControl<string>(
                this.libraryAwardFormInitialValueService.libraryAwardListDescriptionInitialValue(),
                {
                    nonNullable: true,
                    validators: this.libraryAwardFormValidationService.validatorFunctions(LibraryAwardFormPartFieldName.description)
                },
            ),
            [LibraryAwardFormPartFieldName.year]: new FormControl<number>(
                this.libraryAwardFormInitialValueService.libraryAwardListYearInitialValue(),
                {
                    nonNullable: true,
                    validators: this.libraryAwardFormValidationService.validatorFunctions(LibraryAwardFormPartFieldName.year)
                },
            ),
            [LibraryAwardFormPartFieldName.juryList]: new FormArray<FormControl<string>>(
                [] as Array<FormControl<string>>,
                {
                    validators: this.libraryAwardFormValidationService.validatorFunctions(LibraryAwardFormPartFieldName.year)
                },
            ),
        });
    }

    public createInitialLibraryAwardListJuryListForm(): FormControl<string> {
        return new FormControl<string>(
            this.libraryAwardFormInitialValueService.libraryAwardListJuryListInitialValue(),
            {
                nonNullable: true,
                validators: this.libraryAwardFormValidationService.validatorFunctions(LibraryAwardFormPartFieldName.juryList)
            },
        )
    }

    public patchLibraryAwardForm(form: FormGroup<LibraryAwardFormPartGroup>, libraryAward: LibraryAwardWTO): void {
        form.controls[LibraryAwardFormPartFieldName.description].patchValue(libraryAward.description);
        form.controls[LibraryAwardFormPartFieldName.year].patchValue(libraryAward.year);
        form.controls[LibraryAwardFormPartFieldName.juryList].patchValue(libraryAward.juryList)

    }

    public createLibraryAwardFromFormData(form: FormGroup<LibraryAwardFormPartGroup>): LibraryAwardWTO {
        return {
            description: form.controls[LibraryAwardFormPartFieldName.description].getRawValue(),
            year: form.controls[LibraryAwardFormPartFieldName.year].getRawValue(),
            juryList: form.controls[LibraryAwardFormPartFieldName.juryList].getRawValue(),
        };
    }
}
