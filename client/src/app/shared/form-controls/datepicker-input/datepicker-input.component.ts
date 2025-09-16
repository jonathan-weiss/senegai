import {Component, Input} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TranslocoPipe} from "@jsverse/transloco";
import {MatNativeDateModule} from "@angular/material/core";

@Component({
    selector: 'app-datepicker-input',
    templateUrl: './datepicker-input.component.html',
    styleUrls: ['./datepicker-input.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatNativeDateModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerInput,
        MatDatepickerToggle,
        MatDatepicker,
        TranslocoPipe,
    ]
})
export class DatepickerInputComponent {
    @Input() label: string = '';
    @Input() placeholder: string = '';
    @Input({required: true}) datepickerFormControl!: FormControl;
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];
    @Input() readonly: boolean = false;

    hasError(errorName: string): boolean {
        return this.datepickerFormControl.hasError(errorName) && this.datepickerFormControl.touched;
    }


}
