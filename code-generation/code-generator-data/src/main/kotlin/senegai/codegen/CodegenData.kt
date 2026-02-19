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
//                attribute(name = "street", type = STRING)
//                attribute(name = "postalCode", type = STRING)
//                attribute(name = "town", type = STRING)
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
                                    attribute(attributeName = "nickname")
                                    attribute(attributeName = "lastname")

                                    section(sectionName = "Misc")
                                    attribute(attributeName = "age")
                                }
                            }
                            tab(tabName = "Address") {
                                column {
                                    section(sectionName = "Address")
                                    attribute(attributeName = "address")
                                    attribute(attributeName = "zipCode")
                                    attribute(attributeName = "city")
                                }
                            }
                        }
                        configureNestedEntityItem(itemId = Items.ADDRESS) {
                            column {
                                attribute(attributeName = "address")
                                attribute(attributeName = "zipCode")
                                attribute(attributeName = "city")
                            }
                        }
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
                                    attribute(attributeName = "address")
                                    attribute(attributeName = "zipCode")
                                    attribute(attributeName = "city")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
