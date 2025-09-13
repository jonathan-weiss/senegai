/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="ItemServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
    modelClassName="ItemModel"
    modelPackageName="senegai.codegen.renderer.model"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {Author} from "@app/author/author.model";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/author/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

@Injectable({providedIn: 'root'})
export class AuthorService {
    private authors: Author[] = [
        {
            id: 1,
            /* @tt{{{
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]
                    [ searchValue="'John'" replaceByExpression="attribute.typescriptAttributeTypeExample" ]

            }}}@  */
            firstname: 'John',
            /* @tt{{{
                @slbc
                @end-foreach
                @slac
            }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            nickname: 'Johnny',
            lastname: 'Doe',
            libraryAwardList: [
                {
                    description: "ALA Medal of Excellence.",
                    year: 1956,
                    juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
                },
                {
                    description: "Joseph W. Lippincott Award",
                    year: 1989,
                    juryList: ["Peter Booker"]
                }
            ],
            birthday: new Date(1979, 3, 23),
            vegetarian: false,
            gender: GenderEnum.MALE,
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        },
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        {
            id: 2,
            firstname: 'Jane',
            nickname: 'Janey',
            lastname: 'Smith',
            libraryAwardList: [
                {
                    description: "James Madison Award ",
                    year: 1956,
                    juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
                },
                {
                    description: "John Sessions Memorial Award",
                    year: 1998,
                    juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
                }
            ],
            birthday: null,
            vegetarian: false,
            gender: GenderEnum.FEMALE,
        },
        {
            id: 3,
            firstname: 'Robert',
            nickname: null,
            lastname: 'Johnson',
            libraryAwardList: [],
            birthday: new Date(1963, 11, 31),
            vegetarian: true,
            gender: GenderEnum.MALE,
        },
        {
            id: 4,
            firstname: 'Mary',
            nickname: 'Molly',
            lastname: 'Williams',
            libraryAwardList: [
                {
                    description: "Jean E. Coleman Library Outreach Lecture",
                    year: 1956,
                    juryList: ["W.Y. Boyd", "Beta Phi", "Joseph Lippincott"]
                },
                {
                    description: "John Sessions Memorial Award",
                    year: 1998,
                    juryList: ["Justin Windsor", "Edward Swandson", "May Hill"]
                }
            ],
            birthday: new Date(1954, 8, 3),
            vegetarian: false,
            gender: GenderEnum.FEMALE,
        },
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
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
