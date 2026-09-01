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
          |import {Observable} from 'rxjs';
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |import {${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}-search-criteria.wto";
          |import {${model.entityRootItem.itemName.pascalCase}SearchResultWTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}-search-result.wto";
          |import {${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaWTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}-by-ids-criteria.wto";
          |import {${model.entityRootItem.itemName.pascalCase}ByIdsResultWTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}-by-ids-result.wto";
          |import {UUID} from "@app/shared/uuid";
          |
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName.pascalCase}Service {
          |    private readonly baseUrl = 'http://localhost:8081/api/${model.entityName.kebabCase}';
          |
          |    constructor(private readonly http: HttpClient) {}
          |
          |    get${model.entityRootItem.itemName.pascalCase}List(): Observable<${model.entityRootItem.itemName.pascalCase}WTO[]> {
          |        return this.http.get<${model.entityRootItem.itemName.pascalCase}WTO[]>(this.baseUrl);
          |    }
          |
          |    search${model.entityRootItem.itemName.pascalCase}List(searchCriteria: ${model.entityRootItem.itemName.pascalCase}SearchCriteriaWTO): Observable<${model.entityRootItem.itemName.pascalCase}SearchResultWTO> {
          |        return this.http.post<${model.entityRootItem.itemName.pascalCase}SearchResultWTO>(`${"$"}{this.baseUrl}/search`, searchCriteria);
          |    }
          |
          |    /**
          |     * Resolves a whole set of references to this entity in one call, so that a list of stored
          |     * identifiers can be shown by the display attributes instead of the bare UUIDs.
          |     */
          |    get${model.entityRootItem.itemName.pascalCase}ListByIds(criteria: ${model.entityRootItem.itemName.pascalCase}ByIdsCriteriaWTO): Observable<${model.entityRootItem.itemName.pascalCase}ByIdsResultWTO> {
          |        return this.http.post<${model.entityRootItem.itemName.pascalCase}ByIdsResultWTO>(`${"$"}{this.baseUrl}/by-ids`, criteria);
          |    }
          |
          |    get${model.entityRootItem.itemName.pascalCase}ById(${model.idAttribute.attributeName.camelCase}: UUID): Observable<${model.entityRootItem.itemName.pascalCase}WTO | null> {
          |        return this.http.get<${model.entityRootItem.itemName.pascalCase}WTO | null>(`${"$"}{this.baseUrl}/${"$"}{${model.idAttribute.attributeName.camelCase}}`);
          |    }
          |
          |    delete${model.entityRootItem.itemName.pascalCase}(${model.idAttribute.attributeName.camelCase}: UUID): Observable<void> {
          |        return this.http.delete<void>(`${"$"}{this.baseUrl}/${"$"}{${model.idAttribute.attributeName.camelCase}}`);
          |    }
          |
          |    update${model.entityRootItem.itemName.pascalCase}(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): Observable<${model.entityRootItem.itemName.pascalCase}WTO> {
          |        return this.http.put<${model.entityRootItem.itemName.pascalCase}WTO>(`${"$"}{this.baseUrl}/${"$"}{${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}}`, ${model.entityRootItem.itemName.camelCase});
          |    }
          |
          |    create${model.entityRootItem.itemName.pascalCase}(${model.entityName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): Observable<${model.entityRootItem.itemName.pascalCase}WTO> {
          |        return this.http.post<${model.entityRootItem.itemName.pascalCase}WTO>(this.baseUrl, ${model.entityName.camelCase});
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}.service.ts"
    }
}