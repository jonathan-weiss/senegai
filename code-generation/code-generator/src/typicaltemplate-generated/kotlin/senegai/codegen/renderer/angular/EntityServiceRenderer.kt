/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityServiceRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum.service.ts`
 * - path: `opus-magnum/opus-magnum.service.ts`
 */
object EntityServiceRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |
          |import {Injectable} from '@angular/core';
          |import {Observable, of} from 'rxjs';
          |import {delay} from 'rxjs/operators';
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |import {${model.entityRootItem.itemName.screamingSnakeCase}_EXAMPLE_DATA} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-example-data";
          |
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName.pascalCase}Service {
          |    private ${model.entityRootItem.itemName.camelCase}List: ${model.entityRootItem.itemName.pascalCase}WTO[] = []
          |
          |    constructor() {
          |        this.${model.entityRootItem.itemName.camelCase}List = ${model.entityRootItem.itemName.screamingSnakeCase}_EXAMPLE_DATA
          |    }
          |
          |    get${model.entityRootItem.itemName.pascalCase}List(): Observable<${model.entityRootItem.itemName.pascalCase}WTO[]> {
          |        // Simulate HTTP delay
          |        return of(this.${model.entityRootItem.itemName.camelCase}List).pipe(delay(200));
          |    }
          |
          |    get${model.entityRootItem.itemName.pascalCase}ById(${model.idAttribute.attributeName.camelCase}: string): Observable<${model.entityRootItem.itemName.pascalCase}WTO | null> {
          |        const found = this.${model.entityRootItem.itemName.camelCase}List.find(a => a.${model.idAttribute.attributeName.camelCase} === ${model.idAttribute.attributeName.camelCase}) || null;
          |        return of(found).pipe(delay(200));
          |    }
          |
          |    delete${model.entityRootItem.itemName.pascalCase}(${model.idAttribute.attributeName.camelCase}: string): Observable<void> {
          |        this.${model.entityRootItem.itemName.camelCase}List = this.${model.entityRootItem.itemName.camelCase}List.filter(a => a.${model.idAttribute.attributeName.camelCase} !== ${model.idAttribute.attributeName.camelCase});
          |        return of(void 0).pipe(delay(200));
          |    }
          |
          |    update${model.entityRootItem.itemName.pascalCase}(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): Observable<${model.entityRootItem.itemName.pascalCase}WTO> {
          |        const idx = this.${model.entityRootItem.itemName.camelCase}List.findIndex(a => a.${model.idAttribute.attributeName.camelCase} === ${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase});
          |        if (idx !== -1) {
          |            this.${model.entityRootItem.itemName.camelCase}List[idx] = {...${model.entityRootItem.itemName.camelCase}};
          |        }
          |        return of(${model.entityRootItem.itemName.camelCase}).pipe(delay(200));
          |    }
          |
          |    create${model.entityRootItem.itemName.pascalCase}(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): Observable<${model.entityRootItem.itemName.pascalCase}WTO> {
          |        const created: ${model.entityRootItem.itemName.pascalCase}WTO = {...${model.entityName.camelCase}, ${model.idAttribute.attributeName.camelCase}: crypto.randomUUID()};
          |        this.${model.entityRootItem.itemName.camelCase}List = [...this.${model.entityRootItem.itemName.camelCase}List, created];
          |        return of(created).pipe(delay(200));
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}.service.ts"
    }
}