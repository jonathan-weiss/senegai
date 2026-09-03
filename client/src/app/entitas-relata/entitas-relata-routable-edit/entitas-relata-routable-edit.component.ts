import {Component} from '@angular/core';
import {MembrumRelatumWTO} from "@app/wto/membrum-relatum.wto";
import {MembrumRelatumService} from "@app/service/membrum-relatum.service";
import {EntitasRelataFormComponent} from "@app/entitas-relata/entitas-relata-form/entitas-relata-form/entitas-relata-form.component";
import {ActivatedRoute} from "@angular/router";
import {UUID} from "@app/shared/uuid";


@Component({
    selector: 'app-entitas-relata-routable-edit',
    templateUrl: './entitas-relata-routable-edit.component.html',
    styleUrls: ['./entitas-relata-routable-edit.component.scss'],
    imports: [
        EntitasRelataFormComponent,
    ]
})
export class EntitasRelataRoutableEditComponent {
    selectedEntitasRelata: MembrumRelatumWTO | null = null;

    constructor(
        private membrumRelatumService: MembrumRelatumService,
        private route: ActivatedRoute,
    ) {
        this.route.params.subscribe(params => {
            const idParam = params['clavisPrimaria'];
            if (idParam) {
                const clavisPrimaria = idParam as UUID;
                this.membrumRelatumService.getMembrumRelatumById(clavisPrimaria).subscribe(entitasRelata => {
                    this.selectedEntitasRelata = entitasRelata;
                });
            }
        });
    }
}
