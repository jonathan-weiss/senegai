import {Component, Input} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormControl, ReactiveFormsModule} from '@angular/forms';

@Component({
    selector: 'app-text-input',
    templateUrl: './text-input.component.html',
    styleUrls: ['./text-input.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
    ]
})
export class TextInputComponent {
    @Input() label: string = '';
    @Input() placeholder: string = '';
    @Input({required: true}) textFormControl!: FormControl;
    @Input() validatorNames: ReadonlyArray<string> = [];
    @Input() readonly: boolean = false;

    hasError(errorName: string): boolean {
        return this.textFormControl.hasError(errorName) && this.textFormControl.touched;
    }


}
