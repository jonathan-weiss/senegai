/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `TypescriptItemsRoutingListRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `app-opus-magnum-routing.ts`
 * - path: `app-opus-magnum-routing.ts`
 */
object TypescriptItemsRoutingListRenderer : UiEntitiesRenderer {

    override fun renderTemplate(models: List<UiEntityModel>): String {
        return """
          |
          |import {Routes} from '@angular/router';
          |${ models.joinToString("") { entity ->  """
              |import {${entity.entityName.pascalCase}BoardComponent} from '@app/${entity.entityName.kebabCase}/${entity.entityName.kebabCase}-board/${entity.entityName.kebabCase}-board.component';
              |import {
              |    ${entity.entityName.pascalCase}RoutableEditComponent
              |} from "@app/${entity.entityName.kebabCase}/${entity.entityName.kebabCase}-routable-edit/${entity.entityName.kebabCase}-routable-edit.component";
              |import {${entity.entityName.camelCase}FirstEntryEditGuard} from "@app/${entity.entityName.kebabCase}/${entity.entityName.kebabCase}-first-entry-edit.guard";
              |""" } }
          |
          |export const GENERATED_ITEMS_ROUTES: Routes = [
          |${ models.joinToString("") { entity ->  """    {path: '${entity.entityName.kebabCase}-board', component: ${entity.entityName.pascalCase}BoardComponent},
              |    {path: '${entity.entityName.kebabCase}-edit-first-entry', canActivate: [${entity.entityName.camelCase}FirstEntryEditGuard], children: []},
              |    {path: '${entity.entityName.kebabCase}-routable-edit/:${entity.idAttribute.attributeName.camelCase}', component: ${entity.entityName.pascalCase}RoutableEditComponent},
              |    {path: '${entity.entityName.kebabCase}-edit/:${entity.idAttribute.attributeName.camelCase}', component: ${entity.entityName.pascalCase}RoutableEditComponent},
              |""" } }];
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(models: List<UiEntityModel>): String {
      return "generated-routes.ts"
    }
}