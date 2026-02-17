import {Routes} from '@angular/router';
import {OPUS_MAGNUM_ROUTES} from "@app/app-opus-magnum-routing";
import {GENERATED_ITEMS_ROUTES} from "@app/generated-routes";
import {
    OpusMagnumRoutableEditComponent
} from "@app/helper/opus-magnum-routable-edit/opus-magnum-routable-edit.component";

export const ROUTES: Routes = [
    ...OPUS_MAGNUM_ROUTES,
    ...GENERATED_ITEMS_ROUTES,
    {path: 'opus-magnum-routable-edit/:id', component: OpusMagnumRoutableEditComponent},
    {path: '', redirectTo: '/opus-magnum-board', pathMatch: 'full'}
];

