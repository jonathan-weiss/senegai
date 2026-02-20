/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityItemFormPartComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityItemRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityFormViewItemModel"
        modelPackageName="senegai.codegen.renderer.model.ui.entityform"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Author" replaceByExpression="model.item.itemName" ]
        [ searchValue="author" replaceByExpression="model.item.itemNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityNameDashCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityNameLowercase" ]

    @modify-provided-filename-by-replacements

    @slac

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
    @slac
}}}@
 */
import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
/*
@tt{{{
    @end-if @slac
}}}@
 */
/*
@tt{{{
    @if [ conditionExpression="model.containsTextBlocks()" ]
    @slac
}}}@
 */
import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
/*
@tt{{{
    @end-if @slac
}}}@
 */
/* @tt{{{ @slbc  @ignore-text @slac }}}@ */
import {
    LibraryAwardTableComponent
} from "@app/opus-magnum/opus-magnum-form/library-award-table/library-award-table.component";
import {
    LibraryAwardFormPartComponent
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part.component";
import {GenderEnum} from "@app/wto/gender.enum";
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";
import {
    LibraryAwardFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/library-award-form-part/library-award-form-part-group";
import {GenderSelectorComponent} from "@app/enum/gender-input-selection/gender-selector.component";

/* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

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
            @slac
        }}}@
         */
        SectionSplitterComponent,
        /*
        @tt{{{
            @end-if @slac
        }}}@
         */
        /*
        @tt{{{
            @if [ conditionExpression="model.containsTextBlocks()" ]
            @slac
        }}}@
         */
        TextBlockComponent,
        /*
        @tt{{{
            @end-if @slac
        }}}@
         */

        /* @tt{{{ @slbc  @ignore-text }}}@ */
        DatepickerInputComponent,
        LibraryAwardTableComponent,
        LibraryAwardFormPartComponent,
        BooleanInputComponent,
        GenderSelectorComponent,
        /* @tt{{{ @slbc  @end-ignore-text }}}@ */
    ]
})
export class AuthorFormPartComponent implements OnInit {
    @Input({ required: true }) authorForm!: FormGroup<AuthorFormPartGroup>;

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
    authorLibraryAwardUnderEdit: FormGroup<LibraryAwardFormPartGroup> | undefined = undefined;

    protected idControl!: FormControl<number>
    protected firstnameControl!: FormControl<string>
    protected firstnameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    /* @tt{{{
    @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

    @replace-value-by-expression
        [ searchValue="nickname" replaceByExpression="attribute.attributeName" ]

    @slac
    }}}@  */
    protected nicknameIsNotNullControl!: FormControl<boolean>
    protected nicknameIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected nicknameControl!: FormControl<string | null>
    protected nicknameValidatorNames!: ReadonlyArray<ValidatorTranslation>

    /* @tt{{{ @slbc @end-foreach @slac }}}@ */
    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */

    protected lastnameControl!: FormControl<string>
    protected lastnameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected libraryAwardListFormArray!: FormArray<FormGroup<LibraryAwardFormPartGroup>>
    protected libraryAwardListValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayIsNotNullControl!: FormControl<boolean>
    protected birthdayIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected birthdayControl!: FormControl<Date | null>
    protected birthdayValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected vegetarianControl!: FormControl<boolean>
    protected vegetarianValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected genderControl!: FormControl<GenderEnum>
    protected genderValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */

    constructor(private readonly authorFormValidationService: AuthorFormPartValidationService,) {
    }

    ngOnInit() {
        /* @tt{{{ @ignore-text @slac }}}@ */
        this.idControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.id);
        /* @tt{{{ @slbc  @end-ignore-text }}}@ */
        /* @tt{{{ @slbc
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="firstname" replaceByExpression="attribute.attributeName" ]

        }}}@  */
        this.firstnameControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.firstname)
        this.firstnameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.firstname)

        /* @tt{{{ @slbc @end-foreach @slac }}}@ */
        /* @tt{{{ @slbc  @ignore-text @slac }}}@ */
        this.nicknameIsNotNullControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.nicknameIsNotNull)
        this.nicknameIsNotNullValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.nicknameIsNotNull)
        this.nicknameControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.nickname)
        this.nicknameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.nickname)
        this.lastnameControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.lastname)
        this.lastnameValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.lastname)
        this.libraryAwardListFormArray = FormUtil.requiredFormArray(this.authorForm, AuthorFormPartFieldName.libraryAwardList)
        this.libraryAwardListValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.libraryAwardList)
        this.birthdayIsNotNullControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.birthdayIsNotNull)
        this.birthdayIsNotNullValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.birthdayIsNotNull)
        this.birthdayControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.birthday)
        this.birthdayValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.birthday)
        this.vegetarianControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.vegetarian)
        this.vegetarianValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.vegetarian)
        this.genderControl = FormUtil.requiredFormControl(this.authorForm, AuthorFormPartFieldName.gender)
        this.genderValidatorNames = this.authorFormValidationService.validatorNames(AuthorFormPartFieldName.gender)
        /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
    }

    /* @tt{{{ @slbc  @ignore-text @slac }}}@ */

    onAuthorLibraryAwardFormGroupEdit(formGroup: FormGroup<LibraryAwardFormPartGroup>): void {
        this.authorLibraryAwardUnderEdit = formGroup;
    }

    onAuthorLibraryAwardFormGroupDelete(formGroup: FormGroup<LibraryAwardFormPartGroup>): void {
        if(this.authorLibraryAwardUnderEdit == formGroup) {
            this.authorLibraryAwardUnderEdit = undefined
        }
        FormUtil.removeControl(this.libraryAwardListFormArray, formGroup)
    }

    closeAuthorLibraryAwardUnderEdit(): void {
        this.authorLibraryAwardUnderEdit = undefined;
    }
    /* @tt{{{ @slbc  @end-ignore-text @slac }}}@ */
}
