/* @tt{{{

  @template-renderer [ templateRendererClassName="TypescriptItemsRoutingList" templateRendererPackageName="senegai.codegen.renderer.angular" ]

  @template-model [
      modelClassName="ItemsModel"
      modelPackageName="senegai.codegen.renderer.model"
      modelName="model"
  ]

}}}@ */

import {Routes} from '@angular/router';
/* @tt{{{
    @slbc
    @replace-value-by-expression
        [ searchValue="author" replaceByExpression="item.itemNameLowercase" ]
        [ searchValue="Author" replaceByExpression="item.itemName" ]

    @foreach [ iteratorExpression="model.allItems" loopVariable="item" ]
}}}@  */

import {AuthorBoardComponent} from './author/author-board/author-board.component';
import {AuthorEditFormComponent} from './author/author-edit-form/author-edit-form.component';
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
    {path: 'author-board/edit/:id', component: AuthorEditFormComponent},
/* @tt{{{
    @slbc
    @end-foreach
    @slac
}}}@ */
];

