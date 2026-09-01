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
import {Observable} from 'rxjs';
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
import {SilvaOptionumSearchCriteriaWTO} from "@app/wto/silva-optionum-search-criteria.wto";
import {SilvaOptionumSearchResultWTO} from "@app/wto/silva-optionum-search-result.wto";
import {SilvaOptionumByIdsCriteriaWTO} from "@app/wto/silva-optionum-by-ids-criteria.wto";
import {SilvaOptionumByIdsResultWTO} from "@app/wto/silva-optionum-by-ids-result.wto";
import {UUID} from "@app/shared/uuid";


@Injectable({providedIn: 'root'})
export class OpusMagnumService {
    private readonly baseUrl = 'http://localhost:8081/api/opus-magnum';

    constructor(private readonly http: HttpClient) {}

    getSilvaOptionumList(): Observable<SilvaOptionumWTO[]> {
        return this.http.get<SilvaOptionumWTO[]>(this.baseUrl);
    }

    searchSilvaOptionumList(searchCriteria: SilvaOptionumSearchCriteriaWTO): Observable<SilvaOptionumSearchResultWTO> {
        return this.http.post<SilvaOptionumSearchResultWTO>(`${this.baseUrl}/search`, searchCriteria);
    }

    /**
     * Resolves a whole set of references to this entity in one call, so that a list of stored
     * identifiers can be shown by the display attributes instead of the bare UUIDs.
     */
    getSilvaOptionumListByIds(criteria: SilvaOptionumByIdsCriteriaWTO): Observable<SilvaOptionumByIdsResultWTO> {
        return this.http.post<SilvaOptionumByIdsResultWTO>(`${this.baseUrl}/by-ids`, criteria);
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
