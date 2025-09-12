/* @tt{{{

  @template-renderer [ templateRendererClassName="TypescriptSideNavLinkList" templateRendererPackageName="senegai.codegen.renderer.angular" ]

  @template-model [
      modelClassName="ItemsModel"
      modelPackageName="senegai.codegen.renderer.model"
      modelName="model"
  ]

}}}@ */

/* @tt{{{
    @replace-value-by-value
        [ searchValue="SIDE_NAVIGATION_LINKS" replaceByValue="GENERATED_SIDE_NAVIGATION_LINKS" ]
        [ searchValue="side-nav-links.ts" replaceByValue="generated-side-nav-links.ts" ]

    @modify-provided-filename-by-replacements

}}}@  */

import {SideNavLink} from "@app/side-nav/side-nav-list/side-nav-link.model";

export const SIDE_NAVIGATION_LINKS: ReadonlyArray<SideNavLink> = [
    /* @tt{{{
        @foreach [ iteratorExpression="model.allItems" loopVariable="item" ]

        @replace-value-by-expression
            [ searchValue="author" replaceByExpression="item.itemNameLowercase" ]
            [ searchValue="Authors" replaceByExpression="item.itemName" ]

        @slac
      }}}@  */
    {routeLink: '/author-board', name: "Authors", icon: "people"},
/* @tt{{{ @slbc  @end-replace-value-by-expression @end-foreach  @slac }}}@ */
]
/* @tt{{{ @slbc  @end-replace-value-by-value @slac }}}@ */


