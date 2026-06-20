import {Injectable} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
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
            [ArticulusInteriorFormPartFieldName.scriptumTriviale]: new FormControl<string>(
                this.articulusInteriorFormInitialValueService.articulusInteriorListScriptumTrivialeInitialValue(),
                {
                    nonNullable: true,
                    validators: this.articulusInteriorFormValidationService.validatorFunctions(ArticulusInteriorFormPartFieldName.scriptumTriviale)
                },
            ),
            [ArticulusInteriorFormPartFieldName.numerusStupidus]: new FormControl<number>(
                this.articulusInteriorFormInitialValueService.articulusInteriorListNumerusStupidusInitialValue(),
                {
                    nonNullable: true,
                    validators: this.articulusInteriorFormValidationService.validatorFunctions(ArticulusInteriorFormPartFieldName.numerusStupidus)
                },
            ),
        });
    }

    public patchArticulusInteriorForm(form: FormGroup<ArticulusInteriorFormPartGroup>, articulusInterior: ArticulusInteriorWTO): void {
        form.controls[ArticulusInteriorFormPartFieldName.scriptumTriviale].patchValue(articulusInterior.scriptumTriviale);
        form.controls[ArticulusInteriorFormPartFieldName.numerusStupidus].patchValue(articulusInterior.numerusStupidus);
    }

    public createArticulusInteriorWTOFromForm(form: FormGroup<ArticulusInteriorFormPartGroup>): ArticulusInteriorWTO {
        return {
            scriptumTriviale: form.controls[ArticulusInteriorFormPartFieldName.scriptumTriviale].getRawValue(),
            numerusStupidus: form.controls[ArticulusInteriorFormPartFieldName.numerusStupidus].getRawValue(),
        };
    }
}
