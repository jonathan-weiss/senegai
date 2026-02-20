/* @tt{{{

  @template-renderer [ templateRendererClassName="TypescriptItemsRoutingListRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntitiesRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

  @template-model [
      isList="true"
      modelClassName="UiEntityModel"
      modelPackageName="senegai.codegen.renderer.model.ui"
      modelName="models"
  ]

}}}@ */
/* @tt{{{
    @replace-value-by-value
        [ searchValue="app-opus-magnum-routing.ts" replaceByValue="generated-routes.ts" ]
    @modify-provided-filename-by-replacements
    @end-replace-value-by-value
}}}@  */

import {Routes} from '@angular/router';
/* @tt{{{
    @slbc
    @replace-value-by-expression
        [ searchValue="opus-magnum" replaceByExpression="entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="entity.entityName.pascalCase" ]

    @foreach [ iteratorExpression="models" loopVariable="entity" ]
}}}@  */

import {OpusMagnumBoardComponent} from '@app/opus-magnum/opus-magnum-board/opus-magnum-board.component';
import {OpusMagnumFormComponent} from '@app/opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component';
import {
    OpusMagnumRoutableEditComponent
} from "@app/opus-magnum/opus-magnum-routable-edit/opus-magnum-routable-edit.component";
/* @tt{{{
    @slbc
    @end-foreach
    @slac
}}}@ */

/* @tt{{{
    @replace-value-by-value
        [ searchValue="OPUS_MAGNUM_ROUTES" replaceByValue="GENERATED_ITEMS_ROUTES" ]

}}}@  */

export const OPUS_MAGNUM_ROUTES: Routes = [
/* @tt{{{
    @slbc
    @replace-value-by-expression
        [ searchValue="opus-magnum" replaceByExpression="entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="entity.entityName.pascalCase" ]

    @foreach [ iteratorExpression="models" loopVariable="entity" ]
}}}@  */
    {path: 'opus-magnum-board', component: OpusMagnumBoardComponent},
    {path: 'opus-magnum-edit/:id', component: OpusMagnumRoutableEditComponent},
/* @tt{{{
    @slbc
    @end-foreach
    @slac
}}}@ */
];

