/* @tt{{{

    @remove-blanks-and-linebreak-before-comment

    @move-comment-backward
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
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]

    @modify-provided-filename-by-replacements

    @remove-blanks-and-linebreak-after-comment

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
    SilvaOptionumFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-validation.service";
import {SilvaOptionumFormPartFieldName,} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-field-name";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {SilvaOptionumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";
/*
@tt{{{
    @if [ conditionExpression="model.containsNamedSectionSplitBlocks()" ]
    @remove-blanks-and-linebreak-after-comment
}}}@
 */
import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
/*
@tt{{{
    @end-if @remove-blanks-and-linebreak-after-comment
}}}@
 */
/*
@tt{{{
    @if [ conditionExpression="model.containsTextBlocks()" ]
    @remove-blanks-and-linebreak-after-comment
}}}@
 */
import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
/*
@tt{{{
    @end-if @remove-blanks-and-linebreak-after-comment
}}}@
 */
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";
import {AppellatioSelectorComponent} from "@app/enum/appellatio-input-selection/appellatio-selector.component";

/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */


/* @tt{{{ @remove-blanks-and-linebreak-before-comment
    @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
    @replace-value-by-expression
        [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]
        [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
        [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

}}}@  */
import {
    ArticulusInteriorTableComponent
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-table/articulus-interior-table.component";
import {
    ArticulusInteriorFormPartComponent
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part.component";
import {
    ArticulusInteriorFormPartGroup
} from "@app/opus-magnum/opus-magnum-form/articulus-interior-form-part/articulus-interior-form-part-group";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */

@Component({
    selector: 'app-silva-optionum-form-part',
    templateUrl: './silva-optionum-form-part.component.html',
    styleUrls: ['./silva-optionum-form-part.component.scss'],
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
            @remove-blanks-and-linebreak-after-comment
        }}}@
         */
        SectionSplitterComponent,
        /*
        @tt{{{
            @end-if @remove-blanks-and-linebreak-after-comment
        }}}@
         */
        /*
        @tt{{{
            @if [ conditionExpression="model.containsTextBlocks()" ]
            @remove-blanks-and-linebreak-after-comment
        }}}@
         */
        TextBlockComponent,
        /*
        @tt{{{
            @end-if @remove-blanks-and-linebreak-after-comment
        }}}@
         */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]
                [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */

        ArticulusInteriorTableComponent,
        ArticulusInteriorFormPartComponent,
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text }}}@ */
        DatepickerInputComponent,
        BooleanInputComponent,
        AppellatioSelectorComponent,
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text }}}@ */
    ]
})
export class SilvaOptionumFormPartComponent implements OnInit {
    @Input({ required: true }) silvaOptionumForm!: FormGroup<SilvaOptionumFormPartGroup>;

    /* @tt{{{ @remove-blanks-and-linebreak-before-comment
        @foreach [ iteratorExpression="model.item.attributesWithItems" loopVariable="attribute" ]
        @replace-value-by-expression
            [ searchValue="articulusInteriorList" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="FormGroup<ArticulusInteriorFormPartGroup>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

    }}}@  */
    articulusInteriorListFormGroupUnderEdit: FormGroup<ArticulusInteriorFormPartGroup> | undefined = undefined;
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */


    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    protected idControl!: FormControl<string>
    protected campusTextusObligatoriusControl!: FormControl<string>
    protected campusTextusObligatoriusValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    /* @tt{{{
    @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

    @replace-value-by-expression
        [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="FormControl<string | null>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

    @remove-blanks-and-linebreak-after-comment
    }}}@  */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
    protected campusTextusOptionalisIsNotNullControl!: FormControl<boolean>
    protected campusTextusOptionalisIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
    protected campusTextusOptionalisControl!: FormControl<string | null>
    protected campusTextusOptionalisValidatorNames!: ReadonlyArray<ValidatorTranslation>

    /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

    protected lastnameControl!: FormControl<string>
    protected lastnameValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected articulusInteriorListControl!: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>
    protected articulusInteriorListValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected campusDieiIsNotNullControl!: FormControl<boolean>
    protected campusDieiIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected campusDieiControl!: FormControl<Date | null>
    protected campusDieiValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected vegetarianControl!: FormControl<boolean>
    protected vegetarianValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected appellatioControl!: FormControl<AppellatioEnum>
    protected appellatioValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

    constructor(private readonly silvaOptionumFormValidationService: SilvaOptionumFormPartValidationService,) {
    }

    ngOnInit() {
        /* @tt{{{ @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        this.idControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.id]
        this.campusTextusObligatoriusControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius]
        this.campusTextusObligatoriusValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusObligatorius)
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text }}}@ */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @if [ conditionExpression="attribute.isNullable"] @remove-blanks-and-linebreak-after-comment }}}@ */
        this.campusTextusOptionalisIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]
        this.campusTextusOptionalisIsNotNullValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull)
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-if @remove-blanks-and-linebreak-after-comment }}}@ */
        this.campusTextusOptionalisControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis]
        this.campusTextusOptionalisValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusOptionalis)

        /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        this.lastnameControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.lastname]
        this.lastnameValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.lastname)
        this.articulusInteriorListControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorList]
        this.articulusInteriorListValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorList)
        this.campusDieiIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull]
        this.campusDieiIsNotNullValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusDieiIsNotNull)
        this.campusDieiControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusDiei]
        this.campusDieiValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusDiei)
        this.vegetarianControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.vegetarian]
        this.vegetarianValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.vegetarian)
        this.appellatioControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.appellatio]
        this.appellatioValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.appellatio)
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    }


    /* @tt{{{ @remove-blanks-and-linebreak-before-comment
    @foreach [ iteratorExpression="model.item.attributesWithItems" loopVariable="attribute" ]
    @replace-value-by-expression
        [ searchValue="articulusInteriorList" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="ArticulusInteriorList" replaceByExpression="attribute.attributeName.pascalCase" ]
        [ searchValue="FormGroup<ArticulusInteriorFormPartGroup>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

    }}}@  */
    onArticulusInteriorListFormGroupEdit(formGroup: FormGroup<ArticulusInteriorFormPartGroup>): void {
        this.articulusInteriorListFormGroupUnderEdit = formGroup;
    }

    onArticulusInteriorListFormGroupDelete(formGroup: FormGroup<ArticulusInteriorFormPartGroup>): void {
        if(this.articulusInteriorListFormGroupUnderEdit == formGroup) {
            this.articulusInteriorListFormGroupUnderEdit = undefined
        }
        FormUtil.removeControl(this.articulusInteriorListControl, formGroup)
    }

    closeArticulusInteriorListFormGroupUnderEdit(): void {
        this.articulusInteriorListFormGroupUnderEdit = undefined;
    }

    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
}
