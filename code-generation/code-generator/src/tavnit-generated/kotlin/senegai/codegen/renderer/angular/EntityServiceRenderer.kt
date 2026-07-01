/*
 * This file is generated using tavnit.
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
          |import {Injectable} from '@angular/core';
          |import {HttpClient} from '@angular/common/http';
          |import {Observable, of} from 'rxjs';
          |import {delay} from 'rxjs/operators';
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |import {${model.entityRootItem.itemName.screamingSnakeCase}_EXAMPLE_DATA} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-example-data";
          |
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName.pascalCase}Service {
          |    /**
          |     * Data source switch: when `true` the service serves the in-memory
          |     * {@link ${model.entityRootItem.itemName.screamingSnakeCase}_EXAMPLE_DATA} constant; when `false` it calls the Spring Boot
          |     * REST API at {@link baseUrl}. Flip to `false` to consume the real backend.
          |     */
          |    private readonly useExampleData = true;
          |    private readonly baseUrl = 'http://localhost:8080/api/${model.entityName.kebabCase}';
          |
          |    private ${model.entityRootItem.itemName.camelCase}List: ${model.entityRootItem.itemName.pascalCase}WTO[] = []
          |
          |    constructor(private readonly http: HttpClient) {
          |        this.${model.entityRootItem.itemName.camelCase}List = ${model.entityRootItem.itemName.screamingSnakeCase}_EXAMPLE_DATA
          |    }
          |
          |    get${model.entityRootItem.itemName.pascalCase}List(): Observable<${model.entityRootItem.itemName.pascalCase}WTO[]> {
          |        if (!this.useExampleData) {
          |            return this.http.get<${model.entityRootItem.itemName.pascalCase}WTO[]>(this.baseUrl);
          |        }
          |        // Simulate HTTP delay
          |        return of(this.${model.entityRootItem.itemName.camelCase}List).pipe(delay(200));
          |    }
          |
          |    get${model.entityRootItem.itemName.pascalCase}ById(${model.idAttribute.attributeName.camelCase}: string): Observable<${model.entityRootItem.itemName.pascalCase}WTO | null> {
          |        if (!this.useExampleData) {
          |            return this.http.get<${model.entityRootItem.itemName.pascalCase}WTO | null>(`${"$"}{this.baseUrl}/${"$"}{${model.idAttribute.attributeName.camelCase}}`);
          |        }
          |        const found = this.${model.entityRootItem.itemName.camelCase}List.find(a => a.${model.idAttribute.attributeName.camelCase} === ${model.idAttribute.attributeName.camelCase}) || null;
          |        return of(found).pipe(delay(200));
          |    }
          |
          |    delete${model.entityRootItem.itemName.pascalCase}(${model.idAttribute.attributeName.camelCase}: string): Observable<void> {
          |        if (!this.useExampleData) {
          |            return this.http.delete<void>(`${"$"}{this.baseUrl}/${"$"}{${model.idAttribute.attributeName.camelCase}}`);
          |        }
          |        this.${model.entityRootItem.itemName.camelCase}List = this.${model.entityRootItem.itemName.camelCase}List.filter(a => a.${model.idAttribute.attributeName.camelCase} !== ${model.idAttribute.attributeName.camelCase});
          |        return of(void 0).pipe(delay(200));
          |    }
          |
          |    update${model.entityRootItem.itemName.pascalCase}(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): Observable<${model.entityRootItem.itemName.pascalCase}WTO> {
          |        if (!this.useExampleData) {
          |            return this.http.put<${model.entityRootItem.itemName.pascalCase}WTO>(`${"$"}{this.baseUrl}/${"$"}{${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}}`, ${model.entityRootItem.itemName.camelCase});
          |        }
          |        const idx = this.${model.entityRootItem.itemName.camelCase}List.findIndex(a => a.${model.idAttribute.attributeName.camelCase} === ${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase});
          |        if (idx !== -1) {
          |            this.${model.entityRootItem.itemName.camelCase}List[idx] = {...${model.entityRootItem.itemName.camelCase}};
          |        }
          |        return of(${model.entityRootItem.itemName.camelCase}).pipe(delay(200));
          |    }
          |
          |    create${model.entityRootItem.itemName.pascalCase}(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): Observable<${model.entityRootItem.itemName.pascalCase}WTO> {
          |        if (!this.useExampleData) {
          |            return this.http.post<${model.entityRootItem.itemName.pascalCase}WTO>(this.baseUrl, ${model.entityName.camelCase});
          |        }
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