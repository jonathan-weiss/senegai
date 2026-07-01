/* @tt{{{
    

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


    @modify-provided-filepath-by-replacements

    

}}}@ */

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
import {SILVA_OTIONUM_EXAMPLE_DATA} from "@app/opus-magnum/opus-magnum-example-data";


@Injectable({providedIn: 'root'})
export class OpusMagnumService {
    /**
     * Data source switch: when `true` the service serves the in-memory
     * {@link SILVA_OTIONUM_EXAMPLE_DATA} constant; when `false` it calls the Spring Boot
     * REST API at {@link baseUrl}. Flip to `false` to consume the real backend.
     */
    private readonly useExampleData = true;
    private readonly baseUrl = 'http://localhost:8080/api/opus-magnum';

    private silvaOptionumList: SilvaOptionumWTO[] = []

    constructor(private readonly http: HttpClient) {
        this.silvaOptionumList = SILVA_OTIONUM_EXAMPLE_DATA
    }

    getSilvaOptionumList(): Observable<SilvaOptionumWTO[]> {
        if (!this.useExampleData) {
            return this.http.get<SilvaOptionumWTO[]>(this.baseUrl);
        }
        // Simulate HTTP delay
        return of(this.silvaOptionumList).pipe(delay(200));
    }

    getSilvaOptionumById(indexUnicus: string): Observable<SilvaOptionumWTO | null> {
        if (!this.useExampleData) {
            return this.http.get<SilvaOptionumWTO | null>(`${this.baseUrl}/${indexUnicus}`);
        }
        const found = this.silvaOptionumList.find(a => a.indexUnicus === indexUnicus) || null;
        return of(found).pipe(delay(200));
    }

    deleteSilvaOptionum(indexUnicus: string): Observable<void> {
        if (!this.useExampleData) {
            return this.http.delete<void>(`${this.baseUrl}/${indexUnicus}`);
        }
        this.silvaOptionumList = this.silvaOptionumList.filter(a => a.indexUnicus !== indexUnicus);
        return of(void 0).pipe(delay(200));
    }

    updateSilvaOptionum(silvaOptionum: SilvaOptionumWTO): Observable<SilvaOptionumWTO> {
        if (!this.useExampleData) {
            return this.http.put<SilvaOptionumWTO>(`${this.baseUrl}/${silvaOptionum.indexUnicus}`, silvaOptionum);
        }
        const idx = this.silvaOptionumList.findIndex(a => a.indexUnicus === silvaOptionum.indexUnicus);
        if (idx !== -1) {
            this.silvaOptionumList[idx] = {...silvaOptionum};
        }
        return of(silvaOptionum).pipe(delay(200));
    }

    createSilvaOptionum(opusMagnum: SilvaOptionumWTO): Observable<SilvaOptionumWTO> {
        if (!this.useExampleData) {
            return this.http.post<SilvaOptionumWTO>(this.baseUrl, opusMagnum);
        }
        const created: SilvaOptionumWTO = {...opusMagnum, indexUnicus: crypto.randomUUID()};
        this.silvaOptionumList = [...this.silvaOptionumList, created];
        return of(created).pipe(delay(200));
    }
}
