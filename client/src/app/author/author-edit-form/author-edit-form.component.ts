/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="ItemEditFormComponentTypescript" templateRendererPackageName="senegai.codegen.renderer.angular" ]

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
import {Author} from "@app/author/author.model";
import {AuthorEditFormService} from "@app/author/author-edit-form/author-edit-form.service";
import {AuthorFormPartComponent} from "@app/author/author-form-part/author-form-part.component";

@Component({
    selector: 'app-author-edit-form',
    templateUrl: './author-edit-form.component.html',
    styleUrls: ['./author-edit-form.component.scss'],
    standalone: true,
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
    ],
})
export class AuthorEditFormComponent implements OnInit {
    @Input() author: Author | null = null;
    @Output() save = new EventEmitter<Author>();
    @Output() cancel = new EventEmitter<void>();

    authorForm: FormGroup;

    constructor(private authorEditFormService: AuthorEditFormService) {
        this.authorForm = authorEditFormService.createEmptyForm();
    }

    ngOnInit(): void {
        if (this.author) {
            this.authorEditFormService.patchForm(this.authorForm, this.author)
        }
    }

    onSubmit(): void {
        if (this.authorForm.valid) {
            const updatedAuthor: Author = this.authorEditFormService.createAuthorFromFormData(this.authorForm)
            this.save.emit(updatedAuthor);
        }
    }

    onCancel(): void {
        this.cancel.emit();
    }
}
