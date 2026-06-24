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
        [ searchValue="AppellatioComis" replaceByExpression="model.enumName.pascalCase" ]
        [ searchValue="appellatioComis" replaceByExpression="model.enumName.camelCase" ]
        [ searchValue="appellatio-comis" replaceByExpression="model.enumName.kebabCase" ]

    @modify-provided-filename-by-replacements


}}}@ */
import {Component, Input} from '@angular/core';
import {AppellatioComisEnum} from "@app/wto/appellatio-comis.enum";

@Component({
    selector: 'app-appellatio-comis-i18n',
    templateUrl: './appellatio-comis-i18n.component.html',
    styleUrls: ['./appellatio-comis-i18n.component.scss'],
    standalone: true,
})
export class AppellatioComisI18nComponent {
    @Input({ required: true }) enumValue!: AppellatioComisEnum;
}
