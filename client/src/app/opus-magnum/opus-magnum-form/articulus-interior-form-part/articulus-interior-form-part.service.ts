import {Injectable} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {ArticulusInteriorWTO} from "@app/wto/articulus-interior.wto";
import {
    ArticulusInteriorFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-validation.service";
import {
    ArticulusInteriorFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-initial-value.service";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
import {
    ArticulusInteriorFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-field-name";


@Injectable({providedIn: 'root'})
export class ArticulusInteriorFormPartService {

    constructor(
        private articulusInteriorFormValidationService: ArticulusInteriorFormPartValidationService,
        private articulusInteriorFormInitialValueService: ArticulusInteriorFormPartInitialValueService,
    ) {}

    public createInitialArticulusInteriorForm(): FormGroup<ArticulusInteriorFormPartGroup> {
        return new FormGroup({
            [ArticulusInteriorFormPartFieldName.description]: new FormControl<string>(
                this.articulusInteriorFormInitialValueService.articulusInteriorListDescriptionInitialValue(),
                {
                    nonNullable: true,
                    validators: this.articulusInteriorFormValidationService.validatorFunctions(ArticulusInteriorFormPartFieldName.description)
                },
            ),
            [ArticulusInteriorFormPartFieldName.year]: new FormControl<number>(
                this.articulusInteriorFormInitialValueService.articulusInteriorListYearInitialValue(),
                {
                    nonNullable: true,
                    validators: this.articulusInteriorFormValidationService.validatorFunctions(ArticulusInteriorFormPartFieldName.year)
                },
            ),
            [ArticulusInteriorFormPartFieldName.juryList]: new FormArray<FormControl<string>>(
                [] as Array<FormControl<string>>,
                {
                    validators: this.articulusInteriorFormValidationService.validatorFunctions(ArticulusInteriorFormPartFieldName.year)
                },
            ),
        });
    }

    public createInitialArticulusInteriorListJuryListForm(): FormControl<string> {
        return new FormControl<string>(
            this.articulusInteriorFormInitialValueService.articulusInteriorListJuryListInitialValue(),
            {
                nonNullable: true,
                validators: this.articulusInteriorFormValidationService.validatorFunctions(ArticulusInteriorFormPartFieldName.juryList)
            },
        )
    }

    public patchArticulusInteriorForm(form: FormGroup<ArticulusInteriorFormPartGroup>, articulusInterior: ArticulusInteriorWTO): void {
        if(form.controls[ArticulusInteriorFormPartFieldName.juryList].controls.length < articulusInterior.juryList.length) {
            for (let i = articulusInterior.juryList.length; i < form.controls[ArticulusInteriorFormPartFieldName.juryList].controls.length; i++) {
                form.controls[ArticulusInteriorFormPartFieldName.juryList].push(this.createInitialArticulusInteriorListJuryListForm())
            }
        }

        form.controls[ArticulusInteriorFormPartFieldName.description].patchValue(articulusInterior.description);
        form.controls[ArticulusInteriorFormPartFieldName.year].patchValue(articulusInterior.year);
        form.controls[ArticulusInteriorFormPartFieldName.juryList].patchValue(articulusInterior.juryList)

    }

    public createArticulusInteriorFromFormData(form: FormGroup<ArticulusInteriorFormPartGroup>): ArticulusInteriorWTO {
        return {
            description: form.controls[ArticulusInteriorFormPartFieldName.description].getRawValue(),
            year: form.controls[ArticulusInteriorFormPartFieldName.year].getRawValue(),
            juryList: form.controls[ArticulusInteriorFormPartFieldName.juryList].getRawValue(),
        };
    }
}
