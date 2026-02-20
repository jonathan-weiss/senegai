/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template TypescriptItemsRoutingListRenderer filled up
 * with the content of the passed models.
 */
object TypescriptItemsRoutingListRenderer : UiEntitiesRenderer {

    override fun renderTemplate(models: List<UiEntityModel>): String {
        return """
          |
          |
          |
          |import {Routes} from '@angular/router';${ models.joinToString("") { entity ->  """
              |
              |import {${entity.entityName.pascalCase}BoardComponent} from '@app/${entity.entityName.kebabCase}/${entity.entityName.kebabCase}-board/${entity.entityName.kebabCase}-board.component';
              |import {${entity.entityName.pascalCase}FormComponent} from '@app/${entity.entityName.kebabCase}/${entity.entityName.kebabCase}-form/${entity.entityName.kebabCase}-form/${entity.entityName.kebabCase}-form.component';
              |import {
              |    ${entity.entityName.pascalCase}RoutableEditComponent
              |} from "@app/${entity.entityName.kebabCase}/${entity.entityName.kebabCase}-routable-edit/${entity.entityName.kebabCase}-routable-edit.component";
          """ } }
          |
          |
          |export const GENERATED_ITEMS_ROUTES: Routes = [${ models.joinToString("") { entity ->  """
              |    {path: '${entity.entityName.kebabCase}-board', component: ${entity.entityName.pascalCase}BoardComponent},
              |    {path: '${entity.entityName.kebabCase}-edit/:id', component: ${entity.entityName.pascalCase}RoutableEditComponent},
          """ } }];
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(models: List<UiEntityModel>): String {
      return "generated-routes.ts"
    }
}