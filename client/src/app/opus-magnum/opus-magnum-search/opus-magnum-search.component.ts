/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntitySearchComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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
import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {MatDialogModule} from "@angular/material/dialog";

export interface OpusMagnumSearchCriteria {
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.searchCriteriaAttributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]
            [ searchValue="string" replaceByExpression="attribute.typescriptAttributeTypeWithoutNullability" ]
        @slac
    }}}@  */
    firstname?: string;
    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    nickname?: string;
    lastname?: string;
    id?: string;
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}

@Component({
    selector: 'app-opus-magnum-search',
    templateUrl: './opus-magnum-search.component.html',
    styleUrls: ['./opus-magnum-search.component.scss'],
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
export class OpusMagnumSearchComponent {
    @Output() search = new EventEmitter<OpusMagnumSearchCriteria>();

    searchForm: FormGroup;

    constructor(private fb: FormBuilder) {
        this.searchForm = this.fb.group({
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.searchCriteriaAttributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]
                    [ searchValue="''" replaceByExpression="attribute.typescriptAttributeInitialValue" ]
                @slac
            }}}@  */
            firstname: [''],
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            nickname: [''],
            lastname: [''],
            id: [''],
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        });
    }

    onSubmit(): void {
        if (this.searchForm.valid) {
            const criteria: OpusMagnumSearchCriteria = {};
            Object.keys(this.searchForm.controls).forEach(key => {
                const value = this.searchForm.get(key)?.value;
                if (value !== null && value !== '') {
                    criteria[key as keyof OpusMagnumSearchCriteria] = value;
                }
            });
            this.search.emit(criteria);
        }
    }

    onReset(): void {
        this.searchForm.reset();
        this.search.emit({});
    }
} 
