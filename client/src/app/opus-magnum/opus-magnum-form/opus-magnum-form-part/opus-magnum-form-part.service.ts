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
import {AuthorFormPartService} from "../author-form-part/author-form-part.service";


@Injectable({providedIn: 'root'})
export class OpusMagnumFormPartService {

    constructor(
        private opusMagnumFormInitialValueService: OpusMagnumFormPartInitialValueService,
        private authorFormPartService: AuthorFormPartService,
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
            [OpusMagnumFormPartFieldName.author]: this.authorFormPartService.createInitialAuthorForm(),
        });
    }

    public patchOpusMagnumForm(form: FormGroup<OpusMagnumFormPartGroup>, opusMagnum: OpusMagnumWTO): void {
        form.controls[OpusMagnumFormPartFieldName.id].patchValue(opusMagnum.id);
        form.controls[OpusMagnumFormPartFieldName.title].patchValue(opusMagnum.title);
        this.authorFormPartService.patchAuthorForm(form.controls[OpusMagnumFormPartFieldName.author], opusMagnum.author)
    }

    public createOpusMagnumFromFormData(form: FormGroup<OpusMagnumFormPartGroup>): OpusMagnumWTO {
        return {
            id: form.controls[OpusMagnumFormPartFieldName.id].value,
            title: form.controls[OpusMagnumFormPartFieldName.title].value,
            author: this.authorFormPartService.createAuthorFromFormData(form.controls[OpusMagnumFormPartFieldName.author]),
        };
    }
}
