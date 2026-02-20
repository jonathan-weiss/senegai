/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

/**
 * Generate the content for the template EntityItemFormPartComponentTypescriptRenderer filled up
 * with the content of the passed models.
 */
object EntityItemFormPartComponentTypescriptRenderer : UiEntityItemRenderer {

    override fun renderTemplate(model: UiEntityFormViewItemModel): String {
        return """
          |import {Component, Input, OnInit} from '@angular/core';
          |import {FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
          |import {MatButtonModule} from "@angular/material/button";
          |import {MatToolbarModule} from "@angular/material/toolbar";
          |import {MatTableModule} from "@angular/material/table";
          |import {MatCardModule} from "@angular/material/card";
          |import {MatFormFieldModule} from "@angular/material/form-field";
          |import {MatInputModule} from "@angular/material/input";
          |import {MatIconModule} from "@angular/material/icon";
          |import {MatExpansionModule} from "@angular/material/expansion";
          |import {MatSidenavModule} from "@angular/material/sidenav";
          |import {MatListModule} from "@angular/material/list";
          |import {MatDialogModule} from "@angular/material/dialog";
          |import {FormUtil} from "@app/shared/form-controls/form.util";
          |import {FieldWrapperComponent} from "@app/shared/form-controls/field-wrapper/field-wrapper.component";
          |import {MatOption} from "@angular/material/core";
          |import {MatSelect} from "@angular/material/select";
          |import {${model.item.itemName}FormPartValidationService} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-validation.service";
          |import {
          |    ${model.item.itemName}FormPartFieldName,
          |} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-field-name";
          |import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
          |import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |import {MatTab, MatTabGroup} from "@angular/material/tabs";
          |import {
          |    ${model.item.itemName}FormPartGroup
          |} from "@app/${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part-group";
          |${ if(model.containsNamedSectionSplitBlocks()) { """import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
              |
          """ } else { """
          """ } }${ if(model.containsTextBlocks()) { """import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
              |
          """ } else { """
          """ } }
          |@Component({
          |    selector: 'app-${model.item.itemNameLowercase}-form-part',
          |    templateUrl: './${model.item.itemNameLowercase}-form-part.component.html',
          |    styleUrls: ['./${model.item.itemNameLowercase}-form-part.component.scss'],
          |    imports: [
          |        ReactiveFormsModule,
          |        MatButtonModule,
          |        MatToolbarModule,
          |        MatTableModule,
          |        MatCardModule,
          |        MatFormFieldModule,
          |        MatInputModule,
          |        MatIconModule,
          |        MatExpansionModule,
          |        MatSidenavModule,
          |        MatListModule,
          |        MatDialogModule,
          |        FieldWrapperComponent,
          |        TextInputComponent,
          |        MatTabGroup,
          |        MatTab,
          |    ${ if(model.containsNamedSectionSplitBlocks()) { """        SectionSplitterComponent,
              |    
          """ } else { """
          """ } }    ${ if(model.containsTextBlocks()) { """        TextBlockComponent,
              |    
          """ } else { """
          """ } }
          |    ]
          |})
          |export class ${model.item.itemName}FormPartComponent implements OnInit {
          |    @Input({ required: true }) ${model.item.itemNameLowercase}Form!: FormGroup<${model.item.itemName}FormPartGroup>;
          |    ${ model.item.attributes.joinToString("") { attribute ->  """    protected ${attribute.attributeName}Control!: FormControl<string>
              |    protected ${attribute.attributeName}ValidatorNames!: ReadonlyArray<ValidatorTranslation>
              |
          """ } }
          |    constructor(private readonly ${model.item.itemNameLowercase}FormValidationService: ${model.item.itemName}FormPartValidationService,) {
          |    }
          |
          |    ngOnInit() {
          |        ${ model.item.attributes.joinToString("") { attribute ->  """
              |        this.${attribute.attributeName}Control = FormUtil.requiredFormControl(this.${model.item.itemNameLowercase}Form, ${model.item.itemName}FormPartFieldName.${attribute.attributeName})
              |        this.${attribute.attributeName}ValidatorNames = this.${model.item.itemNameLowercase}FormValidationService.validatorNames(${model.item.itemName}FormPartFieldName.${attribute.attributeName})
              |
          """ } }    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityNameDashCase}/${model.entity.entityNameDashCase}-form/${model.item.itemNameLowercase}-form-part/${model.item.itemNameLowercase}-form-part.component.ts"
    }
}