/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemEditFormComponentTypescript" templateRendererPackageName="senegai.codegen.renderer.angular" ]

    @template-model [
        modelClassName="ItemModel"
        modelPackageName="senegai.codegen.renderer.model"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

    @slac

}}}@ */
import {Component} from '@angular/core';
import {Author} from "@app/author/author.model";
import {AuthorService} from "@app/author/author.service";
import {AuthorEditFormComponent} from "@app/author/author-edit-form/author-edit-form.component";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: 'app-author-routable-edit',
    templateUrl: './author-routable-edit.component.html',
    styleUrls: ['./author-routable-edit.component.scss'],
    standalone: true,
    imports: [
        AuthorEditFormComponent,
    ],
})
export class AuthorRoutableEditComponent {
    selectedAuthor: Author | null = null;

    constructor(
        private authorService: AuthorService,
        private route: ActivatedRoute,
    ) {
        this.route.params.subscribe(params => {
            const idParam = params['id'];
            if (idParam) {
                const id = Number(idParam);
                this.authorService.getAuthorById(id).subscribe(author => {
                    this.selectedAuthor = author;
                });
            }
        });
    }
}
