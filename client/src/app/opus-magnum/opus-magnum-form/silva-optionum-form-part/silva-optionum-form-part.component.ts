/* @tt{{{

    @rlb

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
      [ searchValue="OpusMagnum" replaceByExpression="model.entity.entityName.pascalCase" ]
      [ searchValue="opusMagnum" replaceByExpression="model.entity.entityName.camelCase" ]
      [ searchValue="opus-magnum" replaceByExpression="model.entity.entityName.kebabCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.item.itemName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

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
/* @tt{{{ @rlb  @if [ conditionExpression="model.item.containsTextAttributes"]  @rla }}}@ */
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
/* @tt{{{ @rlb  @end-if @rla }}}@ */
/* @tt{{{ @rlb  @if [ conditionExpression="model.item.containsBooleanAttributes"] }}}@ */
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";
/* @tt{{{ @rlb  @end-if @rla }}}@ */
/* @tt{{{ @rlb  @if [ conditionExpression="model.item.containsNumberAttributes"] }}}@ */
import {NumberInputComponent} from "@app/shared/form-controls/number-input/number-input.component";
/* @tt{{{ @rlb  @end-if }}}@ */
import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {SilvaOptionumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";
/*
@tt{{{
    @if [ conditionExpression="model.containsNamedSectionSplitBlocks()" ]
    @rla
}}}@
 */
import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
/*
@tt{{{
    @end-if @rla
}}}@
 */
/*
@tt{{{
    @if [ conditionExpression="model.containsTextBlocks()" ]
    @rla
}}}@
 */
import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
/*
@tt{{{
    @end-if @rla
}}}@
 */
/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
import {AppellatioSelectorComponent} from "@app/enum/appellatio-input-selection/appellatio-selector.component";

/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */


/* @tt{{{ @rlb
    @foreach [ iteratorExpression="model.item.directlyNestedItems" loopVariable="nestedItem" ]
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
/* @tt{{{ @rlb  @end-foreach @rla }}}@ */

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
        /*
        @tt{{{
            @if [ conditionExpression="model.tabs.isNotEmpty()" ]
            @rla
        }}}@
         */
        MatTabGroup,
        MatTab,
        /*
        @tt{{{
            @end-if @rla
        }}}@
         */
        /*
        @tt{{{
            @if [ conditionExpression="model.containsNamedSectionSplitBlocks()" ]
            @rla
        }}}@
         */
        SectionSplitterComponent,
        /*
        @tt{{{
            @end-if @rla
        }}}@
         */
        /*
        @tt{{{
            @if [ conditionExpression="model.containsTextBlocks()" ]
            @rla
        }}}@
         */
        TextBlockComponent,
        /*
        @tt{{{
            @end-if @rla
        }}}@
         */
        /* @tt{{{ @rlb
            @foreach [ iteratorExpression="model.item.attributesWithItem" loopVariable="attributeWithItem" ]
            @replace-value-by-expression
                [ searchValue="articulus-interior" replaceByExpression="attributeWithItem.type.item.itemName.kebabCase" ]
                [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

        }}}@  */

        /* @tt{{{ @rlb  @if [ conditionExpression="attributeWithItem.attribute.isList"] }}}@ */
        ArticulusInteriorTableComponent,
        /* @tt{{{ @rlb  @end-if }}}@ */
        ArticulusInteriorFormPartComponent,
        /* @tt{{{ @rlb  @end-foreach @rla }}}@ */
        /* @tt{{{ @rlb  @if [ conditionExpression="model.item.containsTextAttributes"] }}}@ */
        TextInputComponent,
        /* @tt{{{ @rlb  @end-if }}}@ */
        /* @tt{{{ @rlb  @if [ conditionExpression="model.item.containsBooleanAttributes"] }}}@ */
        BooleanInputComponent,
        /* @tt{{{ @rlb  @end-if }}}@ */
        /* @tt{{{ @rlb  @if [ conditionExpression="model.item.containsNumberAttributes"] }}}@ */
        NumberInputComponent,
        /* @tt{{{ @rlb  @end-if }}}@ */
        /* @tt{{{ @rlb  @ignore-text }}}@ */
        DatepickerInputComponent,
        AppellatioSelectorComponent,
        /* @tt{{{ @rlb  @end-ignore-text }}}@ */
    ]
})
export class SilvaOptionumFormPartComponent implements OnInit {
    @Input({ required: true }) silvaOptionumForm!: FormGroup<SilvaOptionumFormPartGroup>;

