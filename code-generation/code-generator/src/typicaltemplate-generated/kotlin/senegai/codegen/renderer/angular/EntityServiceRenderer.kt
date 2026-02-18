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
          |import {${model.entityName}WTO} from "@app/wto/${model.entityNameDashCase}.wto";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName}Service {
          |    private ${model.entityNameLowercase}List: ${model.entityName}WTO[] = []
          |
          |    get${model.entityName}s(): Observable<${model.entityName}WTO[]> {
          |        // Simulate HTTP delay
          |        return of(this.${model.entityNameLowercase}List).pipe(delay(200));
          |    }
          |
          |    get${model.entityName}ById(id: string): Observable<${model.entityName}WTO | null> {
          |        const found = this.${model.entityNameLowercase}List.find(a => a.id === id) || null;
          |        return of(found).pipe(delay(200));
          |    }
          |
          |    delete${model.entityName}(id: string): Observable<void> {
          |        this.${model.entityNameLowercase}List = this.${model.entityNameLowercase}List.filter(a => a.id !== id);
          |        return of(void 0).pipe(delay(200));
          |    }
          |
          |    update${model.entityName}(${model.entityNameLowercase}: ${model.entityName}WTO): Observable<${model.entityName}WTO> {
          |        const idx = this.${model.entityNameLowercase}List.findIndex(a => a.id === ${model.entityNameLowercase}.id);
          |        if (idx !== -1) {
          |            this.${model.entityNameLowercase}List[idx] = {...${model.entityNameLowercase}};
          |        }
          |        return of(${model.entityNameLowercase}).pipe(delay(200));
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameDashCase}/${model.entityNameDashCase}.service.ts"
    }
}