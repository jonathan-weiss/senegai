import {Component} from '@angular/core';
import {AuthorWTO} from "@app/wto/author.wto";
import {AuthorService} from "@app/author/author.service";
import {AuthorFormComponent} from "@app/author/author-form/author-form/author-form.component";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: 'app-author-routable-edit',
    templateUrl: './author-routable-edit.component.html',
    styleUrls: ['./author-routable-edit.component.scss'],
    imports: [
        AuthorFormComponent,
    ]
})
export class AuthorRoutableEditComponent {
    selectedAuthor: AuthorWTO | null = null;

    constructor(
        private authorService: AuthorService,
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
