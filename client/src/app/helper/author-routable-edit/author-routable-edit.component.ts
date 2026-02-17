import {Component} from '@angular/core';
import {AuthorWTO} from "@app/wto/author.wto";
import {OpusMagnumService} from "@app/opus-magnum/opus-magnum.service";
import {OpusMagnumFormComponent} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: 'app-author-routable-edit',
    templateUrl: './author-routable-edit.component.html',
    styleUrls: ['./author-routable-edit.component.scss'],
    imports: [
        OpusMagnumFormComponent,
    ]
})
export class AuthorRoutableEditComponent {
    selectedAuthor: AuthorWTO | null = null;

    constructor(
        private authorService: OpusMagnumService,
        private route: ActivatedRoute,
    ) {
        this.route.params.subscribe(params => {
            const idParam = params['id'];
            if (idParam) {
                const id = idParam as string;
                this.authorService.getAuthorById(id).subscribe(author => {
                    this.selectedAuthor = author;
                });
            }
        });
    }
}
