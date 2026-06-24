/* @tt{{{


    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EnumSelectorComponentTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEnumRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEnumModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="AppellatioComis" replaceByExpression="model.enumName.pascalCase" ]
        [ searchValue="appellatioComis" replaceByExpression="model.enumName.camelCase" ]
        [ searchValue="appellatio-comis" replaceByExpression="model.enumName.kebabCase" ]

    @modify-provided-filename-by-replacements


}}}@ */
import {Component, Input} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
import {TranslocoPipe} from "@jsverse/transloco";
import {AppellatioComisI18nComponent} from "@app/enum/appellatio-comis-i18n/appellatio-comis-i18n.component";
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {AppellatioComisEnumValues} from "@app/wto/appellatio-comis.enum";

@Component({
    selector: 'app-appellatio-comis-selector',
    templateUrl: './appellatio-comis-selector.component.html',
    styleUrls: ['./appellatio-comis-selector.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        TranslocoPipe,
        AppellatioComisI18nComponent,
        MatOption,
        MatSelect,
    ]
})
export class AppellatioComisSelectorComponent {
    @Input() label: string = '';
    @Input({required: true}) enumFormControl!: FormControl;
    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];

    hasError(errorName: string): boolean {
        return this.enumFormControl.hasError(errorName) && this.enumFormControl.touched;
    }


    protected readonly appellatioComisList = AppellatioComisEnumValues;
}
