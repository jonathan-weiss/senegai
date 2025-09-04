import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import {AbstractControl, FormControl, ReactiveFormsModule} from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {CommonModule} from '@angular/common';
import {Subscription} from 'rxjs';
import {MatLabel} from "@angular/material/form-field";
import {MatSlideToggle} from "@angular/material/slide-toggle";

@Component({
    selector: 'app-field-wrapper',
    templateUrl: './field-wrapper.component.html',
    styleUrls: ['./field-wrapper.component.scss'],
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, MatCheckboxModule, MatLabel, MatSlideToggle]
})
export class FieldWrapperComponent implements OnInit, OnDestroy {
    @Input({ required: true}) label!: String;
    @Input() nullabilityCheckboxFormControl: FormControl | undefined;
    @Input() formGroupToDisableIfNullField: AbstractControl | undefined;

    private subscription: Subscription = new Subscription();

    ngOnInit(): void {
        if(this.nullabilityCheckboxFormControl) {
            if(this.formGroupToDisableIfNullField) {
                this.subscription.add(
                    this.nullabilityCheckboxFormControl.valueChanges.subscribe(isEnabled => {
                        if(this.formGroupToDisableIfNullField) {
                            if (isEnabled) {
                                this.formGroupToDisableIfNullField.enable();
                            } else {
                                this.formGroupToDisableIfNullField.disable();
                            }
                        }
                    })
                );

                // Set initial state
                if (this.nullabilityCheckboxFormControl.value) {
                    this.formGroupToDisableIfNullField.enable();
                } else {
                    this.formGroupToDisableIfNullField.disable();
                }
            } else {
                throw Error("If 'nullabilityCheckboxFormControl' is passed, then 'formGroupToDisableIfNullField' is required");
            }
        }
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
}
