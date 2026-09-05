/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityRoutingRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-routing.ts`
 * - path: `opus-magnum/opus-magnum-routing.ts`
 */
object EntityRoutingRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {Routes} from '@angular/router';
          |import {${model.entityName.pascalCase}BoardComponent} from '@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-board/${model.entityName.kebabCase}-board.component';
          |import {
          |    ${model.entityName.pascalCase}RoutableEditComponent
          |} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-routable-edit/${model.entityName.kebabCase}-routable-edit.component";
          |import {${model.entityName.camelCase}FirstEntryEditGuard} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-first-entry-edit.guard";
          |import {SideNavLink} from "@app/side-nav/side-nav-list/side-nav-link.model";
          |
          |export const ${model.entityName.screamingSnakeCase}_ROUTES: Routes = [
          |    {path: '${model.entityName.kebabCase}-board', component: ${model.entityName.pascalCase}BoardComponent},
          |    {path: '${model.entityName.kebabCase}-edit-first-entry', canActivate: [${model.entityName.camelCase}FirstEntryEditGuard], children: []},
          |    {path: '${model.entityName.kebabCase}-routable-edit/:${model.idAttribute.attributeName.camelCase}', component: ${model.entityName.pascalCase}RoutableEditComponent},
          |    {path: '${model.entityName.kebabCase}-edit/:${model.idAttribute.attributeName.camelCase}', component: ${model.entityName.pascalCase}RoutableEditComponent},
          |];
          |
          |/**
          | * t(${model.entityName.camelCase}.sideNav.board)
          | * t(${model.entityName.camelCase}.sideNav.editFirstEntry)
          | */
          |export const ${model.entityName.screamingSnakeCase}_SIDE_NAVIGATION_LINKS: ReadonlyArray<SideNavLink> = [
          |    {routeLink: '/${model.entityName.kebabCase}-board', nameTranslationKey: "${model.entityName.camelCase}.sideNav.board", icon: "people"},
          |]
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-routing.ts"
    }
}