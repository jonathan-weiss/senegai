import {Routes} from '@angular/router';
import {GENERATED_ENTITY_ROUTES} from "@app/generated-routes";
import {ENTITY_ROUTES} from "@app/entity-routing";

export const ROUTES: Routes = [
    ...ENTITY_ROUTES,
    ...GENERATED_ENTITY_ROUTES,
    {path: '', redirectTo: '/opus-magnum-board', pathMatch: 'full'}
];

