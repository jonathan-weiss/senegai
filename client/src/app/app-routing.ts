import {Routes} from '@angular/router';
import {GENERATED_ITEMS_ROUTES} from "@app/generated-routes";
import {OPUS_MAGNUM_ROUTES} from "@app/opus-magnum/opus-magnum-routing";

export const ROUTES: Routes = [
    ...OPUS_MAGNUM_ROUTES,
    ...GENERATED_ITEMS_ROUTES,
    {path: '', redirectTo: '/opus-magnum-board', pathMatch: 'full'}
];

