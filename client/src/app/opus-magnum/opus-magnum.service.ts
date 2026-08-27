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
import {UUID} from "@app/shared/uuid";


@Injectable({providedIn: 'root'})
export class OpusMagnumService {
    private readonly baseUrl = 'http://localhost:8081/api/opus-magnum';

    constructor(private readonly http: HttpClient) {}

    getSilvaOptionumList(): Observable<SilvaOptionumWTO[]> {
        return this.http.get<SilvaOptionumWTO[]>(this.baseUrl);
    }

    getSilvaOptionumById(indexUnicus: UUID): Observable<SilvaOptionumWTO | null> {
        return this.http.get<SilvaOptionumWTO | null>(`${this.baseUrl}/${indexUnicus}`);
    }

    deleteSilvaOptionum(indexUnicus: UUID): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${indexUnicus}`);
    }

    updateSilvaOptionum(silvaOptionum: SilvaOptionumWTO): Observable<SilvaOptionumWTO> {
        return this.http.put<SilvaOptionumWTO>(`${this.baseUrl}/${silvaOptionum.indexUnicus}`, silvaOptionum);
    }

    createSilvaOptionum(opusMagnum: SilvaOptionumWTO): Observable<SilvaOptionumWTO> {
        return this.http.post<SilvaOptionumWTO>(this.baseUrl, opusMagnum);
    }
}
