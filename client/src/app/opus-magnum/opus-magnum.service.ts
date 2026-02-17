/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
    modelClassName="UiEntityModel"
    modelPackageName="senegai.codegen.renderer.model.ui"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.entityName" ]
        [ searchValue="author" replaceByExpression="model.entityNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */

import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {AuthorWTO} from "@app/wto/author.wto";
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

@Injectable({providedIn: 'root'})
export class OpusMagnumService {
    private authors: AuthorWTO[] = [
        {
            /* @tt{{{
                @foreach [ iteratorExpression="model.entityRootItem.attributes" loopVariable="attribute" ]

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
            id: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        },
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        {
            id: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
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
            id: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
            firstname: 'Robert',
            nickname: null,
            lastname: 'Johnson',
            libraryAwardList: [],
            birthday: new Date(1963, 11, 31),
            vegetarian: true,
            gender: GenderEnum.MALE,
        },
        {
            id: 'af18a7cc-7e7a-4388-bb32-95652fc1e379',
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

    getAuthors(): Observable<AuthorWTO[]> {
        // Simulate HTTP delay
        return of(this.authors).pipe(delay(200));
    }

    getAuthorById(id: string): Observable<AuthorWTO | null> {
        const found = this.authors.find(a => a.id === id) || null;
        return of(found).pipe(delay(200));
    }

    deleteAuthor(id: string): Observable<void> {
        this.authors = this.authors.filter(a => a.id !== id);
        return of(void 0).pipe(delay(200));
    }

    updateAuthor(author: AuthorWTO): Observable<AuthorWTO> {
        const idx = this.authors.findIndex(a => a.id === author.id);
        if (idx !== -1) {
            this.authors[idx] = {...author};
        }
        return of(author).pipe(delay(200));
    }
} 
