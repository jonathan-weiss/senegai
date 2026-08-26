/* @tt{{{

  @move-comment-backward
  @template-renderer [
      templateRendererClassName="TypescriptSideNavLinkListRenderer"
      templateRendererPackageName="senegai.codegen.renderer.angular"
      templateRendererInterfaceName="UiEntitiesRenderer"
      templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
  ] [
      isList="yes"
      modelClassName="UiEntityModel"
      modelPackageName="senegai.codegen.renderer.model.ui"
      modelName="models"
  ]

}}}@ */

/* @tt{{{
    @replace-value-by-value
        [ searchValue="ENTITY_SIDE_NAVIGATION_LINKS" replaceByValue="GENERATED_ENTITY_SIDE_NAVIGATION_LINKS" ]
        [ searchValue="entity-side-nav-links.ts" replaceByValue="generated-entity-side-nav-links.ts" ]

    @modify-provided-filepath-by-replacements

        @replace-value-by-expression
            [ searchValue="OPUS_MAGNUM" replaceByExpression="entity.entityName.screamingSnakeCase" ]
            [ searchValue="opus-magnum" replaceByExpression="entity.entityName.kebabCase" ]
            [ searchValue="opusMagnum" replaceByExpression="entity.entityName.camelCase" ]

}}}@  */

import {SideNavLink} from "@app/side-nav/side-nav-list/side-nav-link.model";
/* @tt{{{ @foreach [ iteratorExpression="models" loopVariable="entity" ] }}}@  */
import {OPUS_MAGNUM_SIDE_NAVIGATION_LINKS} from "@app/opus-magnum/opus-magnum-routing";
/* @tt{{{   @end-foreach   }}}@ */

export const ENTITY_SIDE_NAVIGATION_LINKS: ReadonlyArray<SideNavLink> = [
    /* @tt{{{ @foreach [ iteratorExpression="models" loopVariable="entity" ] }}}@  */
    ...OPUS_MAGNUM_SIDE_NAVIGATION_LINKS,
    /* @tt{{{   @end-foreach   }}}@ */
]


