package senegai.codegen

import senegai.codegen.builders.RootDsl
import senegai.codegen.schema.BuiltInType
import senegai.codegen.schema.EntityId
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemId

object CodegenData {
    fun RootDsl.collectData() {
        collectEntityData()
        collectUiData()
    }

    enum class Entities(override val entityName: String): EntityId {
        EMPLOYEE(entityName ="Employee"),
        EXTERNAL_PERSON(entityName = "ExternalPerson"),
        ;
    }

    enum class Items(override val itemName: String): ItemId {
        CONTACT(itemName = "Contact"),
        ADDRESS(itemName ="Address"),
        ;
    }

    enum class EnumTypes(override val enumName: String): EnumId {
        GENDER(enumName = "Gender"),
        ;
    }

    private fun RootDsl.collectEntityData() {
        schema {
            entity(entityId = Entities.EMPLOYEE, entityRootItemId = Items.CONTACT)
            entity(entityId = Entities.EXTERNAL_PERSON, entityRootItemId = Items.ADDRESS)

            enumType(enumId = EnumTypes.GENDER) {
                enumValue(name = "Male")
                enumValue(name = "Female")
            }

            item(itemId = Items.CONTACT) {
                attribute(name = "id", type = BuiltInType.STRING)
                attribute(name = "firstname", type = BuiltInType.STRING)
                attribute(name = "nickname", type = BuiltInType.STRING)
                attribute(name = "lastname", type = BuiltInType.STRING)
                // attribute(name = "age", type = BuiltInType.NUMBER)
                attribute(name = "address", type = BuiltInType.STRING)
                attribute(name = "zipCode", type = BuiltInType.STRING)
                attribute(name = "city", type = BuiltInType.STRING)
            }

            item(itemId = Items.ADDRESS) {
                attribute(name = "id", type = BuiltInType.STRING)
                attribute(name = "firstname", type = BuiltInType.STRING)
                attribute(name = "lastname", type = BuiltInType.STRING)
                attribute(name = "street", type = BuiltInType.STRING)
                attribute(name = "postalCode", type = BuiltInType.STRING)
                attribute(name = "town", type = BuiltInType.STRING)
            }
        }
    }

    private fun RootDsl.collectUiData() {
        schema {
            uiEntity(entityId = Entities.EMPLOYEE) {
                views {
                    editor {
                        configureEditorForEntity {
                            tab(tabName = "Common") {
                                column {
                                    section(sectionName = "Names")
                                    text(text = "Lorem ipsum dolor sit amet")
                                    attribute(attributeName = "firstname")
                                    text(text = "First things first")
                                    attribute(attributeName = "nickname")
                                    text(text = "Nick, you have a nickname")
                                    attribute(attributeName = "lastname")
                                    text(text = "The lastname, the lastname")

                                }
                            }
                            tab(tabName = "Miscellaneous") {
                                column {
                                    section(sectionName = "Misc")
                                    text(text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.")
                                    //attribute(attributeName = "age")
                                }
                                column {
                                    section(sectionName = "Legend:")
                                    text(text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.  \n" +
                                            "\n" +
                                            "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.  \n" +
                                            "\n" +
                                            "Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.  \n" +
                                            "\n" +
                                            "Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem")
                                    //attribute(attributeName = "age")
                                }

                            }
                        }
// TODO ADDRESS is not a correct nested item
//                        configureNestedEntityItem(itemId = Items.ADDRESS) {
//                            column {
//                                attribute(attributeName = "address")
//                                attribute(attributeName = "zipCode")
//                                attribute(attributeName = "city")
//                            }
//                        }
                    }
                }
            }
            uiEntity(entityId = Entities.EXTERNAL_PERSON) {
                views {
                    editor {
                        configureEditorForEntity {
                            tab(tabName = "Address") {
                                column {
                                    section(sectionName = "Address")
                                    attribute(attributeName = "street")
                                    attribute(attributeName = "postalCode")
                                    attribute(attributeName = "town")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