    /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributesWithItem" loopVariable="attributeWithItem" ]
        @replace-value-by-expression
            [ searchValue="articulusInteriorList" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
            [ searchValue="FormGroup<ArticulusInteriorFormPartGroup>" replaceByExpression="attributeWithItem.attribute.angularFormControlType" ]

    }}}@  */
    articulusInteriorListFormGroupUnderEdit: FormGroup<ArticulusInteriorFormPartGroup> | undefined = undefined;
    /* @tt{{{ @rlb  @end-foreach @rla }}}@ */


    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    protected campusTextusObligatoriusControl!: FormControl<string>
    protected campusTextusObligatoriusValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
    /* @tt{{{
    @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

    @replace-value-by-expression
        [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="FormControl<string | null>" replaceByExpression="attribute.angularFormControlTypeWithCollection" ]

    @rla
    }}}@  */
    /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
    protected campusTextusOptionalisIsNotNullControl!: FormControl<boolean>
    protected campusTextusOptionalisIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @rlb  @end-if @rla }}}@ */
    protected campusTextusOptionalisControl!: FormControl<string | null>
    protected campusTextusOptionalisValidatorNames!: ReadonlyArray<ValidatorTranslation>

    /* @tt{{{ @rlb @end-foreach @rla }}}@ */
    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */

    protected articulusInteriorListControl!: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>
    protected articulusInteriorListValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected campusDieiIsNotNullControl!: FormControl<boolean>
    protected campusDieiIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected campusDieiControl!: FormControl<Date | null>
    protected campusDieiValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected campusBivalensControl!: FormControl<boolean>
    protected campusBivalensValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected appellatioControl!: FormControl<AppellatioEnum>
    protected appellatioValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected articulusInteriorSingularisControl!: FormGroup<ArticulusInteriorFormPartGroup>
    protected articulusInteriorSingularisValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected articulusInteriorSingularisOptionalisControl!: FormGroup<ArticulusInteriorFormPartGroup>
    protected articulusInteriorSingularisOptionalisValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected articulusInteriorSingularisOptionalisIsNotNullControl!: FormControl<boolean>
    protected articulusInteriorSingularisOptionalisIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected indexUnicusControl!: FormControl<string>
    protected indexUnicusValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected campusNumerorumControl!: FormControl<number>
    protected campusNumerorumValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

    constructor(private readonly silvaOptionumMagnumFormValidationService: SilvaOptionumFormPartValidationService,) {
    }

    ngOnInit() {
        /* @tt{{{ @ignore-text @rla }}}@ */
        this.campusTextusObligatoriusControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius]
        this.campusTextusObligatoriusValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusObligatorius)
        /* @tt{{{ @rlb  @end-ignore-text }}}@ */
        /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
        this.campusTextusOptionalisIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]
        this.campusTextusOptionalisIsNotNullValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull)
        /* @tt{{{ @rlb  @end-if @rla }}}@ */
        this.campusTextusOptionalisControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis]
        this.campusTextusOptionalisValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusOptionalis)

        /* @tt{{{ @rlb @end-foreach @rla }}}@ */
        /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
        this.articulusInteriorListControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorList]
        this.articulusInteriorListValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorList)
        this.campusDieiIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull]
        this.campusDieiIsNotNullValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusDieiIsNotNull)
        this.campusDieiControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusDiei]
        this.campusDieiValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusDiei)
        this.campusBivalensControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusBivalens]
        this.campusBivalensValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusBivalens)
        this.appellatioControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.appellatio]
        this.appellatioValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.appellatio)
        this.articulusInteriorSingularisControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis]
        this.articulusInteriorSingularisValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorSingularis)
        this.indexUnicusControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.indexUnicus]
        this.indexUnicusValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.indexUnicus)
        this.campusNumerorumControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusNumerorum]
        this.campusNumerorumValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusNumerorum)
        this.articulusInteriorSingularisOptionalisControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis]
        this.articulusInteriorSingularisOptionalisValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis)
        this.articulusInteriorSingularisOptionalisIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull]
        this.articulusInteriorSingularisOptionalisIsNotNullValidatorNames = this.silvaOptionumMagnumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull)
        /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
    }


    /* @tt{{{ @rlb
    @foreach [ iteratorExpression="model.item.attributesWithLists" loopVariable="attribute" ]
    @replace-value-by-expression
        [ searchValue="articulusInteriorList" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="ArticulusInteriorList" replaceByExpression="attribute.attributeName.pascalCase" ]
        [ searchValue="FormGroup<ArticulusInteriorFormPartGroup>" replaceByExpression="attribute.angularFormControlType" ]

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

    /* @tt{{{ @rlb  @end-foreach @rla }}}@ */
}
