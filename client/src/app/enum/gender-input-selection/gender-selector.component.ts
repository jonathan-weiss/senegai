import {Component, Input} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TranslocoPipe} from "@jsverse/transloco";
import {GenderI18nComponent} from "@app/enum/gender-i18n/gender-i18n.component";
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {GenderEnumValues} from "@app/wto/gender.enum";

@Component({
    selector: 'app-gender-selector',
    templateUrl: './gender-selector.component.html',
    styleUrls: ['./gender-selector.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        TranslocoPipe,
        GenderI18nComponent,
        MatOption,
        MatSelect,
    ]
})
export class GenderSelectorComponent {
    @Input() label: string = '';
    @Input({required: true}) enumFormControl!: FormControl;
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];

    hasError(errorName: string): boolean {
        return this.enumFormControl.hasError(errorName) && this.enumFormControl.touched;
    }


    protected readonly genderList = GenderEnumValues;
}
