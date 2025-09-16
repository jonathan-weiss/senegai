import {Component, Input} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TranslocoPipe} from "@jsverse/transloco";

@Component({
    selector: 'app-number-input',
    templateUrl: './number-input.component.html',
    styleUrls: ['./number-input.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        TranslocoPipe,
    ]
})
export class NumberInputComponent {
    @Input() label: string = '';
    @Input() placeholder: string = '';
    @Input({required: true}) numberFormControl!: FormControl;
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];
    @Input() readonly: boolean = false;

    hasError(errorName: string): boolean {
        return this.numberFormControl.hasError(errorName) && this.numberFormControl.touched;
    }


}
