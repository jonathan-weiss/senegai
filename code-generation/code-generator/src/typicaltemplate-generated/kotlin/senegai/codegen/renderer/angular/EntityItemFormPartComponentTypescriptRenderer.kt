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
          |import {
          |    ${model.item.itemName.pascalCase}FormPartValidationService
          |} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-validation.service";
          |import {${model.item.itemName.pascalCase}FormPartFieldName,} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-field-name";
          |import {TextInputComponent} from "@app/shared/form-controls/text-input/text-input.component";
          |import {DatepickerInputComponent} from "@app/shared/form-controls/datepicker-input/datepicker-input.component";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |import {MatTab, MatTabGroup} from "@angular/material/tabs";
          |import {${model.item.itemName.pascalCase}FormPartGroup} from "@app/${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part-group";
          |${ if(model.containsNamedSectionSplitBlocks()) { """import {SectionSplitterComponent} from "@app/shared/blocks/section-splitter/section-splitter.component";
              |
          """ } else { """
          """ } }${ if(model.containsTextBlocks()) { """import {TextBlockComponent} from "@app/shared/blocks/text-block/text-block.component";
              |
          """ } else { """
          """ } }
          |@Component({
          |    selector: 'app-${model.item.itemName.camelCase}-form-part',
          |    templateUrl: './${model.item.itemName.camelCase}-form-part.component.html',
          |    styleUrls: ['./${model.item.itemName.camelCase}-form-part.component.scss'],
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
          |        ${ if(model.containsNamedSectionSplitBlocks()) { """        SectionSplitterComponent,
              |        
          """ } else { """
          """ } }        ${ if(model.containsTextBlocks()) { """        TextBlockComponent,
              |        
          """ } else { """
          """ } }
          |    ]
          |})
          |export class ${model.item.itemName.pascalCase}FormPartComponent implements OnInit {
          |    @Input({ required: true }) ${model.item.itemName.camelCase}Form!: FormGroup<${model.item.itemName.pascalCase}FormPartGroup>;
          |    ${ model.item.attributes.joinToString("") { attribute ->  """${ if(attribute.isNullable) { """    protected ${attribute.attributeName.camelCase}IsNotNullControl!: FormControl<boolean>
                  |    protected ${attribute.attributeName.camelCase}IsNotNullValidatorNames!: ReadonlyArray<ValidatorTranslation>
          """ } else { """
          """ } }    protected ${attribute.attributeName.camelCase}Control!: FormControl<string | null>
              |    protected ${attribute.attributeName.camelCase}ValidatorNames!: ReadonlyArray<ValidatorTranslation>
              |
          """ } }
          |    constructor(private readonly ${model.item.itemName.camelCase}FormValidationService: ${model.item.itemName.pascalCase}FormPartValidationService,) {
          |    }
          |
          |    ngOnInit() {
          |        ${ model.item.attributes.joinToString("") { attribute ->  """${ if(attribute.isNullable) { """        this.${attribute.attributeName.camelCase}IsNotNullControl = FormUtil.requiredFormControl(this.${model.item.itemName.camelCase}Form, ${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull)
                  |        this.${attribute.attributeName.camelCase}IsNotNullValidatorNames = this.${model.item.itemName.camelCase}FormValidationService.validatorNames(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase}IsNotNull)
          """ } else { """
          """ } }        this.${attribute.attributeName.camelCase}Control = FormUtil.requiredFormControl(this.${model.item.itemName.camelCase}Form, ${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase})
              |        this.${attribute.attributeName.camelCase}ValidatorNames = this.${model.item.itemName.camelCase}FormValidationService.validatorNames(${model.item.itemName.pascalCase}FormPartFieldName.${attribute.attributeName.camelCase})
              |
          """ } }    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityFormViewItemModel): String {
      return "${model.entity.entityName.kebabCase}/${model.entity.entityName.kebabCase}-form/${model.item.itemName.camelCase}-form-part/${model.item.itemName.camelCase}-form-part.component.ts"
    }
}