/* @tt{{{

  @move-comment-backward
  @template-renderer [
      templateRendererClassName="TypescriptItemsRoutingListRenderer"
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
        [ searchValue="app-routing-template.ts" replaceByValue="generated-routes.ts" ]
    @modify-provided-filepath-by-replacements
    @end-replace-value-by-value

    @replace-value-by-expression
        [ searchValue="opus-magnum" replaceByExpression="entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="entity.entityName.camelCase" ]
        [ searchValue="OPUS_MAGNUM" replaceByExpression="entity.entityName.screamingSnakeCase" ]
}}}@  */

import {Routes} from '@angular/router';
/* @tt{{{ @foreach [ iteratorExpression="models" loopVariable="entity" ] }}}@  */
import {OPUS_MAGNUM_ROUTES} from "@app/opus-magnum/opus-magnum-routing";
/* @tt{{{ @end-foreach }}}@ */

/* @tt{{{
    @replace-value-by-value
        [ searchValue="TEMPLATE_ROUTES" replaceByValue="GENERATED_ITEMS_ROUTES" ]
}}}@  */

export const TEMPLATE_ROUTES: Routes = [
/* @tt{{{ @end-replace-value-by-value }}}@  */
/* @tt{{{ @foreach [ iteratorExpression="models" loopVariable="entity" ] }}}@  */
    ...OPUS_MAGNUM_ROUTES,
/* @tt{{{ @end-foreach }}}@ */
];

