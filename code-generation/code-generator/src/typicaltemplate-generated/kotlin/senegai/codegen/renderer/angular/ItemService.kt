/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ItemModel

/**
 * Generate the content for the template ItemService filled up
 * with the content of the passed models.
 */
object ItemService {

    fun renderTemplate(model: ItemModel): String {
        return """
          |
          |import {Injectable} from '@angular/core';
          |import {Observable, of} from 'rxjs';
          |import {delay} from 'rxjs/operators';
          |import {${model.itemName}} from "@app/${model.itemNameLowercase}/${model.itemNameLowercase}.model";
          |
          |@Injectable({providedIn: 'root'})
          |export class ${model.itemName}Service {
          |    private ${model.itemNameLowercase}s: ${model.itemName}[] = [
          |        {id: 1, firstname: 'John', nickname: 'Johnny', lastname: 'Doe'},
          |        {id: 2, firstname: 'Jane', nickname: 'Janey', lastname: 'Smith'},
          |        {id: 3, firstname: 'Robert', nickname: 'Bob', lastname: 'Johnson'},
          |        {id: 4, firstname: 'Mary', nickname: 'Molly', lastname: 'Williams'}
          |    ];
          |
          |    get${model.itemName}s(): Observable<${model.itemName}[]> {
          |        // Simulate HTTP delay
          |        return of(this.${model.itemNameLowercase}s).pipe(delay(200));
          |    }
          |
          |    delete${model.itemName}(id: number): Observable<void> {
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
}