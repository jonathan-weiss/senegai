package senegai.codegen.definitions

import senegai.codegen.builders.RootDsl
import senegai.codegen.schema.BuiltInType.NUMBER
import senegai.codegen.schema.BuiltInType.STRING
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
            entity(entityId = Entities.EXTERNAL_PERSON, entityRootItemId = Items.CONTACT)

            enumType(enumId = EnumTypes.GENDER) {
                enumValue(name = "Male")
                enumValue(name = "Female")
            }

            item(itemId = Items.CONTACT) {
                attribute(name = "firstname", type = STRING)
                attribute(name = "nickname", type = STRING)
                attribute(name = "lastname", type = STRING)
                attribute(name = "age", type = NUMBER)
                attribute(name = "address", type = STRING)
                attribute(name = "zipCode", type = STRING)
                attribute(name = "city", type = STRING)
            }

            item(itemId = Items.ADDRESS) {
                attribute(name = "firstname", type = STRING)
                attribute(name = "lastname", type = STRING)
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
                        tab(tabName = "Common") {
                            column {
                                section(sectionName = "Names") {
                                    entityAttribute(attributeName = "firstname")
                                    entityAttribute(attributeName = "nickname")
                                    entityAttribute(attributeName = "lastname")
                                }
                                section(sectionName = "Misc") {
                                    entityAttribute(attributeName = "age")
                                    entityAttribute(attributeName = "nickname")
                                    entityAttribute(attributeName = "lastname")
                                }
                            }
                        }
                        tab(tabName = "Address") {
                            column {
                                section(sectionName = "Address") {
                                    entityAttribute(attributeName = "address")
                                    entityAttribute(attributeName = "zipCode")
                                    entityAttribute(attributeName = "city")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
