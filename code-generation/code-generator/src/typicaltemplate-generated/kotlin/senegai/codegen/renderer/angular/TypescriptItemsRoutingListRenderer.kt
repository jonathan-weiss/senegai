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
              |import {${entity.entityName}BoardComponent} from './${entity.entityNameLowercase}/${entity.entityNameLowercase}-board/${entity.entityNameLowercase}-board.component';
              |import {${entity.entityName}FormComponent} from '@app/${entity.entityNameLowercase}/${entity.entityNameLowercase}-form/${entity.entityNameLowercase}-form/${entity.entityNameLowercase}-form.component';
          """ } }
          |
          |
          |export const GENERATED_ITEMS_ROUTES: Routes = [${ models.joinToString("") { entity ->  """
              |    {path: '${entity.entityNameLowercase}-board', component: ${entity.entityName}BoardComponent},
              |    {path: '${entity.entityNameLowercase}-board/edit/:id', component: ${entity.entityName}FormComponent},
          """ } }];
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(models: List<UiEntityModel>): String {
      return "generated-routes.ts"
    }
}