import {Component, Input} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TranslocoPipe} from "@jsverse/transloco";
import {MatCheckbox} from "@angular/material/checkbox";

@Component({
    selector: 'app-boolean-input',
    templateUrl: './boolean-input.component.html',
    styleUrls: ['./boolean-input.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        TranslocoPipe,
        MatCheckbox,
    ]
})
export class BooleanInputComponent {
    @Input() label: string = '';
    @Input({required: true}) booleanFormControl!: FormControl;
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];

    hasError(errorName: string): boolean {
        return this.booleanFormControl.hasError(errorName) && this.booleanFormControl.touched;
    }


}
