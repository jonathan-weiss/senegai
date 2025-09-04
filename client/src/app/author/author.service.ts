/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemService" templateRendererPackageName="senegai.codegen.renderer.angular" ]

    @template-model [
    modelClassName="ItemModel"
    modelPackageName="senegai.codegen.renderer.model"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {Author} from "@app/author/author.model";

@Injectable({providedIn: 'root'})
export class AuthorService {
    private authors: Author[] = [
        {id: 1, firstname: 'John', nickname: 'Johnny', lastname: 'Doe', libraryAwardList: [{description: "The award", year: 1956}, {description: "Publizer prize", year: 1989}]},
        {id: 2, firstname: 'Jane', nickname: 'Janey', lastname: 'Smith', libraryAwardList: [{description: "The award", year: 1956}, {description: "Publizer prize 2", year: 2023}]},
        {id: 3, firstname: 'Robert', nickname: null, lastname: 'Johnson', libraryAwardList: [{description: "The award", year: 1956}, {description: "Publizer prize", year: 1989}]},
        {id: 4, firstname: 'Mary', nickname: 'Molly', lastname: 'Williams', libraryAwardList: [{description: "The award", year: 1956}, {description: "Publizer prize", year: 2003}]},
    ];

    getAuthors(): Observable<Author[]> {
        // Simulate HTTP delay
        return of(this.authors).pipe(delay(200));
    }

    getAuthorById(id: number): Observable<Author | null> {
        const found = this.authors.find(a => a.id === id) || null;
        return of(found).pipe(delay(200));
    }

    deleteAuthor(id: number): Observable<void> {
        this.authors = this.authors.filter(a => a.id !== id);
        return of(void 0).pipe(delay(200));
    }

    updateAuthor(author: Author): Observable<Author> {
        const idx = this.authors.findIndex(a => a.id === author.id);
        if (idx !== -1) {
            this.authors[idx] = {...author};
        }
        return of(author).pipe(delay(200));
    }
} 
