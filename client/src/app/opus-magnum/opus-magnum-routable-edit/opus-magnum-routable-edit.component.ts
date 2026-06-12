/* @tt{{{

    @rlb

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityRoutableEditComponentTypescriptRenderer"
        templateRendererPackageName="senegai.codegen.renderer.angular"
        templateRendererInterfaceName="UiEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.angular"
    ] [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityName.kebabCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="silva-optionum" replaceByExpression="model.entityRootItem.itemName.kebabCase" ]

    @replace-value-by-expression
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */
import {Component} from '@angular/core';
import {SilvaOptionumWTO} from "@app/wto/silva-optionum-wto";
import {OpusMagnumService} from "@app/opus-magnum/opus-magnum.service";
import {OpusMagnumFormComponent} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: 'app-opus-magnum-routable-edit',
    templateUrl: './opus-magnum-routable-edit.component.html',
    styleUrls: ['./opus-magnum-routable-edit.component.scss'],
    imports: [
        OpusMagnumFormComponent,
    ]
})
export class OpusMagnumRoutableEditComponent {
    selectedOpusMagnum: SilvaOptionumWTO | null = null;

    constructor(
        private opusMagnumService: OpusMagnumService,
        private route: ActivatedRoute,
    ) {
        this.route.params.subscribe(params => {
            const idParam = params['indexUnicus'];
            if (idParam) {
                const indexUnicus = idParam as string;
                this.opusMagnumService.getSilvaOptionumById(indexUnicus).subscribe(opusMagnum => {
                    this.selectedOpusMagnum = opusMagnum;
                });
            }
        });
    }
}
