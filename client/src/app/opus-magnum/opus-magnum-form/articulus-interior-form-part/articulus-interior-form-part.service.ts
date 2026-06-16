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
import {
    SilvaOptionumFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";


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
        this.patchPreparation(form, articulusInterior);
        form.controls[ArticulusInteriorFormPartFieldName.description].patchValue(articulusInterior.description);
        form.controls[ArticulusInteriorFormPartFieldName.year].patchValue(articulusInterior.year);
        form.controls[ArticulusInteriorFormPartFieldName.juryList].patchValue(articulusInterior.juryList)

        this.patchNestedItems(form, articulusInterior)
    }

    /**
     * patchValue does not create missing FormGroups inside the FormArray.
     * So if your FormArray is empty (or shorter than the incoming data), nothing (or only the first N) gets patched.
     * We need to prefill the FormArray with empty values first
     */
    private patchPreparation(form: FormGroup<ArticulusInteriorFormPartGroup>, articulusInterior: ArticulusInteriorWTO): void {
        const juryListLength = form.controls[ArticulusInteriorFormPartFieldName.juryList].controls.length
        if(juryListLength < articulusInterior.juryList.length) {
            for (let i = juryListLength; i < articulusInterior.juryList.length; i++) {
                form.controls[ArticulusInteriorFormPartFieldName.juryList].push(this.createInitialArticulusInteriorListJuryListForm())
            }
        }
    }

    private patchNestedItems(form: FormGroup<ArticulusInteriorFormPartGroup>, articulusInterior: ArticulusInteriorWTO): void {
        for (let i = 0; i < articulusInterior.juryList.length; i++) {
            form.controls[ArticulusInteriorFormPartFieldName.juryList].at(i).patchValue(articulusInterior.juryList[i])
        }
    }

    public createArticulusInteriorWTOFromForm(form: FormGroup<ArticulusInteriorFormPartGroup>): ArticulusInteriorWTO {
        return {
            description: form.controls[ArticulusInteriorFormPartFieldName.description].getRawValue(),
            year: form.controls[ArticulusInteriorFormPartFieldName.year].getRawValue(),
            juryList: form.controls[ArticulusInteriorFormPartFieldName.juryList].getRawValue(),
        };
    }
}
