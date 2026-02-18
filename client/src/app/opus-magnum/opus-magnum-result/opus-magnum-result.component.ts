/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityResultComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityNameDashCase" ]

    @modify-provided-filename-by-replacements

    @slac

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

    displayedColumns: string[] = [
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        'id',
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.searchResultAttributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="title" replaceByExpression="attribute.attributeName" ]

        }}}@  */
        'title',
    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
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

    onEdit(opusMagnum: OpusMagnumWTO): void {
        this.selectOpusMagnum.emit(opusMagnum);
    }

    onDelete(opusMagnum: OpusMagnumWTO): void {
        this.deleteOpusMagnum.emit(opusMagnum);
    }

    private filterOpusMagnums(): void {
        const criteria = this.searchCriteria;
        this.dataSource.data = this.allOpusMagnums.filter(opusMagnum => {
            return (
                /* @tt{{{ @slbc
                    @foreach [ iteratorExpression="model.searchResultAttributes" loopVariable="attribute" ]

                    @replace-value-by-expression
                        [ searchValue="title" replaceByExpression="attribute.attributeName" ]
                        [ searchValue="String" replaceByExpression="attribute.typescriptAttributeTypeCapitalizedWithoutNullability" ]
                    @slac
                }}}@  */
                this.isMatchingStringCriteria(criteria.title, opusMagnum.title) &&
                    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
                    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
                this.isMatchingStringCriteria(criteria.id, opusMagnum.id) &&
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
