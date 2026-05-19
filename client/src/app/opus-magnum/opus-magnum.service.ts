/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityServiceRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityName.kebabCase" ]
        [ searchValue="OPUS_MAGNUM" replaceByExpression="model.entityName.screamingSnakeCase" ]

    @replace-value-by-value
        [ searchValue="IndexUnicus" replaceByValue="Id" ]
        [ searchValue="indexUnicus" replaceByValue="id" ]


    @modify-provided-filename-by-replacements

    @remove-blanks-and-linebreak-after-comment

}}}@ */

import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
import {OPUS_MAGNUM_EXAMPLE_DATA} from "@app/opus-magnum/opus-magnum-example-data";


@Injectable({providedIn: 'root'})
export class OpusMagnumService {
    private opusMagnumList: OpusMagnumWTO[] = []

    constructor() {
        this.opusMagnumList = OPUS_MAGNUM_EXAMPLE_DATA
    }

    getOpusMagnums(): Observable<OpusMagnumWTO[]> {
        // Simulate HTTP delay
        return of(this.opusMagnumList).pipe(delay(200));
    }

    getOpusMagnumById(indexUnicus: string): Observable<OpusMagnumWTO | null> {
        const found = this.opusMagnumList.find(a => a.indexUnicus === indexUnicus) || null;
        return of(found).pipe(delay(200));
    }

    deleteOpusMagnum(indexUnicus: string): Observable<void> {
        this.opusMagnumList = this.opusMagnumList.filter(a => a.indexUnicus !== indexUnicus);
        return of(void 0).pipe(delay(200));
    }

    updateOpusMagnum(opusMagnum: OpusMagnumWTO): Observable<OpusMagnumWTO> {
        const idx = this.opusMagnumList.findIndex(a => a.indexUnicus === opusMagnum.indexUnicus);
        if (idx !== -1) {
            this.opusMagnumList[idx] = {...opusMagnum};
        }
        return of(opusMagnum).pipe(delay(200));
    }
} 
