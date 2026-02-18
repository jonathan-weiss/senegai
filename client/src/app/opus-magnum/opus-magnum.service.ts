/* @tt{{{
    @slbc

    @template-renderer [ templateRendererClassName="EntityServiceRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
    modelClassName="UiEntityModel"
    modelPackageName="senegai.codegen.renderer.model.ui"
    modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityNameDashCase" ]
        [ searchValue="OPUS_MAGNUM" replaceByExpression="model.entityNameUppercase" ]

    @modify-provided-filename-by-replacements

    @slac

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

    getOpusMagnumById(id: string): Observable<OpusMagnumWTO | null> {
        const found = this.opusMagnumList.find(a => a.id === id) || null;
        return of(found).pipe(delay(200));
    }

    deleteOpusMagnum(id: string): Observable<void> {
        this.opusMagnumList = this.opusMagnumList.filter(a => a.id !== id);
        return of(void 0).pipe(delay(200));
    }

    updateOpusMagnum(opusMagnum: OpusMagnumWTO): Observable<OpusMagnumWTO> {
        const idx = this.opusMagnumList.findIndex(a => a.id === opusMagnum.id);
        if (idx !== -1) {
            this.opusMagnumList[idx] = {...opusMagnum};
        }
        return of(opusMagnum).pipe(delay(200));
    }
} 
