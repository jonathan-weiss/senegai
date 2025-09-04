import {Component, Input} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from "@angular/material/list";
import {RouterLink} from "@angular/router";
import {SideNavEntryComponent} from "../side-nav-entry/side-nav-entry.component";
import {SideNavLink} from "./side-nav-link.model";

@Component({
    selector: 'side-nav-link-list',
    templateUrl: './side-nav-link-list.component.html',
    styleUrls: ['./side-nav-link-list.component.scss'],
    standalone: true,
    imports: [
        MatIconModule,
        MatListModule,
        RouterLink,
        SideNavEntryComponent,
    ],
})
export class SideNavLinkListComponent {
    @Input() sideNavLinks: ReadonlyArray<SideNavLink> = []

} 
