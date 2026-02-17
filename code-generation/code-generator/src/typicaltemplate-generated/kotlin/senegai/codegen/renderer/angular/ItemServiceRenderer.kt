/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template ItemServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemServiceRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {Observable, of} from 'rxjs';
          |import {delay} from 'rxjs/operators';
          |import {${model.entityName}} from "@app/${model.entityNameLowercase}/${model.entityNameLowercase}.model";
          |@Injectable({providedIn: 'root'})
          |export class ${model.entityName}Service {
          |    private ${model.entityNameLowercase}s: ${model.entityName}[] = [
          |        {
          |            ${ model.attributes.joinToString("") { attribute ->  """
              |            ${attribute.attributeName}: ${attribute.typescriptAttributeTypeExample},
          """ } }        },    ];
          |
          |    get${model.entityName}s(): Observable<${model.entityName}[]> {
          |        // Simulate HTTP delay
          |        return of(this.${model.entityNameLowercase}s).pipe(delay(200));
          |    }
          |
          |    get${model.entityName}ById(id: string): Observable<${model.entityName} | null> {
          |        const found = this.${model.entityNameLowercase}s.find(a => a.id === id) || null;
          |        return of(found).pipe(delay(200));
          |    }
          |
          |    delete${model.entityName}(id: string): Observable<void> {
          |        this.${model.entityNameLowercase}s = this.${model.entityNameLowercase}s.filter(a => a.id !== id);
          |        return of(void 0).pipe(delay(200));
          |    }
          |
          |    update${model.entityName}(${model.entityNameLowercase}: ${model.entityName}): Observable<${model.entityName}> {
          |        const idx = this.${model.entityNameLowercase}s.findIndex(a => a.id === ${model.entityNameLowercase}.id);
          |        if (idx !== -1) {
          |            this.${model.entityNameLowercase}s[idx] = {...${model.entityNameLowercase}};
          |        }
          |        return of(${model.entityNameLowercase}).pipe(delay(200));
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityNameLowercase}/${model.entityNameLowercase}.service.ts"
    }
}