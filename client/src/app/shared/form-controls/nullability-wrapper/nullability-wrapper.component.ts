import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import {AbstractControl, FormControl, ReactiveFormsModule} from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {CommonModule} from '@angular/common';
import {Subscription} from 'rxjs';
import {MatLabel} from "@angular/material/form-field";
import {MatSlideToggle} from "@angular/material/slide-toggle";

@Component({
    selector: 'app-nullability-wrapper',
    templateUrl: './nullability-wrapper.component.html',
    styleUrls: ['./nullability-wrapper.component.scss'],
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, MatCheckboxModule, MatLabel, MatSlideToggle]
})
export class NullabilityWrapperComponent implements OnInit, OnDestroy {
    @Input({ required: true}) nullabilityCheckboxFormControl!: FormControl;
    @Input({ required: true}) formGroupToDisable!: AbstractControl;
    @Input({ required: true}) label!: String;

    private subscription: Subscription = new Subscription();

    ngOnInit(): void {
        // Subscribe to checkbox changes and update form group enabled state
        this.subscription.add(
            this.nullabilityCheckboxFormControl.valueChanges.subscribe(isEnabled => {
                if (isEnabled) {
                    this.formGroupToDisable.enable();
                } else {
                    this.formGroupToDisable.disable();
                }
            })
        );

        // Set initial state
        if (this.nullabilityCheckboxFormControl.value) {
            this.formGroupToDisable.enable();
        } else {
            this.formGroupToDisable.disable();
        }
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
}
