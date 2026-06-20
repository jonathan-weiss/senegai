/* @tt{{{

    

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
/* @tt{{{   @if [ conditionExpression="model.item.containsTextAttributes"]   }}}@ */
import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
/* @tt{{{   @end-if  }}}@ */
/* @tt{{{   @if [ conditionExpression="model.item.containsBooleanAttributes"] }}}@ */
import {BooleanInputComponent} from "@app/shared/form-controls/boolean-input/boolean-input.component";
/* @tt{{{   @end-if  }}}@ */
/* @tt{{{   @if [ conditionExpression="model.item.containsNumberAttributes"] }}}@ */
import {NumberInputComponent} from "@app/shared/form-controls/number-input/number-input.component";
/* @tt{{{   @end-if }}}@ */
import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {SilvaOptionumFormPartGroup} from "@app/opus-magnum/opus-magnum-form/silva-optionum-form-part/silva-optionum-form-part-group";
/*
@tt{{{
    @if [ conditionExpression="model.containsNamedSectionSplitBlocks()" ]
    
}}}@
 */
import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
/*
@tt{{{
    @end-if 
}}}@
 */
/*
@tt{{{
    @if [ conditionExpression="model.containsTextBlocks()" ]
    
}}}@
 */
import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
/*
@tt{{{
    @end-if 
}}}@
 */
/* @tt{{{   @ignore-text  }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
import {AppellatioSelectorComponent} from "@app/enum/appellatio-input-selection/appellatio-selector.component";
import {
    SingleTextFormFieldTableComponent
} from "@app/shared/form-controls/single-text-form-field-table/single-text-form-field-table.component";

/* @tt{{{   @end-ignore-text  }}}@ */


/* @tt{{{ 
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
/* @tt{{{   @end-foreach  }}}@ */

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
            
        }}}@
         */
        MatTabGroup,
        MatTab,
        /*
        @tt{{{
            @end-if 
        }}}@
         */
        /*
        @tt{{{
            @if [ conditionExpression="model.containsNamedSectionSplitBlocks()" ]
            
        }}}@
         */
        SectionSplitterComponent,
        /*
        @tt{{{
            @end-if 
        }}}@
         */
        /*
        @tt{{{
            @if [ conditionExpression="model.containsTextBlocks()" ]
            
        }}}@
         */
        TextBlockComponent,
        /*
        @tt{{{
            @end-if 
        }}}@
         */
        /* @tt{{{ 
            @foreach [ iteratorExpression="model.item.attributesWithItem" loopVariable="attributeWithItem" ]
            @replace-value-by-expression
                [ searchValue="articulus-interior" replaceByExpression="attributeWithItem.type.item.itemName.kebabCase" ]
                [ searchValue="articulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.camelCase" ]
                [ searchValue="ArticulusInterior" replaceByExpression="attributeWithItem.type.item.itemName.pascalCase" ]

        }}}@  */

        /* @tt{{{   @if [ conditionExpression="attributeWithItem.attribute.isList"] }}}@ */
        ArticulusInteriorTableComponent,
        /* @tt{{{   @end-if }}}@ */
        ArticulusInteriorFormPartComponent,
        /* @tt{{{   @end-foreach  }}}@ */
        /* @tt{{{   @if [ conditionExpression="model.item.containsTextAttributes"] }}}@ */
        TextInputComponent,
        /* @tt{{{   @end-if }}}@ */
        /* @tt{{{   @if [ conditionExpression="model.item.containsBooleanAttributes"] }}}@ */
        BooleanInputComponent,
        /* @tt{{{   @end-if }}}@ */
        /* @tt{{{   @if [ conditionExpression="model.item.containsNumberAttributes"] }}}@ */
        NumberInputComponent,
        /* @tt{{{   @end-if }}}@ */
        /* @tt{{{   @ignore-text }}}@ */
        DatepickerInputComponent,
        AppellatioSelectorComponent,
        SingleTextFormFieldTableComponent,
        /* @tt{{{   @end-ignore-text }}}@ */
    ]
})
export class SilvaOptionumFormPartComponent implements OnInit {
    @Input({ required: true }) silvaOptionumForm!: FormGroup<SilvaOptionumFormPartGroup>;

