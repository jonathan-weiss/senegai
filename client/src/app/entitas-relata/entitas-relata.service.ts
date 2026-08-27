import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MembrumRelatumWTO} from "@app/wto/membrum-relatum.wto";
import {UUID} from "@app/shared/uuid";


@Injectable({providedIn: 'root'})
export class EntitasRelataService {
    private readonly baseUrl = 'http://localhost:8081/api/entitas-relata';

    constructor(private readonly http: HttpClient) {}

    getMembrumRelatumList(): Observable<MembrumRelatumWTO[]> {
        return this.http.get<MembrumRelatumWTO[]>(this.baseUrl);
    }

    getMembrumRelatumById(clavisPrimaria: UUID): Observable<MembrumRelatumWTO | null> {
        return this.http.get<MembrumRelatumWTO | null>(`${this.baseUrl}/${clavisPrimaria}`);
    }

    deleteMembrumRelatum(clavisPrimaria: UUID): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${clavisPrimaria}`);
    }

    updateMembrumRelatum(membrumRelatum: MembrumRelatumWTO): Observable<MembrumRelatumWTO> {
        return this.http.put<MembrumRelatumWTO>(`${this.baseUrl}/${membrumRelatum.clavisPrimaria}`, membrumRelatum);
    }

    createMembrumRelatum(entitasRelata: MembrumRelatumWTO): Observable<MembrumRelatumWTO> {
        return this.http.post<MembrumRelatumWTO>(this.baseUrl, entitasRelata);
    }
}
