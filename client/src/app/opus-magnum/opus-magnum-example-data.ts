/* @tt{{{
    @rlb

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
        [ searchValue="OPUS_MAGNUM" replaceByExpression="model.entityName.screamingSnakeCase" ]

    @modify-provided-filename-by-replacements

    @rla

}}}@ */
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
/* @tt{{{ @rlb  @ignore-text @rla }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */

export const OPUS_MAGNUM_EXAMPLE_DATA: OpusMagnumWTO[] = [
    /* @tt{{{
    @rlb
    @render-template
        [ templateRendererClassName="ItemExampleDataRenderer" templateRendererPackageName="senegai.codegen.renderer.angular" ]
        [ modelName="itemModel" modelExpression="model.entityRootItem" ]
        [ modelName="entityModel" modelExpression="model" ]
    @rla
    }}}@ */

    /* @tt{{{ @rlb  @ignore-text @rla }}}@ */
    /* @tt{{{ @rlb
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
            @rla
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
            @rlb
            @ignore-text
            @rla
        }}}@ */
        campusBivalens: false,
        campusDiei: new Date(1979, 3, 23),
        appellatio: AppellatioEnum.SENIOR,
        campusTextusObligatorius: 'Doe',
        articulusInteriorSingularis:                 {
            description: "ALA Medal of Excellence.",
            year: 1956,
            juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
        },
        articulusInteriorList: [
            {
                description: "ALA Medal of Excellence.",
                year: 1956,
                juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
            },
            {
                description: "Joseph W. Lippincott Award",
                year: 1989,
                juryList: ["Peter Booker"]
            }
        ],
        campusNumerorum: 42,
        indexUnicus: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
        /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
        /* @tt{{{ @rlb @end-foreach @rla }}}@ */

    }/* @tt{{{ @end-template-renderer}}}@  */,
    {
        indexUnicus: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
        campusTextusObligatorius: 'Smith',
        campusTextusOptionalis: 'Jane',
        articulusInteriorSingularis:                 {
            description: "ALA Medal of Excellence.",
            year: 1956,
            juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
        },
        articulusInteriorList: [
            {
                description: "James Madison Award ",
                year: 1956,
                juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
            },
            {
                description: "John Sessions Memorial Award",
                year: 1998,
                juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
            }
        ],
        campusDiei: null,
        campusBivalens: false,
        appellatio: AppellatioEnum.MATRONA,
        campusNumerorum: -2,
    },
    {
        indexUnicus: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
        campusTextusObligatorius: 'Johnson',
        campusTextusOptionalis: null,
        articulusInteriorSingularis:                 {
            description: "ALA Medal of Excellence.",
            year: 1956,
            juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
        },
        articulusInteriorList: [],
        campusDiei: new Date(1963, 11, 31),
        campusBivalens: true,
        campusNumerorum: 7544,
        appellatio: AppellatioEnum.SENIOR,
    },
    {
        indexUnicus: 'example',
        campusTextusObligatorius: 'Williams',
        campusTextusOptionalis: 'Molly',
        articulusInteriorSingularis:                 {
            description: "ALA Medal of Excellence.",
            year: 1956,
            juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
        },
        articulusInteriorList: [
            {
                description: "Jean E. Coleman Library Outreach Lecture",
                year: 1956,
                juryList: ["W.Y. Boyd", "Beta Phi", "Joseph Lippincott"]
            },
            {
                description: "John Sessions Memorial Award",
                year: 1998,
                juryList: ["Justin Windsor", "Edward Swandson", "May Hill"]
            }
        ],
        campusDiei: new Date(1954, 8, 3),
        campusBivalens: false,
        appellatio: AppellatioEnum.MATRONA,
        campusNumerorum: 687358,
    }
    /* @tt{{{ @rlb  @end-ignore-text @rla }}}@ */
];
