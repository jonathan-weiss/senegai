/* @tt{{{

    @remove-blanks-and-linebreak-before-comment

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntitySearchComponentTypescriptRenderer"
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

    @remove-blanks-and-linebreak-after-comment

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
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment
        @foreach [ iteratorExpression="model.searchCriteriaAttributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="title" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="string" replaceByExpression="attribute.typescriptAttributeTypeWithoutNullability" ]
        @remove-blanks-and-linebreak-after-comment
    }}}@  */
    title?: string;
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    id?: string;
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
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
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment
                @foreach [ iteratorExpression="model.searchCriteriaAttributes" loopVariable="attribute" ]

                @replace-value-by-expression
                    [ searchValue="title" replaceByExpression="attribute.attributeName.camelCase" ]
                    [ searchValue="''" replaceByExpression="attribute.typescriptAttributeInitialValue" ]
                @remove-blanks-and-linebreak-after-comment
            }}}@  */
            title: [''],
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
            id: [''],
            /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
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
