/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemsModel

/**
 * Generate the content for the template TypescriptItemsRoutingList filled up
 * with the content of the passed models.
 */
object TypescriptItemsRoutingList {

    fun renderTemplate(model: ItemsModel): String {
        return """
          |
          |
          |import {Routes} from '@angular/router';${ model.allItems.joinToString("") { item ->  """
              |
              |import {${item.itemName}BoardComponent} from './${item.itemNameLowercase}/${item.itemNameLowercase}-board/${item.itemNameLowercase}-board.component';
              |import {${item.itemName}EditFormComponent} from './${item.itemNameLowercase}/${item.itemNameLowercase}-edit-form/${item.itemNameLowercase}-edit-form.component';
          """ } }
          |
          |
          |export const GENERATED_ITEMS_ROUTES: Routes = [${ model.allItems.joinToString("") { item ->  """
              |    {path: '${item.itemNameLowercase}-board', component: ${item.itemName}BoardComponent},
              |    {path: '${item.itemNameLowercase}-board/edit/:id', component: ${item.itemName}EditFormComponent},
          """ } }];
          |
          |
        """.trimMargin(marginPrefix = "|")
    }
}