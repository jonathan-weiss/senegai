/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemsModel

/**
 * Generate the content for the template TypescriptSideNavLinkList filled up
 * with the content of the passed models.
 */
object TypescriptSideNavLinkList {

    fun renderTemplate(model: ItemsModel): String {
        return """
          |
          |
          |
          |
          |import {SideNavLink} from "@app/side-nav/side-nav-list/side-nav-link.model";
          |
          |export const GENERATED_SIDE_NAVIGATION_LINKS: ReadonlyArray<SideNavLink> = [
          |    ${ model.allItems.joinToString("") { item ->  """    {routeLink: '/${item.itemNameLowercase}-board', name: "${item.itemName}", icon: "people"},
          """ } }]
          |
          |
        """.trimMargin(marginPrefix = "|")
    }
}