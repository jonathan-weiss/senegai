/* @tt{{{
    #expand-comment [ direction="backward" strip="linebreak"]

    #move-comment [ direction="backward" ]
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

    #expand-comment [ direction="forward" strip="linebreak"]

}}}@ */
import {OpusMagnumWTO} from "@app/wto/opus-magnum.wto";
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
import {GenderEnum} from "@app/wto/gender.enum";
/* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */

export const OPUS_MAGNUM_EXAMPLE_DATA: OpusMagnumWTO[] = [
    {
        /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]
            @foreach [ iteratorExpression="model.allAttributes" loopVariable="attribute" ]

            @replace-value-by-expression
                [ searchValue="title" replaceByExpression="attribute.attributeName.camelCase" ]
            @replace-value-by-value
                [ searchValue="the grande finali" replaceByValue="example" ]
            #expand-comment [ direction="forward" strip="linebreak"]
        }}}@  */
        title: 'the grande finali',
        /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"] @end-foreach #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
        /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
        id: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
        author: {
            id: '828cf29b-a7fb-4b07-bf13-9a313a9967f6',
            firstname: 'John',
            nickname: 'Johnny',
            lastname: 'Doe',
            libraryAwardList: [
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
            birthday: new Date(1979, 3, 23),
            vegetarian: false,
            gender: GenderEnum.MALE,
        }
    },
    {
        id: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
        title: 'populari opulari',
        author: {
            id: '6b9a179c-641b-4204-a6ae-46be2fbbaa3a',
            firstname: 'Jane',
            nickname: 'Janey',
            lastname: 'Smith',
            libraryAwardList: [
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
            birthday: null,
            vegetarian: false,
            gender: GenderEnum.FEMALE,
        }
    },
    {
        id: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
        title: 'sczenzo riguldi',
        author: {
            id: 'd4076f05-50ac-4ceb-b54d-06f5c77874e4',
            firstname: 'Robert',
            nickname: null,
            lastname: 'Johnson',
            libraryAwardList: [],
            birthday: new Date(1963, 11, 31),
            vegetarian: true,
            gender: GenderEnum.MALE,
        }
    },
    {
        id: 'example',
        title: 'dak szena',
        author: {
            id: 'af18a7cc-7e7a-4388-bb32-95652fc1e379',
            firstname: 'Mary',
            nickname: 'Molly',
            lastname: 'Williams',
            libraryAwardList: [
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
            birthday: new Date(1954, 8, 3),
            vegetarian: false,
            gender: GenderEnum.FEMALE,
        }
    /* @tt{{{ #expand-comment [ direction="backward" strip="linebreak"]  @end-ignore-text #expand-comment [ direction="forward" strip="linebreak"] }}}@ */
    }
];
