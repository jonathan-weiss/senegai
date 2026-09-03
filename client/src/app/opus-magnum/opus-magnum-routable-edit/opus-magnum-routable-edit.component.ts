/* @tt{{{

    

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
        [ searchValue="UUID" replaceByExpression="model.idAttribute.typescriptAttributeType" ]

    @modify-provided-filepath-by-replacements

    

}}}@ */
import {Component} from '@angular/core';
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
import {SilvaOptionumService} from "@app/service/silva-optionum.service";
import {OpusMagnumFormComponent} from "@app/opus-magnum/opus-magnum-form/opus-magnum-form/opus-magnum-form.component";
import {ActivatedRoute} from "@angular/router";
/* @tt{{{   @if [ conditionExpression="model.entityRootItem.hasUuidPrimaryKey"]  }}}@ */
import {UUID} from "@app/shared/uuid";
/* @tt{{{   @end-if  }}}@ */

import {TranslocoPipe} from "@jsverse/transloco";

@Component({
    selector: 'app-opus-magnum-routable-edit',
    templateUrl: './opus-magnum-routable-edit.component.html',
    styleUrls: ['./opus-magnum-routable-edit.component.scss'],
    imports: [
        OpusMagnumFormComponent,
        TranslocoPipe,
    ]
})
export class OpusMagnumRoutableEditComponent {
    selectedOpusMagnum: SilvaOptionumWTO | null = null;

    constructor(
        private silvaOptionumService: SilvaOptionumService,
        private route: ActivatedRoute,
    ) {
        this.route.params.subscribe(params => {
            const idParam = params['indexUnicus'];
            if (idParam) {
                /* @tt{{{
                    @replace-value-by-expression
                        [ searchValue="idParam as UUID" replaceByExpression="model.entityRootItem.primaryKeyFromRouteParamExpression" ]
                }}}@ */
                const indexUnicus = idParam as UUID;
                /* @tt{{{   @end-replace-value-by-expression  }}}@ */
                this.silvaOptionumService.getSilvaOptionumById(indexUnicus).subscribe(opusMagnum => {
                    this.selectedOpusMagnum = opusMagnum;
                });
            }
        });
    }
}
