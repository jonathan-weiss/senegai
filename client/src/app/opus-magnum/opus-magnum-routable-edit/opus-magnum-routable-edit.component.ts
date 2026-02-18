/* @tt{{{

    @slbc

    @template-renderer [ templateRendererClassName="EntityRoutableEditComponentTypescriptRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" templateRendererInterfaceName="UiEntityRenderer" templateRendererInterfacePackageName="senegai.codegen.renderer.angular"]

    @template-model [
        modelClassName="UiEntityModel"
        modelPackageName="senegai.codegen.renderer.model.ui"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityNameLowercase" ]
        [ searchValue="opus-magnum" replaceByExpression="model.entityNameDashCase" ]

    @modify-provided-filename-by-replacements

    @slac

}}}@ */
import {Component} from '@angular/core';
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
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
    selectedOpusMagnum: OpusMagnumWTO | null = null;

    constructor(
        private opusMagnumService: OpusMagnumService,
        private route: ActivatedRoute,
    ) {
        this.route.params.subscribe(params => {
            const idParam = params['id'];
            if (idParam) {
                const id = idParam as string;
                this.opusMagnumService.getOpusMagnumById(id).subscribe(opusMagnum => {
                    this.selectedOpusMagnum = opusMagnum;
                });
            }
        });
    }
}
