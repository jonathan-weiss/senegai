/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template TypescriptSideNavLinkListRenderer filled up
 * with the content of the passed models.
 */
object TypescriptSideNavLinkListRenderer : UiEntitiesRenderer {

    override fun renderTemplate(models: List<UiEntityModel>): String {
        return """
          |
          |
          |
          |
          |import {SideNavLink} from "@app/side-nav/side-nav-list/side-nav-link.model";
          |
          |export const GENERATED_SIDE_NAVIGATION_LINKS: ReadonlyArray<SideNavLink> = [
          |    ${ models.joinToString("") { entity ->  """    {routeLink: '/${entity.entityNameLowercase}-board', name: "${entity.entityName}", icon: "people"},
          """ } }]
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(models: List<UiEntityModel>): String {
      return "generated-side-nav-links.ts"
    }
}