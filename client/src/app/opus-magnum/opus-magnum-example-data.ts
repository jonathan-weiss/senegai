/* @tt{{{
    @remove-blanks-and-linebreak-before-comment

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

    @remove-blanks-and-linebreak-after-comment

}}}@ */
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
import {AppellatioEnum} from "@app/wto/appellatio.enum";
/* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */

export const OPUS_MAGNUM_EXAMPLE_DATA: OpusMagnumWTO[] = [
    {
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment
            @foreach [ iteratorExpression="model.allAttributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="title" replaceByExpression="attribute.attributeName.camelCase" ]
            @replace-value-by-value
                [ searchValue="the grande finali" replaceByValue="example" ]
            @remove-blanks-and-linebreak-after-comment
        }}}@  */
        title: 'the grande finali',
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment @end-foreach @remove-blanks-and-linebreak-after-comment }}}@ */
        /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
        id: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
        silvaOptionum: {
            id: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
            campusTextusObligatorius: 'John',
            campusTextusOptionalis: 'Johnny',
            lastname: 'Doe',
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
            campusDiei: new Date(1979, 3, 23),
            campusBivalens: false,
            appellatio: AppellatioEnum.SENIOR,
        }
    },
    {
        id: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
        title: 'populari opulari',
        silvaOptionum: {
            id: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
            campusTextusObligatorius: 'Jane',
            campusTextusOptionalis: 'Janey',
            lastname: 'Smith',
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
        }
    },
    {
        id: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
        title: 'sczenzo riguldi',
        silvaOptionum: {
            id: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
            campusTextusObligatorius: 'Robert',
            campusTextusOptionalis: null,
            lastname: 'Johnson',
            articulusInteriorList: [],
            campusDiei: new Date(1963, 11, 31),
            campusBivalens: true,
            appellatio: AppellatioEnum.SENIOR,
        }
    },
    {
        id: 'example',
        title: 'dak szena',
        silvaOptionum: {
            id: 'af18a7cc-7e7a-4388-bb32-95652fc1e379',
            campusTextusObligatorius: 'Mary',
            campusTextusOptionalis: 'Molly',
            lastname: 'Williams',
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
        }
    /* @tt{{{ @remove-blanks-and-linebreak-before-comment  @end-ignore-text @remove-blanks-and-linebreak-after-comment }}}@ */
    }
];
