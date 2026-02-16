/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemServiceRenderer filled up
 * with the content of the passed models.
 */
object ItemServiceRenderer : ItemRenderer {

    override fun renderTemplate(model: ItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {Observable, of} from 'rxjs';
          |import {delay} from 'rxjs/operators';
          |import {${model.itemName}} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}.model";
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}Service {
          |    private ${model.itemNameLowercase}s: ${model.itemName}[] = [
          |        {
          |            ${ model.attributes.joinToString("") { attribute ->  """
              |            ${attribute.attributeName}: ${attribute.typescriptAttributeTypeExample},
          """ } }        },    ];
          |
          |    get${model.itemName}s(): Observable<${model.itemName}[]> {
          |        // Simulate HTTP delay
          |        return of(this.${model.itemNameLowercase}s).pipe(delay(200));
          |    }
          |
          |    get${model.itemName}ById(id: string): Observable<${model.itemName} | null> {
          |        const found = this.${model.itemNameLowercase}s.find(a => a.id === id) || null;
          |        return of(found).pipe(delay(200));
          |    }
          |
          |    delete${model.itemName}(id: string): Observable<void> {
          |        this.${model.itemNameLowercase}s = this.${model.itemNameLowercase}s.filter(a => a.id !== id);
          |        return of(void 0).pipe(delay(200));
          |    }
          |
          |    update${model.itemName}(${model.itemNameLowercase}: ${model.itemName}): Observable<${model.itemName}> {
          |        const idx = this.${model.itemNameLowercase}s.findIndex(a => a.id === ${model.itemNameLowercase}.id);
          |        if (idx !== -1) {
          |            this.${model.itemNameLowercase}s[idx] = {...${model.itemNameLowercase}};
          |        }
          |        return of(${model.itemNameLowercase}).pipe(delay(200));
          |    }
          |} 
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: ItemModel): String {
      return "${model.itemNameLowercase}/${model.itemNameLowercase}.service.ts"
    }
}