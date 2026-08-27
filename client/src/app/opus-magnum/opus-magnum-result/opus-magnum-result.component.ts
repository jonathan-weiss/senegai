/* @tt{{{

    

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
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.entityRootItem.itemName.kebabCase" ]

    @modify-provided-filepath-by-replacements

    

}}}@ */
import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
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
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
import {SilvaOptionumSearchCriteriaWTO} from "@app/wto/silva-optionum-search-criteria.wto";

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
export class OpusMagnumResultComponent implements OnInit, OnChanges {
    @Input() searchCriteria: OpusMagnumSearchCriteria = {};
    @Input() refreshKey: number = 0;
    @Output() selectOpusMagnum = new EventEmitter<SilvaOptionumWTO>();
    @Output() deleteOpusMagnum = new EventEmitter<SilvaOptionumWTO>();
    @Output() createOpusMagnum = new EventEmitter<void>();

    displayedColumns: string[] = [
        /* @tt{{{   @ignore-text  }}}@ */
        'indexUnicus',
        /* @tt{{{   @end-ignore-text  }}}@ */
        /* @tt{{{
            @foreach [ iteratorExpression="model.searchResultAttributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="campusTextusObligatorius" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        'campusTextusObligatorius',
    /* @tt{{{  @end-foreach  }}}@ */
        'actions'
    ];
    dataSource: MatTableDataSource<SilvaOptionumWTO> = new MatTableDataSource<SilvaOptionumWTO>();

    constructor(private opusMagnumService: OpusMagnumService) {
    }

    ngOnInit(): void {
        this.loadOpusMagnums();
    }

    ngOnChanges(changes: SimpleChanges): void {
        const refreshed = changes['refreshKey'] && !changes['refreshKey'].firstChange;
        const searchCriteriaChanged = changes['searchCriteria'] && !changes['searchCriteria'].firstChange;
        if (refreshed || searchCriteriaChanged) {
            this.loadOpusMagnums();
        }
    }

    private loadOpusMagnums(): void {
        const searchCriteria: SilvaOptionumSearchCriteriaWTO = {
            query: this.searchCriteria?.searchQuery ?? '',
        };
        this.opusMagnumService.searchSilvaOptionumList(searchCriteria)
            .subscribe(searchResult => {
                this.dataSource.data = searchResult.silvaOptionumList;
            });
    }

    onCreate(): void {
        this.createOpusMagnum.emit();
    }

    onEdit(opusMagnum: SilvaOptionumWTO): void {
        this.selectOpusMagnum.emit(opusMagnum);
    }

    onDelete(opusMagnum: SilvaOptionumWTO): void {
        this.deleteOpusMagnum.emit(opusMagnum);
    }
}
