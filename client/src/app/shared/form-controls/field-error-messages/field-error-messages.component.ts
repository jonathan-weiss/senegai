import {Component, Input} from '@angular/core';
import {AbstractControl} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {TranslocoPipe} from '@jsverse/transloco';
import {ValidatorTranslation} from '@app/shared/form-controls/validator-translation';

/**
 * Renders the list of validation error messages for a single form control.
 *
 *   <app-field-error-messages
 *       [control]="someControl" [validatorTranslations]="someTranslations" />
 */
@Component({
    selector: 'app-field-error-messages',
    templateUrl: './field-error-messages.component.html',
    standalone: true,
    imports: [
        MatFormFieldModule,
        TranslocoPipe,
    ],
})
export class FieldErrorMessagesComponent {
    @Input({required: true}) control!: AbstractControl;
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];

    hasError(errorName: string): boolean {
        return this.control.hasError(errorName) && this.control.touched;
    }

    /**
     * Merges the static translation params with the dynamic payload a validator
     * stored in the control's error (e.g. { nextEven: 6 }), so messages can
     * reference values that only become known at validation time.
     */
    translationParams(validatorTranslation: ValidatorTranslation): Record<string, unknown> {
        const errorPayload = this.control.getError(validatorTranslation.validatorName);
        const dynamicParams = (errorPayload && typeof errorPayload === 'object') ? errorPayload : {};
        return {...validatorTranslation.validatorTranslationParams, ...dynamicParams};
    }
}
