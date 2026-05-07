/* @tt{{{

    #expand-comment [ expandDirection="backward" strip="linebreak"]

    #move-comment [ direction="backward" ]
    @template-renderer [
        templateRendererClassName="EntityItemFormPartComponentTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityItemRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="author" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    #expand-comment [ expandDirection="forward" strip="linebreak"]

}}}@ */
import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
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
import {FormUtil} from "@app/shared/form-controls/form.util";
import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
import {
    AuthorFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-validation.service";
import {AuthorFormPartFieldName,} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-field-name";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {AuthorFormPartGroup} from "@app/opus-magnum/opus-magnum-form/author-form-part/author-form-part-group";
/*
@tt{{{
    @if [ conditionExpression="model.containsNamedSectionSplitBlocks()" ]
    #expand-comment [ expandDirection="forward" strip="linebreak"]
}}}@
 */
import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
/*
@tt{{{
    @end-if #expand-comment [ expandDirection="forward" strip="linebreak"]
}}}@
 */
/*
@tt{{{
    @if [ conditionExpression="model.containsTextBlocks()" ]
    #expand-comment [ expandDirection="forward" strip="linebreak"]
}}}@
 */
import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
/*
@tt{{{
    @end-if #expand-comment [ expandDirection="forward" strip="linebreak"]
}}}@
 */
/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";
import {GenderSelectorComponent} from "@app/enum/gender-input-selection/gender-selector.component";

/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */


/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]
    @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
    @replace-value-by-expression
        [ searchValue="library-award" replaceByExpression="nestedItem.itemName.kebabCase" ]
        [ searchValue="libraryAward" replaceByExpression="nestedItem.itemName.camelCase" ]
        [ searchValue="LibraryAward" replaceByExpression="nestedItem.itemName.pascalCase" ]

}}}@  */
import {
    LibraryAwardTableComponent
} from "@app/opus-magnum/opus-magnum-form/library-award-table/library-award-table.component";
import {
    LibraryAwardFormPartComponent
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part.component";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";
/* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */

@Component({
    selector: 'app-author-form-part',
    templateUrl: './author-form-part.component.html',
    styleUrls: ['./author-form-part.component.scss'],
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
        FieldWrapperComponent,
        TextInputComponent,
        MatTabGroup,
        MatTab,
        /*
        @tt{{{
            @if [ conditionExpression="model.containsNamedSectionSplitBlocks()" ]
            #expand-comment [ expandDirection="forward" strip="linebreak"]
        }}}@
         */
        SectionSplitterComponent,
        /*
        @tt{{{
            @end-if #expand-comment [ expandDirection="forward" strip="linebreak"]
        }}}@
         */
        /*
        @tt{{{
            @if [ conditionExpression="model.containsTextBlocks()" ]
            #expand-comment [ expandDirection="forward" strip="linebreak"]
        }}}@
         */
        TextBlockComponent,
        /*
        @tt{{{
            @end-if #expand-comment [ expandDirection="forward" strip="linebreak"]
        }}}@
         */
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="library-award" replaceByExpression="nestedItem.itemName.kebabCase" ]
                [ searchValue="libraryAward" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="LibraryAward" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */

        LibraryAwardTableComponent,
        LibraryAwardFormPartComponent,
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text }}}@ */
        DatepickerInputComponent,
        BooleanInputComponent,
        GenderSelectorComponent,
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text }}}@ */
    ]
})
export class AuthorFormPartComponent implements OnInit {
    @Input({ required: true }) authorForm!: FormGroup<AuthorFormPartGroup>;

    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]
        @foreach [ iteratorExpression="model.item.attributesWithItems" loopVariable="attribute" ]
        @replace-value-by-expression
            [ searchValue="libraryAwardList" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="FormGroup<LibraryAwardFormPartGroup>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

    }}}@  */
    libraryAwardListFormGroupUnderEdit: FormGroup<LibraryAwardFormPartGroup> | undefined = undefined;
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */


    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    protected idControl!: FormControl<string>
    protected firstnameControl!: FormControl<string>
    protected firstnameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    /* @tt{{{
    @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

    @replace-value-by-expression
        [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="FormControl<string | null>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

    #expand-comment [ expandDirection="forward" strip="linebreak"]
    }}}@  */
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @if [ conditionExpression="attribute.isNullable"] #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    protected nicknameIsNotNullControl!: FormControl<boolean>
    protected nicknameIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-if #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    protected nicknameControl!: FormControl<string | null>
    protected nicknameValidatorNames!: ReadonlyArray<ValidatorTranslation>

    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"] @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */

    protected lastnameControl!: FormControl<string>
    protected lastnameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected libraryAwardListControl!: FormArray<FormGroup<LibraryAwardFormPartGroup>>
    protected libraryAwardListValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayIsNotNullControl!: FormControl<boolean>
    protected birthdayIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayControl!: FormControl<Date | null>
    protected birthdayValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected vegetarianControl!: FormControl<boolean>
    protected vegetarianValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected genderControl!: FormControl<GenderEnum>
    protected genderValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */

    constructor(private readonly authorFormValidationService: AuthorFormPartValidationService,) {
    }

    ngOnInit() {
        /* @tt{{{ @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
        this.idControl = this.authorForm.controls[AuthorFormPartFieldName.id]
        this.firstnameControl = this.authorForm.controls[AuthorFormPartFieldName.firstname]
        this.firstnameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.firstname)
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text }}}@ */
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="nickname" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @if [ conditionExpression="attribute.isNullable"] #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
        this.nicknameIsNotNullControl = this.authorForm.controls[AuthorFormPartFieldName.nicknameIsNotNull]
        this.nicknameIsNotNullValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.nicknameIsNotNull)
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-if #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
        this.nicknameControl = this.authorForm.controls[AuthorFormPartFieldName.nickname]
        this.nicknameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.nickname)

        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"] @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
        this.lastnameControl = this.authorForm.controls[AuthorFormPartFieldName.lastname]
        this.lastnameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.lastname)
        this.libraryAwardListControl = this.authorForm.controls[AuthorFormPartFieldName.libraryAwardList]
        this.libraryAwardListValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.libraryAwardList)
        this.birthdayIsNotNullControl = this.authorForm.controls[AuthorFormPartFieldName.birthdayIsNotNull]
        this.birthdayIsNotNullValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.birthdayIsNotNull)
        this.birthdayControl = this.authorForm.controls[AuthorFormPartFieldName.birthday]
        this.birthdayValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.birthday)
        this.vegetarianControl = this.authorForm.controls[AuthorFormPartFieldName.vegetarian]
        this.vegetarianValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.vegetarian)
        this.genderControl = this.authorForm.controls[AuthorFormPartFieldName.gender]
        this.genderValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.gender)
        /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
    }


    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]
    @foreach [ iteratorExpression="model.item.attributesWithItems" loopVariable="attribute" ]
    @replace-value-by-expression
        [ searchValue="libraryAwardList" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="LibraryAwardList" replaceByExpression="attribute.attributeName.pascalCase" ]
        [ searchValue="FormGroup<LibraryAwardFormPartGroup>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

    }}}@  */
    onLibraryAwardListFormGroupEdit(formGroup: FormGroup<LibraryAwardFormPartGroup>): void {
        this.libraryAwardListFormGroupUnderEdit = formGroup;
    }

    onLibraryAwardListFormGroupDelete(formGroup: FormGroup<LibraryAwardFormPartGroup>): void {
        if(this.libraryAwardListFormGroupUnderEdit == formGroup) {
            this.libraryAwardListFormGroupUnderEdit = undefined
        }
        FormUtil.removeControl(this.libraryAwardListControl, formGroup)
    }

    closeLibraryAwardListFormGroupUnderEdit(): void {
        this.libraryAwardListFormGroupUnderEdit = undefined;
    }

    /* @tt{{{ #expand-comment [ expandDirection="backward" strip="linebreak"]  @end-foreach #expand-comment [ expandDirection="forward" strip="linebreak"] }}}@ */
}
