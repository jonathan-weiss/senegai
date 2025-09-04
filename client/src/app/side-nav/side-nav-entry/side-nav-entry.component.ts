import {Component, Input} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from "@angular/material/list";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
    selector: 'side-nav-entry',
    templateUrl: './side-nav-entry.component.html',
    standalone: true,
    styleUrls: ['./side-nav-entry.component.scss'],
    imports: [
        MatIconModule,
        MatListModule,
        RouterLink,
        RouterLinkActive,
    ],
    providers: [],
})
export class SideNavEntryComponent {

    @Input() routerLink!: string;
    @Input() linkText!: string;
    @Input() iconName: string | undefined;


    constructor() {
    }

} 
