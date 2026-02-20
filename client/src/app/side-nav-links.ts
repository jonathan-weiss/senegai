/* @tt{{{

  @template-renderer [ templateRendererClassName="TypescriptSideNavLinkListRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntitiesRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

  @template-model [
      isList="true"
      modelClassName="UiEntityModel"
      modelPackageName="senegai.codegen.renderer.model.ui"
      modelName="models"
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
        @foreach [ iteratorExpression="models" loopVariable="entity" ]

        @replace-value-by-expression
            [ searchValue="opus-magnum" replaceByExpression="entity.entityName.kebabCase" ]
            [ searchValue="opusMagnum" replaceByExpression="entity.entityName.camelCase" ]
            [ searchValue="Opera Magna" replaceByExpression="entity.entityName.pascalCase" ]

        @slac
      }}}@  */
    {routeLink: '/opus-magnum-board', name: "Opera Magna", icon: "people"},
    {routeLink: '/opus-magnum-edit/example', name: "Edit Opera Magna", icon: "edit"},
/* @tt{{{ @slbc  @end-replace-value-by-expression @end-foreach  @slac }}}@ */
]
/* @tt{{{ @slbc  @end-replace-value-by-value @slac }}}@ */


