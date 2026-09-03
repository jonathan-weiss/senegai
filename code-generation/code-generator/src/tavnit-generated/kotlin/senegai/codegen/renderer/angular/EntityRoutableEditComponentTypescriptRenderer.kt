/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityRoutableEditComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-routable-edit.component.ts`
 * - path: `opus-magnum/opus-magnum-routable-edit/opus-magnum-routable-edit.component.ts`
 */
object EntityRoutableEditComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component} from '@angular/core';
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |import {${model.entityRootItem.itemName.pascalCase}Service} from "@app/service/${model.entityRootItem.itemName.kebabCase}.service";
          |import {${model.entityName.pascalCase}FormComponent} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form.component";
          |import {ActivatedRoute} from "@angular/router";
          |import {UUID} from "@app/shared/uuid";
          |
          |
          |@Component({
          |    selector: 'app-${model.entityName.kebabCase}-routable-edit',
          |    templateUrl: './${model.entityName.kebabCase}-routable-edit.component.html',
          |    styleUrls: ['./${model.entityName.kebabCase}-routable-edit.component.scss'],
          |    imports: [
          |        ${model.entityName.pascalCase}FormComponent,
          |    ]
          |})
          |export class ${model.entityName.pascalCase}RoutableEditComponent {
          |    selected${model.entityName.pascalCase}: ${model.entityRootItem.itemName.pascalCase}WTO | null = null;
          |
          |    constructor(
          |        private ${model.entityRootItem.itemName.camelCase}Service: ${model.entityRootItem.itemName.pascalCase}Service,
          |        private route: ActivatedRoute,
          |    ) {
          |        this.route.params.subscribe(params => {
          |            const idParam = params['${model.idAttribute.attributeName.camelCase}'];
          |            if (idParam) {
          |                const ${model.idAttribute.attributeName.camelCase} = idParam as UUID;
          |                this.${model.entityRootItem.itemName.camelCase}Service.get${model.entityRootItem.itemName.pascalCase}ById(${model.idAttribute.attributeName.camelCase}).subscribe(${model.entityName.camelCase} => {
          |                    this.selected${model.entityName.pascalCase} = ${model.entityName.camelCase};
          |                });
          |            }
          |        });
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-routable-edit/${model.entityName.kebabCase}-routable-edit.component.ts"
    }
}