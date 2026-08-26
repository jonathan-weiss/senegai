/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `TypescriptEntitiesRoutingListRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entity-routing.ts`
 * - path: `entity-routing.ts`
 */
object TypescriptEntitiesRoutingListRenderer : UiEntitiesRenderer {

    override fun renderTemplate(models: List<UiEntityModel>): String {
        return """
          |import {Routes} from '@angular/router';
          |${ models.joinToString("") { entity ->  """import {${entity.entityName.screamingSnakeCase}_ROUTES} from "@app/${entity.entityName.kebabCase}/${entity.entityName.kebabCase}-routing";
              |""" } }
          |
          |export const GENERATED_ENTITY_ROUTES: Routes = [
          |${ models.joinToString("") { entity ->  """    ...${entity.entityName.screamingSnakeCase}_ROUTES,
              |""" } }];
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(models: List<UiEntityModel>): String {
      return "generated-routes.ts"
    }
}