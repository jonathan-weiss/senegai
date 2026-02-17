/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityFormComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

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
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {AuthorWTO} from "@app/wto/author.wto";
import {AuthorFormPartService} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part.service";
import {AuthorFormPartComponent} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part.component";

@Component({
    selector: 'app-author-form',
    templateUrl: './opus-magnum-form.component.html',
    styleUrls: ['./opus-magnum-form.component.scss'],
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
        AuthorFormPartComponent,
    ]
})
export class OpusMagnumFormComponent implements OnInit {
    @Input() author: AuthorWTO | null = null;
    @Output() save = new EventEmitter<AuthorWTO>();
    @Output() cancel = new EventEmitter<void>();

    authorForm: FormGroup;

    constructor(private authorFormPartService: AuthorFormPartService) {
        this.authorForm = authorFormPartService.createInitialAuthorForm();
    }

    ngOnInit(): void {
        if (this.author) {
            this.authorFormPartService.patchAuthorForm(this.authorForm, this.author)
        }
    }

    onSubmit(): void {
        if (this.authorForm.valid) {
            const updatedAuthor: AuthorWTO = this.authorFormPartService.createAuthorFromFormData(this.authorForm)
            this.save.emit(updatedAuthor);
        }
    }

    onCancel(): void {
        this.cancel.emit();
    }
}
