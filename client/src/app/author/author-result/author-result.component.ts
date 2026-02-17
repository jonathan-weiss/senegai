/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityResultComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.entityName" ]
        [ searchValue="author" replaceByExpression="model.entityNameLowercase" ]

    @modify-provided-filename-by-replacements

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
import {AuthorWTO} from "@app/wto/author.wto";

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
    @Output() selectAuthor = new EventEmitter<AuthorWTO>();
    @Output() deleteAuthor = new EventEmitter<AuthorWTO>();

    displayedColumns: string[] = [
        /* @tt{{{
            @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

        }}}@  */
        'firstname',
    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        'nickname',
        'lastname',
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        'actions'
    ];
    dataSource: MatTableDataSource<AuthorWTO> = new MatTableDataSource<AuthorWTO>();
    private allAuthors: AuthorWTO[] = [];

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

    onEdit(author: AuthorWTO): void {
        this.selectAuthor.emit(author);
    }

    onDelete(author: AuthorWTO): void {
        this.deleteAuthor.emit(author);
    }

    private filterAuthors(): void {
        const criteria = this.searchCriteria;
        this.dataSource.data = this.allAuthors.filter(author => {
            return (
                /* @tt{{{ @slbc
                    @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                    @replace-value-by-expression
                        [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]
                        [ searchValue="String" replaceByExpression="attribute.typescriptAttributeTypeCapitalizedWithoutNullability" ]
                    @slac
                }}}@  */
                this.isMatchingStringCriteria(criteria.firstname, author.firstname) &&
                    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
                    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
                this.isMatchingStringCriteria(criteria.nickname, author.nickname) &&
                this.isMatchingStringCriteria(criteria.lastname, author.lastname) &&
                this.isMatchingStringCriteria(criteria.id, author.id) &&
                    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
                    true
            );
        });
    }

    private isMatchingStringCriteria(searchCriteriaText: string | undefined | null, dataText: string | undefined | null): boolean {
        if(searchCriteriaText == undefined) {
            return true;
        }
        if(dataText == undefined) {
            return false;
        }
        return dataText.toLowerCase().trim().includes(searchCriteriaText.toLowerCase().trim())
    }

    private isMatchingNumberCriteria(searchCriteriaNumber: number | undefined | null, dataNumber: number | undefined | null): boolean {
        if(searchCriteriaNumber == undefined) {
            return true;
        }
        if(dataNumber == undefined) {
            return false;
        }
        return searchCriteriaNumber === dataNumber;
    }

} 
