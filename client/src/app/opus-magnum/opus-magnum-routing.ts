/* @tt{{{


    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityRoutingRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OPUS_MAGNUM" replaceByExpression="model.entityName.screamingSnakeCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityName.kebabCase" ]

    @replace-value-by-expression
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]


    @modify-provided-filepath-by-replacements



}}}@ */

import {Routes} from '@angular/router';
import {OpusMagnumBoardComponent} from '@app/opus-magnum/opus-magnum-board/opus-magnum-board.component';
import {
    OpusMagnumRoutableEditComponent
} from "@app/opus-magnum/opus-magnum-routable-edit/opus-magnum-routable-edit.component";
import {opusMagnumFirstEntryEditGuard} from "@app/opus-magnum/opus-magnum-first-entry-edit.guard";

export const OPUS_MAGNUM_ROUTES: Routes = [
    {path: 'opus-magnum-board', component: OpusMagnumBoardComponent},
    {path: 'opus-magnum-edit-first-entry', canActivate: [opusMagnumFirstEntryEditGuard], children: []},
    {path: 'opus-magnum-routable-edit/:indexUnicus', component: OpusMagnumRoutableEditComponent},
    {path: 'opus-magnum-edit/:indexUnicus', component: OpusMagnumRoutableEditComponent},
];

