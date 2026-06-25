import {ApplicationConfig, isDevMode} from "@angular/core";
import {provideHttpClient} from "@angular/common/http";
import {provideTransloco} from "@jsverse/transloco";
import {provideAnimations} from "@angular/platform-browser/animations";
import {PreloadAllModules, provideRouter, withPreloading, withRouterConfig} from "@angular/router";
import {ROUTES} from "@app/app-routing";
import {TranslocoHttpLoader} from "@app/transloco-http-loader";
import {
    CampusTextusObligatoriusNamedValidator
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-silva-optionum-form-part/opus-magnum-silva-optionum-form-part-validation.service";
import {minimalLengthNamedValidator} from "@app/named-validators/field-validators";

export const appConfig: ApplicationConfig = {
    providers: [
        provideHttpClient(),
        provideTransloco({
            config: {
                availableLangs: ['en', 'de'],
                defaultLang: 'en',
                // Remove this option if your application doesn't support changing language in runtime.
                reRenderOnLangChange: true,
                prodMode: !isDevMode(),
            },
            loader: TranslocoHttpLoader,
        }),
        provideRouter(
            ROUTES,
            withPreloading(PreloadAllModules),
            withRouterConfig({
                onSameUrlNavigation: "reload",
            })
        ),
        provideAnimations(),
        // Configure here Validators used in the entity/item forms
        { provide: CampusTextusObligatoriusNamedValidator, useValue: minimalLengthNamedValidator(3) },
    ],
};
