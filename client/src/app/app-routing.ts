import {Routes} from '@angular/router';
import {AUTHOR_ROUTES} from "@app/app-author-routing";
import {GENERATED_ITEMS_ROUTES} from "@app/generated-routes";
import {AuthorRoutableEditComponent} from "@app/author/author-routable-edit/author-routable-edit.component";

export const ROUTES: Routes = [
    ...AUTHOR_ROUTES,
    ...GENERATED_ITEMS_ROUTES,
    {path: 'author-routable-edit/:id', component: AuthorRoutableEditComponent},
    {path: '', redirectTo: '/author-board', pathMatch: 'full'}
];

