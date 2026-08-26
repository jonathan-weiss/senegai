import {Routes} from '@angular/router';
import {OPUS_MAGNUM_ROUTES} from "@app/app-opus-magnum-routing";
import {GENERATED_ITEMS_ROUTES} from "@app/generated-routes";

export const ROUTES: Routes = [
    ...OPUS_MAGNUM_ROUTES,
    ...GENERATED_ITEMS_ROUTES,
    {path: '', redirectTo: '/opus-magnum-board', pathMatch: 'full'}
];

