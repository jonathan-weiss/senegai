import {Component, Input} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TranslocoPipe} from "@jsverse/transloco";
import {AppellatioI18nComponent} from "@app/enum/appellatio-i18n/appellatio-i18n.component";
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {AppellatioEnumValues} from "@app/wto/appellatio.enum";

@Component({
    selector: 'app-appellatio-selector',
    templateUrl: './appellatio-selector.component.html',
    styleUrls: ['./appellatio-selector.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        TranslocoPipe,
        AppellatioI18nComponent,
        MatOption,
        MatSelect,
    ]
})
export class AppellatioSelectorComponent {
    @Input() label: string = '';
    @Input({required: true}) enumFormControl!: FormControl;
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];

    hasError(errorName: string): boolean {
        return this.enumFormControl.hasError(errorName) && this.enumFormControl.touched;
    }


    protected readonly appellatioList = AppellatioEnumValues;
}
