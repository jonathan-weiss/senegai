/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemBoardComponentTypescript" templateRendererPackageName="senegai.codegen.renderer.angular" ]

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
import {AuthorSearchComponent, AuthorSearchCriteria} from '@app/author/author-search/author-search.component';
import {AuthorResultComponent} from '@app/author/author-result/author-result.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {AuthorConfirmDeleteDialogComponent} from '@app/author/author-confirm-delete-dialog/author-confirm-delete-dialog.component';
import {AuthorService} from '@app/author/author.service';
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {AuthorFormComponent} from "@app/author/author-form/author-form/author-form.component";
import {Author} from "@app/author/author.model";

@Component({
    selector: 'app-author-board',
    templateUrl: './author-board.component.html',
    styleUrls: ['./author-board.component.scss'],
    imports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatToolbarModule,
        MatTableModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatExpansionModule,
        MatSidenavModule,
        MatListModule,
        MatDialogModule,
        AuthorSearchComponent,
        AuthorResultComponent,
        AuthorFormComponent,
    ]
})
export class AuthorBoardComponent {
    currentSearchCriteria: AuthorSearchCriteria = {};
    selectedAuthor: Author | null = null;
    refreshKey = 0;

    constructor(private dialog: MatDialog, private authorService: AuthorService) {
    }

    onSearch(criteria: AuthorSearchCriteria): void {
        this.currentSearchCriteria = criteria;
    }

    onAuthorSelect(author: Author): void {
        this.selectedAuthor = author;
    }

    onDeleteAuthor(author: Author): void {
        const dialogRef = this.dialog.open(AuthorConfirmDeleteDialogComponent, {
            data: {firstname: author.firstname, lastname: author.lastname}
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.authorService.deleteAuthor(author.id).subscribe(() => {
                    this.refreshKey++;
                });
            }
        });
    }

    onSave(updatedAuthor: Author): void {
        this.authorService.updateAuthor(updatedAuthor).subscribe(() => {
            this.selectedAuthor = null;
            this.refreshKey++;
        });
    }

    onCancel(): void {
        this.selectedAuthor = null;
    }
} 
