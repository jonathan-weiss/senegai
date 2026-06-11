/* @tt{{{

    @rlb

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityResultComponentTypescriptRenderer"
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

    @modify-provided-filename-by-replacements

    @rla

}}}@ */
import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {OpusMagnumSearchCriteria} from '@app/opus-magnum/opus-magnum-search/opus-magnum-search.component';
import {OpusMagnumService} from '@app/opus-magnum/opus-magnum.service';
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
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";

@Component({
    selector: 'app-opus-magnum-result',
    templateUrl: './opus-magnum-result.component.html',
    styleUrls: ['./opus-magnum-result.component.scss'],
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
export class OpusMagnumResultComponent implements OnChanges {
    @Input() searchCriteria: OpusMagnumSearchCriteria = {};
    @Input() refreshKey: number = 0;
    @Output() selectOpusMagnum = new EventEmitter<OpusMagnumWTO>();
    @Output() deleteOpusMagnum = new EventEmitter<OpusMagnumWTO>();
    @Output() createOpusMagnum = new EventEmitter<void>();

    displayedColumns: string[] = [
        /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
        'indexUnicus',
        /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.searchResultAttributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="title" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        'title',
    /* @tt{{{ @rlb @end-foreach @rla }}}@ */
        'actions'
    ];
    dataSource: MatTableDataSource<OpusMagnumWTO> = new MatTableDataSource<OpusMagnumWTO>();
    private allOpusMagnums: OpusMagnumWTO[] = [];

    constructor(private opusMagnumService: OpusMagnumService) {
        this.loadOpusMagnums();
    }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['refreshKey'] && !changes['refreshKey'].firstChange) {
            this.loadOpusMagnums();
        } else if (changes['searchCriteria'] && this.allOpusMagnums.length) {
            this.filterOpusMagnums();
        }
    }

    private loadOpusMagnums(): void {
        this.opusMagnumService.getOpusMagnums().subscribe(opusMagnumList => {
            this.allOpusMagnums = opusMagnumList;
            this.filterOpusMagnums();
        });
    }

    onCreate(): void {
        this.createOpusMagnum.emit();
    }

    onEdit(opusMagnum: OpusMagnumWTO): void {
        this.selectOpusMagnum.emit(opusMagnum);
    }

    onDelete(opusMagnum: OpusMagnumWTO): void {
        this.deleteOpusMagnum.emit(opusMagnum);
    }

    private filterOpusMagnums(): void {
        this.dataSource.data = this.filteredOpusMagnumList(this.searchCriteria, this.allOpusMagnums);
    }

    private filteredOpusMagnumList(searchCriteria: OpusMagnumSearchCriteria, allOpusMagnum: OpusMagnumWTO[]): OpusMagnumWTO[] {
        const searchTokens = searchCriteria?.searchQuery?.split(" ") ?? [];
        if(searchTokens.length < 1) {
            return allOpusMagnum
        } else {
            return allOpusMagnum.filter(opusMagnum => {
                return searchTokens.some(searchToken => this.isMatchingCriteria(searchToken, opusMagnum))
            });
        }
    }

    private isMatchingCriteria(searchCriteriaText: string | undefined | null, opusMagnum: OpusMagnumWTO): boolean {
        if(searchCriteriaText == undefined) {
            return true;
        }
        // a rather simple implementation to search, but good enough for the moment...
        return JSON.stringify(opusMagnum).includes(searchCriteriaText);
    }
}
