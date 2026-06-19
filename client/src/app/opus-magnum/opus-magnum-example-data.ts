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
import {AppellatioEnum} from "@app/wto/appellatio.enum";
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
        appellatio: AppellatioEnum.SENIOR,
        campusTextusObligatorius: 'Doe',
        articulusInteriorSingularis: {
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
        articulusInteriorOptionalisList: [
            {
                description: "Qwert keyboard.",
                year: 1968,
                juryList: ["Helene Qerty"]
            },
        ],
        articulusInteriorSingularisOptionalis: {
            description: "Truth an right.",
            year: 1981,
            juryList: ["Jonathan Wright"]
        },
        campusNumerorum: 42,
        indexUnicus: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
        /* @tt{{{   @end-ignore-text  }}}@ */
        /* @tt{{{  @end-foreach  }}}@ */

    }/* @tt{{{ @end-template-renderer}}}@  */,
    {
        indexUnicus: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
        campusTextusObligatorius: 'Smith',
        campusTextusOptionalis: 'Jane',
        articulusInteriorSingularis: {
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
        articulusInteriorOptionalisList: [],
        articulusInteriorSingularisOptionalis: null,
        campusDiei: null,
        campusBivalens: false,
        appellatio: AppellatioEnum.MATRONA,
        campusNumerorum: -2,
    },
    {
        indexUnicus: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
        campusTextusObligatorius: 'Johnson',
        campusTextusOptionalis: null,
        articulusInteriorSingularis: {
            description: "ALA Medal of Excellence.",
            year: 1956,
            juryList: ["Elisabeth Smith", "Aaron Glasgow", "James Duroldi"]
        },
        articulusInteriorList: [],
        articulusInteriorSingularisOptionalis: null,
        articulusInteriorOptionalisList: null,
        campusDiei: new Date(1963, 11, 31),
        campusBivalens: true,
        campusNumerorum: 7544,
        appellatio: AppellatioEnum.SENIOR,
    },
    {
        indexUnicus: 'example',
        campusTextusObligatorius: 'Williams',
        campusTextusOptionalis: 'Molly',
        articulusInteriorSingularis: {
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
        articulusInteriorSingularisOptionalis: {
            description: "A bright shining day",
            year: 2021,
            juryList: ["Isaac Hamilton"]
        },
        articulusInteriorOptionalisList: null,
        campusDiei: new Date(1954, 8, 3),
        campusBivalens: false,
        appellatio: AppellatioEnum.MATRONA,
        campusNumerorum: 687358,
    }
    /* @tt{{{   @end-ignore-text  }}}@ */
];
