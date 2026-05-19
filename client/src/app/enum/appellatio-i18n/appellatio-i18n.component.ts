import {Component, Input} from '@angular/core';
import {AppellatioEnum} from "@app/wto/appellatio.enum";

@Component({
    selector: 'app-appellatio-i18n',
    templateUrl: './appellatio-i18n.component.html',
    styleUrls: ['./appellatio-i18n.component.scss'],
    standalone: true,
})
export class AppellatioI18nComponent {
    @Input({ required: true }) enumValue!: AppellatioEnum;
}
