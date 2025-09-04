import {Component, Input} from '@angular/core';
import {GenderEnum} from "@app/author/gender.enum";

@Component({
    selector: 'app-gender-i18n',
    templateUrl: './gender-i18n.component.html',
    styleUrls: ['./gender-i18n.component.scss'],
    standalone: true,
})
export class GenderI18nComponent {
    @Input({ required: true }) enumValue!: GenderEnum;
}
