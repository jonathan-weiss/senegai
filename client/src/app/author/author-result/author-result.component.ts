/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemResultComponentTypescript" templateRendererPackageName="senegai.codegen.renderer.angular" ]

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
import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {AuthorSearchCriteria} from '@app/author/author-search/author-search.component';
import {AuthorService} from '@app/author/author.service';
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatDialogModule} from "@angular/material/dialog";
import {Author} from "@app/author/author.model";

@Component({
    selector: 'app-author-result',
    templateUrl: './author-result.component.html',
    styleUrls: ['./author-result.component.scss'],
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
    ]
})
export class AuthorResultComponent implements OnChanges {
    @Input() searchCriteria: AuthorSearchCriteria = {};
    @Input() refreshKey: number = 0;
    @Output() selectAuthor = new EventEmitter<Author>();
    @Output() deleteAuthor = new EventEmitter<Author>();

    displayedColumns: string[] = ['id', 'firstname', 'nickname', 'lastname', 'actions'];
    dataSource: MatTableDataSource<Author> = new MatTableDataSource<Author>();
    private allAuthors: Author[] = [];

    constructor(private authorService: AuthorService) {
        this.loadAuthors();
    }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['refreshKey'] && !changes['refreshKey'].firstChange) {
            this.loadAuthors();
        } else if (changes['searchCriteria'] && this.allAuthors.length) {
            this.filterAuthors();
        }
    }

    private loadAuthors(): void {
        this.authorService.getAuthors().subscribe(authors => {
            this.allAuthors = authors;
            this.filterAuthors();
        });
    }

    onEdit(author: Author): void {
        this.selectAuthor.emit(author);
    }

    onDelete(author: Author): void {
        this.deleteAuthor.emit(author);
    }

    private filterAuthors(): void {
        const criteria = this.searchCriteria;
        this.dataSource.data = this.allAuthors.filter(author => {
            return (
                (!criteria.id || author.id === criteria.id) &&
                (!criteria.firstname || author.firstname.toLowerCase().includes(criteria.firstname.toLowerCase())) &&
                (!criteria.nickname || (author.nickname ?? "").toLowerCase().includes(criteria.nickname.toLowerCase())) &&
                (!criteria.lastname || author.lastname.toLowerCase().includes(criteria.lastname.toLowerCase()))
            );
        });
    }
} 
