/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template EntityServiceRenderer filled up
 * with the content of the passed models.
 */
object EntityServiceRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {Observable, of} from 'rxjs';
          |import {delay} from 'rxjs/operators';
          |import {${model.entityName.pascalCase}WTO} from "@app/wto/${model.entityName.kebabCase}.wto";
          |import {${model.entityName.screamingSnakeCase}_EXAMPLE_DATA} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-example-data";
          |
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName.pascalCase}Service {
          |    private ${model.entityName.camelCase}List: ${model.entityName.pascalCase}WTO[] = []
          |
          |    constructor() {
          |        this.${model.entityName.camelCase}List = ${model.entityName.screamingSnakeCase}_EXAMPLE_DATA
          |    }
          |
          |    get${model.entityName.pascalCase}s(): Observable<${model.entityName.pascalCase}WTO[]> {
          |        // Simulate HTTP delay
          |        return of(this.${model.entityName.camelCase}List).pipe(delay(200));
          |    }
          |
          |    get${model.entityName.pascalCase}ById(id: string): Observable<${model.entityName.pascalCase}WTO | null> {
          |        const found = this.${model.entityName.camelCase}List.find(a => a.id === id) || null;
          |        return of(found).pipe(delay(200));
          |    }
          |
          |    delete${model.entityName.pascalCase}(id: string): Observable<void> {
          |        this.${model.entityName.camelCase}List = this.${model.entityName.camelCase}List.filter(a => a.id !== id);
          |        return of(void 0).pipe(delay(200));
          |    }
          |
          |    update${model.entityName.pascalCase}(${model.entityName.camelCase}: ${model.entityName.pascalCase}WTO): Observable<${model.entityName.pascalCase}WTO> {
          |        const idx = this.${model.entityName.camelCase}List.findIndex(a => a.id === ${model.entityName.camelCase}.id);
          |        if (idx !== -1) {
          |            this.${model.entityName.camelCase}List[idx] = {...${model.entityName.camelCase}};
          |        }
          |        return of(${model.entityName.camelCase}).pipe(delay(200));
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}.service.ts"
    }
}