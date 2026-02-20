/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityRoutableEditComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object EntityRoutableEditComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component} from '@angular/core';
          |import {${model.entityName.pascalCase}WTO} from "@app/wto/${model.entityName.kebabCase}.wto";
          |import {${model.entityName.pascalCase}Service} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}.service";
          |import {${model.entityName.pascalCase}FormComponent} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form/${model.entityName.kebabCase}-form.component";
          |import {ActivatedRoute} from "@angular/router";
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
          |    selected${model.entityName.pascalCase}: ${model.entityName.pascalCase}WTO | null = null;
          |
          |    constructor(
          |        private ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service,
          |        private route: ActivatedRoute,
          |    ) {
          |        this.route.params.subscribe(params => {
          |            const idParam = params['id'];
          |            if (idParam) {
          |                const id = idParam as string;
          |                this.${model.entityName.camelCase}Service.get${model.entityName.pascalCase}ById(id).subscribe(${model.entityName.camelCase} => {
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