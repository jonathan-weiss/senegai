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
        [ searchValue="app-author-routing.ts" replaceByValue="generated-routes.ts" ]
    @modify-provided-filename-by-replacements
    @end-replace-value-by-value
}}}@  */

import {Routes} from '@angular/router';
/* @tt{{{
    @slbc
    @replace-value-by-expression
        [ searchValue="author" replaceByExpression="entity.entityNameLowercase" ]
        [ searchValue="Author" replaceByExpression="entity.entityName" ]

    @foreach [ iteratorExpression="models" loopVariable="entity" ]
}}}@  */

import {OpusMagnumBoardComponent} from '@app/opus-magnum/opus-magnum-board/opus-magnum-board.component';
import {OpusMagnumFormComponent} from '@app/opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component';
/* @tt{{{
    @slbc
    @end-foreach
    @slac
}}}@ */

/* @tt{{{
    @replace-value-by-value
        [ searchValue="AUTHOR_ROUTES" replaceByValue="GENERATED_ITEMS_ROUTES" ]

}}}@  */

export const AUTHOR_ROUTES: Routes = [
/* @tt{{{
    @slbc
    @replace-value-by-expression
        [ searchValue="authors" replaceByExpression="entity.entityNameLowercase" ]
        [ searchValue="author" replaceByExpression="entity.entityNameLowercase" ]
        [ searchValue="Author" replaceByExpression="entity.entityName" ]

    @foreach [ iteratorExpression="models" loopVariable="entity" ]
}}}@  */
    {path: 'author-board', component: OpusMagnumBoardComponent},
    {path: 'author-board/edit/:id', component: OpusMagnumFormComponent},
/* @tt{{{
    @slbc
    @end-foreach
    @slac
}}}@ */
];

