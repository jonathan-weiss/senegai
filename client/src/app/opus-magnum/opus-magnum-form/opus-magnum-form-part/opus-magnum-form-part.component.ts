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
        [ searchValue="opus-magnum" replaceByExpression="model.item.itemName.kebabCase" ]
        [ searchValue="OpusMagnum" replaceByExpression="model.item.itemName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.item.itemName.camelCase" ]

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
    OpusMagnumFormPartValidationService
} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-validation.service";
import {OpusMagnumFormPartFieldName,} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-field-name";
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {OpusMagnumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form-part/opus-magnum-form-part-group";
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
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";
import {AppellatioSelectorComponent} from "@app/enum/appellatio-input-selection/appellatio-selector.component";

/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */


/* @tt{{{ @rlb
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
/* @tt{{{ @rlb  @end-foreach @rla }}}@ */

@Component({
    selector: 'app-opus-magnum-form-part',
    templateUrl: './opus-magnum-form-part.component.html',
    styleUrls: ['./opus-magnum-form-part.component.scss'],
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
            @foreach [ iteratorExpression="model.item.attributeItemsFlat" loopVariable="nestedItem" ]
            @replace-value-by-expression
                [ searchValue="articulus-interior" replaceByExpression="nestedItem.itemName.kebabCase" ]
                [ searchValue="articulusInterior" replaceByExpression="nestedItem.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="nestedItem.itemName.pascalCase" ]

        }}}@  */

        ArticulusInteriorTableComponent,
        ArticulusInteriorFormPartComponent,
        /* @tt{{{ @rlb  @end-foreach @rla }}}@ */
        /* @tt{{{ @rlb  @ignore-text }}}@ */
        DatepickerInputComponent,
        BooleanInputComponent,
        AppellatioSelectorComponent,
        /* @tt{{{ @rlb  @end-ignore-text }}}@ */
    ]
})
export class OpusMagnumFormPartComponent implements OnInit {
    @Input({ required: true }) opusMagnumForm!: FormGroup<OpusMagnumFormPartGroup>;

    /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributesWithItems" loopVariable="attribute" ]
        @replace-value-by-expression
            [ searchValue="articulusInteriorList" replaceByExpression="attribute.attributeName.camelCase" ]
            [ searchValue="FormGroup<ArticulusInteriorFormPartGroup>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

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
        [ searchValue="FormControl<string | null>" replaceByExpression="attribute.typescriptAttributeFormControlType" ]

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
    protected indexUnicusControl!: FormControl<string>
    protected indexUnicusValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

    constructor(private readonly opusMagnumFormValidationService: OpusMagnumFormPartValidationService,) {
    }

    ngOnInit() {
        /* @tt{{{ @ignore-text @rla }}}@ */
        this.campusTextusObligatoriusControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.campusTextusObligatorius]
        this.campusTextusObligatoriusValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.campusTextusObligatorius)
        /* @tt{{{ @rlb  @end-ignore-text }}}@ */
        /* @tt{{{ @rlb
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        /* @tt{{{ @rlb  @if [ conditionExpression="attribute.isNullable"] @rla }}}@ */
        this.campusTextusOptionalisIsNotNullControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.campusTextusOptionalisIsNotNull]
        this.campusTextusOptionalisIsNotNullValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.campusTextusOptionalisIsNotNull)
        /* @tt{{{ @rlb  @end-if @rla }}}@ */
        this.campusTextusOptionalisControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.campusTextusOptionalis]
        this.campusTextusOptionalisValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.campusTextusOptionalis)

        /* @tt{{{ @rlb @end-foreach @rla }}}@ */
        /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
        this.articulusInteriorListControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.articulusInteriorList]
        this.articulusInteriorListValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.articulusInteriorList)
        this.campusDieiIsNotNullControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.campusDieiIsNotNull]
        this.campusDieiIsNotNullValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.campusDieiIsNotNull)
        this.campusDieiControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.campusDiei]
        this.campusDieiValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.campusDiei)
        this.campusBivalensControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.campusBivalens]
        this.campusBivalensValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.campusBivalens)
        this.appellatioControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.appellatio]
        this.appellatioValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.appellatio)
        this.articulusInteriorSingularisControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.articulusInteriorSingularis]
        this.articulusInteriorSingularisValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.articulusInteriorSingularis)
        this.indexUnicusControl = this.opusMagnumForm.controls[OpusMagnumFormPartFieldName.indexUnicus]
        this.indexUnicusValidatorNames = this.opusMagnumFormValidationService.validatorNames(OpusMagnumFormPartFieldName.indexUnicus)
        /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
    }


    /* @tt{{{ @rlb
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

    /* @tt{{{ @rlb  @end-foreach @rla }}}@ */
}
