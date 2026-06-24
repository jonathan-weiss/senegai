/* @tt{{{


    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityExampleDataRenderer"
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
        [ searchValue="SILVA_OTIONUM" replaceByExpression="model.entityRootItem.itemName.screamingSnakeCase" ]

    @modify-provided-filename-by-replacements



}}}@ */
import {SilvaOptionumWTO} from "@app/wto/silva-optionum.wto";
/* @tt{{{   @ignore-text  }}}@ */
import {AppellatioComisEnum} from "@app/wto/appellatio-comis.enum";
/* @tt{{{   @end-ignore-text  }}}@ */

export const SILVA_OTIONUM_EXAMPLE_DATA: SilvaOptionumWTO[] = [
    /* @tt{{{

    @render-template
        [ templateRendererClassName="ItemExampleDataRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" ]
        [ modelName="itemModel" modelExpression="model.entityRootItem" ]
        [ modelName="entityModel" modelExpression="model" ]

    }}}@ */

    /* @tt{{{   @ignore-text  }}}@ */
    /* @tt{{{
        @template-renderer [
            templateRendererClassName="ItemExampleDataRenderer"
            templateRendererPackageName="senegai.codegen.renderer.angular"
        ] [
            modelClassName="UiItemModel"
            modelPackageName="senegai.codegen.renderer.model.ui"
            modelName="itemModel"
        ] [
            modelClassName="UiEntityModel"
            modelPackageName="senegai.codegen.renderer.model.ui"
            modelName="entityModel"
        ]
        @print-text [text="        "]
        @modify-provided-filename-by-replacements

    }}}@  */
    {
        /* @tt{{{
            @foreach [ iteratorExpression="itemModel.attributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="campusTextusOptionalis" replaceByExpression="attribute.attributeName.camelCase" ]

        }}}@  */
        /* @tt{{{
            @move-comment-forward [ afterFirstOccurrenceOf="campusTextusOptionalis: " ]
            @render-template
                [ templateRendererClassName="AttributeExampleDataRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" ]
                [ modelName="attributeModel" modelExpression="attribute" ]
                [ modelName="entityModel" modelExpression="entityModel" ]
            @ignore-text
        }}}@  */
        campusTextusOptionalis: 'Johnny'/* @tt{{{@end-ignore-text}}}@  */,
        /* @tt{{{

            @ignore-text

        }}}@ */
        campusBivalens: false,
        campusDiei: new Date(1979, 3, 23),
        appellatio: AppellatioComisEnum.VIR_HONORATUS,
        campusTextusObligatorius: 'Doe',
        articulusInteriorSingularis: {
            scriptumTriviale: "ALA Medal of Excellence.",
            numerusStupidus: 1956
        },
        articulusInteriorIteratus: [
            {
                scriptumTriviale: "ALA Medal of Excellence.",
                numerusStupidus: 1956
            },
            {
                scriptumTriviale: "Joseph W. Lippincott Award",
                numerusStupidus: 1989
            }
        ],
        articulusInteriorOptionalisIteratus: [
            {
                scriptumTriviale: "Qwert keyboard.",
                numerusStupidus: 1968
            },
        ],
        articulusInteriorSingularisOptionalis: {
            scriptumTriviale: "Truth an right.",
            numerusStupidus: 1981
        },
        campusNumerorum: 42,
        indexUnicus: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
        iteratioSimpliciumTextuum: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"],
        /* @tt{{{   @end-ignore-text  }}}@ */
        /* @tt{{{  @end-foreach  }}}@ */

    }/* @tt{{{ @end-template-renderer}}}@  */,
    {
        indexUnicus: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
        campusTextusObligatorius: 'Smith',
        campusTextusOptionalis: 'Jane',
        articulusInteriorSingularis: {
            scriptumTriviale: "ALA Medal of Excellence.",
            numerusStupidus: 1956
        },
        articulusInteriorIteratus: [
            {
                scriptumTriviale: "James Madison Award ",
                numerusStupidus: 1956
            },
            {
                scriptumTriviale: "John Sessions Memorial Award",
                numerusStupidus: 1998
            }
        ],
        articulusInteriorOptionalisIteratus: [],
        articulusInteriorSingularisOptionalis: null,
        campusDiei: null,
        campusBivalens: false,
        appellatio: AppellatioComisEnum.FEMINA_HONESTA,
        campusNumerorum: -2,
        iteratioSimpliciumTextuum: ["Peter Booker"],
    },
    {
        indexUnicus: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
        campusTextusObligatorius: 'Johnson',
        campusTextusOptionalis: null,
        articulusInteriorSingularis: {
            scriptumTriviale: "ALA Medal of Excellence.",
            numerusStupidus: 1956
        },
        articulusInteriorIteratus: [],
        articulusInteriorSingularisOptionalis: null,
        articulusInteriorOptionalisIteratus: null,
        campusDiei: new Date(1963, 11, 31),
        campusBivalens: true,
        campusNumerorum: 7544,
        appellatio: AppellatioComisEnum.VIR_HONORATUS,
        iteratioSimpliciumTextuum: [],
    },
    {
        indexUnicus: 'example',
        campusTextusObligatorius: 'Williams',
        campusTextusOptionalis: 'Molly',
        articulusInteriorSingularis: {
            scriptumTriviale: "ALA Medal of Excellence.",
            numerusStupidus: 1956
        },
        articulusInteriorIteratus: [
            {
                scriptumTriviale: "Jean E. Coleman Library Outreach Lecture",
                numerusStupidus: 1956
            },
            {
                scriptumTriviale: "John Sessions Memorial Award",
                numerusStupidus: 1998
            }
        ],
        articulusInteriorSingularisOptionalis: {
            scriptumTriviale: "A bright shining day",
            numerusStupidus: 2021
        },
        articulusInteriorOptionalisIteratus: null,
        campusDiei: new Date(1954, 8, 3),
        campusBivalens: false,
        appellatio: AppellatioComisEnum.FEMINA_HONESTA,
        campusNumerorum: 687358,
        iteratioSimpliciumTextuum: ["W.Y. Boyd", "Beta Phi", "Joseph Lippincott"],
    }
    /* @tt{{{   @end-ignore-text  }}}@ */
];
