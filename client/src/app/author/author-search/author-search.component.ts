/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemSearchComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="ItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="ItemModel"
        modelPackageName="senegai.codegen.renderer.model"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.itemName" ]
        [ searchValue="author" replaceByExpression="model.itemNameLowercase" ]

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

export interface AuthorSearchCriteria {
    id?: number;
    /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

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
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}

@Component({
    selector: 'app-author-search',
    templateUrl: './author-search.component.html',
    styleUrls: ['./author-search.component.scss'],
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
export class AuthorSearchComponent {
    @Output() search = new EventEmitter<AuthorSearchCriteria>();

    searchForm: FormGroup;

    constructor(private fb: FormBuilder) {
        this.searchForm = this.fb.group({
            id: [''],
            /* @tt{{{ @slbc
                @foreach [ iteratorExpression="model.attributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]
                    [ searchValue="''" replaceByExpression="attribute.typescriptAttributeInitialValue" ]
                @slac
            }}}@  */
            firstname: [''],
            /* @tt{{{ @slbc @end-foreach @slac }}}@ */
            /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
            nickname: [''],
            lastname: ['']
            /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
        });
    }

    onSubmit(): void {
        if (this.searchForm.valid) {
            const criteria: AuthorSearchCriteria = {};
            Object.keys(this.searchForm.controls).forEach(key => {
                const value = this.searchForm.get(key)?.value;
                if (value !== null && value !== '') {
                    criteria[key as keyof AuthorSearchCriteria] = value;
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
