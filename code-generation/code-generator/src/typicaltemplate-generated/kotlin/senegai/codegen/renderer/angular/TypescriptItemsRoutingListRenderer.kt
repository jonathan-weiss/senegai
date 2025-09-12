/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemsModel

/**
 * Generate the content for the template TypescriptItemsRoutingListRenderer filled up
 * with the content of the passed models.
 */
object TypescriptItemsRoutingListRenderer : ItemsRenderer {

    override fun renderTemplate(model: ItemsModel): String {
        return """
          |
          |
          |
          |import {Routes} from '@angular/router';${ model.allItems.joinToString("") { item ->  """
              |
              |import {${item.itemName}BoardComponent} from './${item.itemNameLowercase}/${item.itemNameLowercase}-board/${item.itemNameLowercase}-board.component';
              |import {${item.itemName}FormComponent} from '@app/${item.itemNameLowercase}/${item.itemNameLowercase}-form/${item.itemNameLowercase}-form/${item.itemNameLowercase}-form.component';
          """ } }
          |
          |
          |export const GENERATED_ITEMS_ROUTES: Routes = [${ model.allItems.joinToString("") { item ->  """
              |    {path: '${item.itemNameLowercase}-board', component: ${item.itemName}BoardComponent},
              |    {path: '${item.itemNameLowercase}-board/edit/:id', component: ${item.itemName}FormComponent},
          """ } }];
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemsModel): String {
      return "generated-routes.ts"
    }
}