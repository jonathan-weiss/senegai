import {Component, Input} from '@angular/core';
import {FormArray, FormControl} from "@angular/forms";
import {SingleFormFieldTableComponent} from "@app/shared/form-controls/single-form-field-table/single-form-field-table.component";
import {AppellatioComisSelectorComponent} from "@app/enum/appellatio-comis-input-selection/appellatio-comis-selector.component";
import {AppellatioComisEnum, AppellatioComisEnumValues} from "@app/wto/appellatio-comis.enum";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";

@Component({
    selector: 'app-single-appellatio-comis-form-field-table',
    templateUrl: './single-appellatio-comis-form-field-table.component.html',
    standalone: true,
    imports: [
        SingleFormFieldTableComponent,
        AppellatioComisSelectorComponent,
    ]
})
export class SingleAppellatioComisFormFieldTableComponent {
    @Input({required: true}) formArray!: FormArray;
    @Input() columnHeader: string = '';
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];

    createControl = (): FormControl => new FormControl<AppellatioComisEnum>(AppellatioComisEnumValues[0], {nonNullable: true});
}
