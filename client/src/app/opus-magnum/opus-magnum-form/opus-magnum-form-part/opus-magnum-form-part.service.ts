import {Injectable} from '@angular/core';
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
import {AbstractControl, FormControl, FormGroup} from "@angular/forms";
import {FormUtil} from "@app/shared/form-controls/form.util";
import {
    OpusMagnumFormPartInitialValueService
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-initial-value.service";
import {
    OpusMagnumFormPartFieldName
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {
    OpusMagnumFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-group";
import {SilvaOptionumFormPartService} from "../silva-optionum-form-part/silva-optionum-form-part.service";


@Injectable({providedIn: 'root'})
export class OpusMagnumFormPartService {

    constructor(
        private opusMagnumFormInitialValueService: OpusMagnumFormPartInitialValueService,
        private silvaOptionumFormPartService: SilvaOptionumFormPartService,
    ) {}

    public createInitialOpusMagnumForm(): FormGroup<OpusMagnumFormPartGroup> {
        return new FormGroup<OpusMagnumFormPartGroup>({
            [OpusMagnumFormPartFieldName.id]: new FormControl<string>(
                {
                    value: this.opusMagnumFormInitialValueService.idInitialValue(),
                    disabled: true, // ID is readonly
                }, {
                    nonNullable: true,
                },
            ),
            [OpusMagnumFormPartFieldName.title]: new FormControl<string>(
                this.opusMagnumFormInitialValueService.titleInitialValue(),
                {
                    nonNullable: true,
                },
            ),
            [OpusMagnumFormPartFieldName.silvaOptionum]: this.silvaOptionumFormPartService.createInitialSilvaOptionumForm(),
        });
    }

    public patchOpusMagnumForm(form: FormGroup<OpusMagnumFormPartGroup>, opusMagnum: OpusMagnumWTO): void {
        this.silvaOptionumFormPartService.patchPreparation(form.controls[OpusMagnumFormPartFieldName.silvaOptionum], opusMagnum.silvaOptionum)

        form.controls[OpusMagnumFormPartFieldName.id].patchValue(opusMagnum.id);
        form.controls[OpusMagnumFormPartFieldName.title].patchValue(opusMagnum.title);
        form.controls[OpusMagnumFormPartFieldName.silvaOptionum].patchValue(opusMagnum.silvaOptionum)
    }

    public createOpusMagnumFromFormData(form: FormGroup<OpusMagnumFormPartGroup>): OpusMagnumWTO {
        return {
            id: form.controls[OpusMagnumFormPartFieldName.id].getRawValue(),
            title: form.controls[OpusMagnumFormPartFieldName.title].getRawValue(),
            silvaOptionum: form.controls[OpusMagnumFormPartFieldName.silvaOptionum].getRawValue(),
        };
    }
}
