/// <reference types="@angular/localize" />

import {bootstrapApplication} from "@angular/platform-browser";
import {AppComponent} from "./app/app.component";
import {ROUTES} from "./app/app-routing";
import {PreloadAllModules, provideRouter, withPreloading, withRouterConfig} from "@angular/router";
import {provideHttpClient} from "@angular/common/http";
import {provideAnimations} from "@angular/platform-browser/animations";

bootstrapApplication(AppComponent, {
    providers: [
        provideAnimations(),
        provideHttpClient(),
        provideRouter(
            ROUTES,
            withPreloading(PreloadAllModules),
            withRouterConfig({
                onSameUrlNavigation: "reload",
            })
        ),
    ],
}).catch((err) => console.error(err));
