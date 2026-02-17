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

import {AuthorBoardComponent} from './author/author-board/author-board.component';
import {AuthorFormComponent} from '@app/author/author-form/author-form/author-form.component';
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
    {path: 'author-board', component: AuthorBoardComponent},
    {path: 'author-board/edit/:id', component: AuthorFormComponent},
/* @tt{{{
    @slbc
    @end-foreach
    @slac
}}}@ */
];

