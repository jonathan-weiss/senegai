
import {Routes} from '@angular/router';
import {EntitasRelataBoardComponent} from '@app/entitas-relata/entitas-relata-board/entitas-relata-board.component';
import {
    EntitasRelataRoutableEditComponent
} from "@app/entitas-relata/entitas-relata-routable-edit/entitas-relata-routable-edit.component";
import {entitasRelataFirstEntryEditGuard} from "@app/entitas-relata/entitas-relata-first-entry-edit.guard";
import {SideNavLink} from "@app/side-nav/side-nav-list/side-nav-link.model";

export const ENTITAS_RELATA_ROUTES: Routes = [
    {path: 'entitas-relata-board', component: EntitasRelataBoardComponent},
    {path: 'entitas-relata-routable-edit/:clavisPrimaria', component: EntitasRelataRoutableEditComponent},
    {path: 'entitas-relata-edit/:clavisPrimaria', component: EntitasRelataRoutableEditComponent},
];

export const ENTITAS_RELATA_SIDE_NAVIGATION_LINKS: ReadonlyArray<SideNavLink> = [
    {routeLink: '/entitas-relata-board', name: "EntitasRelata", icon: "people"},
]
