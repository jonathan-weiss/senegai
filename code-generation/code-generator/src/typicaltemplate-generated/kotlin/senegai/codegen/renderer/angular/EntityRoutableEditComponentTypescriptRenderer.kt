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
          |import {${model.entityName}WTO} from "@app/wto/${model.entityNameDashCase}.wto";
          |import {${model.entityName}Service} from "@app/${model.entityNameDashCase}/${model.entityNameDashCase}.service";
          |import {${model.entityName}FormComponent} from "@app/${model.entityNameDashCase}/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form/${model.entityNameDashCase}-form.component";
          |import {ActivatedRoute} from "@angular/router";
          |
          |
          |@Component({
          |    selector: 'app-${model.entityNameDashCase}-routable-edit',
          |    templateUrl: './${model.entityNameDashCase}-routable-edit.component.html',
          |    styleUrls: ['./${model.entityNameDashCase}-routable-edit.component.scss'],
          |    imports: [
          |        ${model.entityName}FormComponent,
          |    ]
          |})
          |export class ${model.entityName}RoutableEditComponent {
          |    selected${model.entityName}: ${model.entityName}WTO | null = null;
          |
          |    constructor(
          |        private ${model.entityNameLowercase}Service: ${model.entityName}Service,
          |        private route: ActivatedRoute,
          |    ) {
          |        this.route.params.subscribe(params => {
          |            const idParam = params['id'];
          |            if (idParam) {
          |                const id = idParam as string;
          |                this.${model.entityNameLowercase}Service.get${model.entityName}ById(id).subscribe(${model.entityNameLowercase} => {
          |                    this.selected${model.entityName} = ${model.entityNameLowercase};
          |                });
          |            }
          |        });
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}-routable-edit/${model.entityNameDashCase}-routable-edit.component.ts"
    }
}