    /* @tt{{{ 
        @foreach [ iteratorExpression="model.item.attributesWithItem.filter { it.attribute.isList }" loopVariable="attributeWithItem" ]
        @replace-value-by-expression
            [ searchValue="articulusInteriorIteratus" replaceByExpression="attributeWithItem.attribute.attributeName.camelCase" ]
            [ searchValue="FormGroup<ArticulusInteriorFormPartGroup>" replaceByExpression="attributeWithItem.attribute.angularFormControlType" ]

    }}}@  */
    articulusInteriorIteratusFormGroupUnderEdit: FormGroup<ArticulusInteriorFormPartGroup> | undefined = undefined;
    /* @tt{{{   @end-foreach  }}}@ */

    /* @tt{{{   @ignore-text  }}}@ */
    articulusInteriorOptionalisIteratusFormGroupUnderEdit: FormGroup<ArticulusInteriorFormPartGroup> | undefined = undefined;

    protected campusTextusObligatoriusControl!: FormControl<string>
    protected campusTextusObligatoriusValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{   @end-ignore-text  }}}@ */
    /* @tt{{{
    @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

    @replace-value-by-expression
        [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="FormControl<string | null>" replaceByExpression="attribute.angularFormControlTypeWithCollection" ]

    
    }}}@  */
    /* @tt{{{   @if [ conditionExpression="attribute.isNullable"]  }}}@ */
    protected campusTextusOptionalisIsNotNullControl!: FormControl<boolean>
    protected campusTextusOptionalisIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{   @end-if  }}}@ */
    protected campusTextusOptionalisControl!: FormControl<string | null>
    protected campusTextusOptionalisValidatorNames!: ReadonlyArray<ValidatorTranslation>

    /* @tt{{{  @end-foreach  }}}@ */
    /* @tt{{{   @ignore-text  }}}@ */

    protected articulusInteriorIteratusControl!: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>
    protected articulusInteriorIteratusValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected articulusInteriorOptionalisIteratusControl!: FormArray<FormGroup<ArticulusInteriorFormPartGroup>>
    protected articulusInteriorOptionalisIteratusValidatorNames!: ReadonlyArray<ValidatorTranslation>
    protected articulusInteriorOptionalisIteratusIsNotNullControl!: FormControl<boolean>
    protected articulusInteriorOptionalisIteratusIsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
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
    protected iteratioSimpliciumTextuumFormArray!: FormArray<FormControl<string>>
    protected iteratioSimpliciumTextuumValidatorNames!: ReadonlyArray<ValidatorTranslation>
    /* @tt{{{   @end-ignore-text  }}}@ */

    constructor(private readonly silvaOptionumFormValidationService: SilvaOptionumFormPartValidationService,) {
    }

    ngOnInit() {
        /* @tt{{{ @ignore-text  }}}@ */
        this.campusTextusObligatoriusControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusObligatorius]
        this.campusTextusObligatoriusValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusObligatorius)
        /* @tt{{{   @end-ignore-text }}}@ */
        /* @tt{{{ 
        @foreach [ iteratorExpression="model.item.attributes" loopVariable="attribute" ]

        @replace-value-by-expression
            [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        /* @tt{{{   @if [ conditionExpression="attribute.isNullable"]  }}}@ */
        this.campusTextusOptionalisIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull]
        this.campusTextusOptionalisIsNotNullValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusOptionalisIsNotNull)
        /* @tt{{{   @end-if  }}}@ */
        this.campusTextusOptionalisControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusTextusOptionalis]
        this.campusTextusOptionalisValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusTextusOptionalis)

        /* @tt{{{  @end-foreach  }}}@ */
        /* @tt{{{   @ignore-text  }}}@ */
        this.articulusInteriorIteratusControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorIteratus]
        this.articulusInteriorIteratusValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorIteratus)
        this.articulusInteriorOptionalisIteratusControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisIteratus]
        this.articulusInteriorOptionalisIteratusValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorOptionalisIteratus)
        this.articulusInteriorOptionalisIteratusIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorOptionalisIteratusIsNotNull]
        this.articulusInteriorOptionalisIteratusIsNotNullValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorOptionalisIteratusIsNotNull)
        this.campusDieiIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusDieiIsNotNull]
        this.campusDieiIsNotNullValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusDieiIsNotNull)
        this.campusDieiControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusDiei]
        this.campusDieiValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusDiei)
        this.campusBivalensControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusBivalens]
        this.campusBivalensValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusBivalens)
        this.appellatioControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.appellatio]
        this.appellatioValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.appellatio)
        this.articulusInteriorSingularisControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularis]
        this.articulusInteriorSingularisValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorSingularis)
        this.indexUnicusControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.indexUnicus]
        this.indexUnicusValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.indexUnicus)
        this.campusNumerorumControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.campusNumerorum]
        this.campusNumerorumValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.campusNumerorum)
        this.articulusInteriorSingularisOptionalisControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis]
        this.articulusInteriorSingularisOptionalisValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalis)
        this.articulusInteriorSingularisOptionalisIsNotNullControl = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull]
        this.articulusInteriorSingularisOptionalisIsNotNullValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.articulusInteriorSingularisOptionalisIsNotNull)
        this.iteratioSimpliciumTextuumFormArray = this.silvaOptionumForm.controls[SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum]
        this.iteratioSimpliciumTextuumValidatorNames = this.silvaOptionumFormValidationService.validatorNames(SilvaOptionumFormPartFieldName.iteratioSimpliciumTextuum)
        /* @tt{{{   @end-ignore-text  }}}@ */
    }


    /* @tt{{{ 
    @foreach [ iteratorExpression="model.item.attributesWithLists" loopVariable="attribute" ]
    @replace-value-by-expression
        [ searchValue="articulusInteriorIteratus" replaceByExpression="attribute.attributeName.camelCase" ]
        [ searchValue="ArticulusInteriorIteratus" replaceByExpression="attribute.attributeName.pascalCase" ]
        [ searchValue="FormGroup<ArticulusInteriorFormPartGroup>" replaceByExpression="attribute.angularFormControlType" ]

    }}}@  */
    onArticulusInteriorIteratusFormGroupEdit(formGroup: FormGroup<ArticulusInteriorFormPartGroup>): void {
        this.articulusInteriorIteratusFormGroupUnderEdit = formGroup;
    }

    onArticulusInteriorIteratusFormGroupDelete(formGroup: FormGroup<ArticulusInteriorFormPartGroup>): void {
        if(this.articulusInteriorIteratusFormGroupUnderEdit == formGroup) {
            this.articulusInteriorIteratusFormGroupUnderEdit = undefined
        }
        FormUtil.removeControl(this.articulusInteriorIteratusControl, formGroup)
    }

    closeArticulusInteriorIteratusFormGroupUnderEdit(): void {
        this.articulusInteriorIteratusFormGroupUnderEdit = undefined;
    }

    /* @tt{{{   @end-foreach  }}}@ */

    /* @tt{{{   @ignore-text  }}}@ */
    onArticulusInteriorOptionalisIteratusFormGroupEdit(formGroup: FormGroup<ArticulusInteriorFormPartGroup>): void {
        this.articulusInteriorOptionalisIteratusFormGroupUnderEdit = formGroup;
    }

    onArticulusInteriorOptionalisIteratusFormGroupDelete(formGroup: FormGroup<ArticulusInteriorFormPartGroup>): void {
        if(this.articulusInteriorOptionalisIteratusFormGroupUnderEdit == formGroup) {
            this.articulusInteriorOptionalisIteratusFormGroupUnderEdit = undefined
        }
        FormUtil.removeControl(this.articulusInteriorOptionalisIteratusControl, formGroup)
    }

    closeArticulusInteriorOptionalisIteratusFormGroupUnderEdit(): void {
        this.articulusInteriorOptionalisIteratusFormGroupUnderEdit = undefined;
    }
    /* @tt{{{   @end-ignore-text  }}}@ */
}
