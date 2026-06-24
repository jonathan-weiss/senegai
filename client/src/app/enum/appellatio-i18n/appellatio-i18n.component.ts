/* @tt{{{


    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EnumI18nComponentTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEnumRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEnumModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="Appellatio" replaceByExpression="model.enumName.pascalCase" ]
        [ searchValue="appellatio" replaceByExpression="model.enumName.camelCase" ]

    @modify-provided-filename-by-replacements


}}}@ */
import {Component, Input} from '@angular/core';
import {AppellatioEnum} from "@app/wto/appellatio.enum";

@Component({
    selector: 'app-appellatio-i18n',
    templateUrl: './appellatio-i18n.component.html',
    styleUrls: ['./appellatio-i18n.component.scss'],
    standalone: true,
})
export class AppellatioI18nComponent {
    @Input({ required: true }) enumValue!: AppellatioEnum;
}
