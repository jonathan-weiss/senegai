/* @tt{{{

  @template-renderer [ templateRendererClassName="TypescriptItemsRoutingListRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemsRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

  @template-model [
      modelClassName="ItemsModel"
      modelPackageName="senegai.codegen.renderer.model"
      modelName="model"
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
        [ searchValue="author" replaceByExpression="item.itemNameLowercase" ]
        [ searchValue="Author" replaceByExpression="item.itemName" ]

    @foreach [ iteratorExpression="model.allItems" loopVariable="item" ]
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
        [ searchValue="authors" replaceByExpression="item.itemNameLowercase" ]
        [ searchValue="author" replaceByExpression="item.itemNameLowercase" ]
        [ searchValue="Author" replaceByExpression="item.itemName" ]

    @foreach [ iteratorExpression="model.allItems" loopVariable="item" ]
}}}@  */
    {path: 'author-board', component: AuthorBoardComponent},
    {path: 'author-board/edit/:id', component: AuthorFormComponent},
/* @tt{{{
    @slbc
    @end-foreach
    @slac
}}}@ */
];

