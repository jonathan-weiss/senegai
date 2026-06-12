/* @tt{{{
    @rlb

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
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.entityRootItem.itemName.kebabCase" ]
        [ searchValue="SILVA_OTIONUM" replaceByExpression="model.entityRootItem.itemName.screamingSnakeCase" ]

    @replace-value-by-expression
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]


    @modify-provided-filename-by-replacements

    @rla

}}}@ */

import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {SilvaOptionumWTO} from "@app/wto/silva-optionum-wto";
import {SILVA_OTIONUM_EXAMPLE_DATA} from "@app/opus-magnum/opus-magnum-example-data";


@Injectable({providedIn: 'root'})
export class OpusMagnumService {
    private silvaOptionumList: SilvaOptionumWTO[] = []

    constructor() {
        this.silvaOptionumList = SILVA_OTIONUM_EXAMPLE_DATA
    }

    getSilvaOptionumList(): Observable<SilvaOptionumWTO[]> {
        // Simulate HTTP delay
        return of(this.silvaOptionumList).pipe(delay(200));
    }

    getSilvaOptionumById(indexUnicus: string): Observable<SilvaOptionumWTO | null> {
        const found = this.silvaOptionumList.find(a => a.indexUnicus === indexUnicus) || null;
        return of(found).pipe(delay(200));
    }

    deleteSilvaOptionum(indexUnicus: string): Observable<void> {
        this.silvaOptionumList = this.silvaOptionumList.filter(a => a.indexUnicus !== indexUnicus);
        return of(void 0).pipe(delay(200));
    }

    updateSilvaOptionum(silvaOptionum: SilvaOptionumWTO): Observable<SilvaOptionumWTO> {
        const idx = this.silvaOptionumList.findIndex(a => a.indexUnicus === silvaOptionum.indexUnicus);
        if (idx !== -1) {
            this.silvaOptionumList[idx] = {...silvaOptionum};
        }
        return of(silvaOptionum).pipe(delay(200));
    }

    createSilvaOptionum(opusMagnum: SilvaOptionumWTO): Observable<SilvaOptionumWTO> {
        const created: SilvaOptionumWTO = {...opusMagnum, indexUnicus: crypto.randomUUID()};
        this.silvaOptionumList = [...this.silvaOptionumList, created];
        return of(created).pipe(delay(200));
    }
}
