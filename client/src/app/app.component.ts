import {Component} from '@angular/core';
import {MatIconModule, MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatDialogModule} from "@angular/material/dialog";
import {RouterOutlet} from "@angular/router";
import {SideNavLinkListComponent} from "./side-nav/side-nav-list/side-nav-link-list.component";
import {SIDE_NAVIGATION_LINKS} from "./side-nav-links";
import {OTHER_SIDE_NAVIGATION_LINKS} from "./more-side-nav-links";
import {GENERATED_SIDE_NAVIGATION_LINKS} from "../app-generated/generated-side-nav-links";

@Component({
    imports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatToolbarModule,
        MatTableModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatExpansionModule,
        MatSidenavModule,
        MatListModule,
        MatDialogModule,
        RouterOutlet,
        SideNavLinkListComponent,
    ],
    selector: 'app-root',
    standalone: true,
    styleUrls: ['./app.component.scss'],
    templateUrl: './app.component.html',
})
export class AppComponent {
    title = 'senegal';
    sidenavOpened = true;

    sideNavLinks = SIDE_NAVIGATION_LINKS
    generatedSideNavLinks = GENERATED_SIDE_NAVIGATION_LINKS
    otherSideNavLinks = OTHER_SIDE_NAVIGATION_LINKS

    constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
        iconRegistry.addSvgIcon(
            'github',
            sanitizer.bypassSecurityTrustResourceUrl('assets/github.svg')
        );
    }

    toggleSidenav(): void {
        this.sidenavOpened = !this.sidenavOpened;
    }
} 
