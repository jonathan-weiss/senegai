/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `TypescriptSideNavLinkListRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `side-nav-links.ts`
 * - path: `side-nav-links.ts`
 */
object TypescriptSideNavLinkListRenderer : UiEntitiesRenderer {

    override fun renderTemplate(models: List<UiEntityModel>): String {
        return """
          |
          |
          |import {SideNavLink} from "@app/side-nav/side-nav-list/side-nav-link.model";
          |
          |export const GENERATED_SIDE_NAVIGATION_LINKS: ReadonlyArray<SideNavLink> = [
          |${ models.joinToString("") { entity ->  """    {routeLink: '/${entity.entityName.kebabCase}-board', name: "${entity.entityName.pascalCase}", icon: "people"},
              |    {routeLink: '/${entity.entityName.kebabCase}-edit-first-entry', name: "Edit First ${entity.entityName.pascalCase}", icon: "code"},
              |
              |""" } }]
          |
          |
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(models: List<UiEntityModel>): String {
      return "generated-side-nav-links.ts"
    }
}