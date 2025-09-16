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
import {MatMenuModule} from "@angular/material/menu";
import {RouterOutlet} from "@angular/router";
import {SideNavLinkListComponent} from "./side-nav/side-nav-list/side-nav-link-list.component";
import {SIDE_NAVIGATION_LINKS} from "./side-nav-links";
import {OTHER_SIDE_NAVIGATION_LINKS} from "./more-side-nav-links";
import {GENERATED_SIDE_NAVIGATION_LINKS} from "../app-generated/generated-side-nav-links";
import {TranslocoService} from "@jsverse/transloco";

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
        MatMenuModule,
        RouterOutlet,
        SideNavLinkListComponent,
    ],
    selector: 'app-root',
    styleUrls: ['./app.component.scss'],
    templateUrl: './app.component.html'
})
export class AppComponent {
    title = 'senegal';
    sidenavOpened = true;
    activeLang: string;

    sideNavLinks = SIDE_NAVIGATION_LINKS
    generatedSideNavLinks = GENERATED_SIDE_NAVIGATION_LINKS
    otherSideNavLinks = OTHER_SIDE_NAVIGATION_LINKS

    constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer, private transloco: TranslocoService) {
        iconRegistry.addSvgIcon(
            'github',
            sanitizer.bypassSecurityTrustResourceUrl('assets/github.svg')
        );
        iconRegistry.addSvgIcon('flag-en', sanitizer.bypassSecurityTrustResourceUrl('assets/flags/en.svg'));
        iconRegistry.addSvgIcon('flag-de', sanitizer.bypassSecurityTrustResourceUrl('assets/flags/de.svg'));
        this.activeLang = this.transloco.getActiveLang();
    }

    toggleSidenav(): void {
        this.sidenavOpened = !this.sidenavOpened;
    }

    setLang(lang: string): void {
        if (lang === this.activeLang) {
            return;
        }
        this.transloco.setActiveLang(lang);
        this.activeLang = lang;
    }
} 
