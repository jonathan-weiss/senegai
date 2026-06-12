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

    enum class Entities(
        override val entityName: String,
    ) : EntityId {
        EMPLOYEE(entityName = "Employee"),
        EMPLOYEE_ADDRESS(entityName = "EmployeeAddress"),
    }

    enum class Items(
        override val itemName: String,
    ) : ItemId {
        CONTACT(itemName = "Contact"),
        ADDRESS(itemName = "Address"),
    }

    enum class EnumTypes(
        override val enumName: String,
    ) : EnumId {
        APPELLATIO(enumName = "Appellatio"),
    }

    private fun RootDsl.collectEntityData() {
        schema {
            entity(entityId = Entities.EMPLOYEE, entityRootItemId = Items.CONTACT)
            entity(entityId = Entities.EMPLOYEE_ADDRESS, entityRootItemId = Items.ADDRESS)

            enumType(enumId = EnumTypes.APPELLATIO) {
                enumValue(name = "Male")
                enumValue(name = "Female")
            }

            item(itemId = Items.CONTACT) {
                attribute(name = "contactId", type = BuiltInType.STRING)
                attribute(name = "firstname", type = BuiltInType.STRING)
                attribute(name = "nickname", type = BuiltInType.STRING, nullable = true)
                attribute(name = "lastname", type = BuiltInType.STRING)
                attribute(name = "age", type = BuiltInType.NUMBER)
                attribute(name = "vegetarian", type = BuiltInType.BOOLEAN)
                //attribute(name = "contactAddress", itemId = Items.ADDRESS, multiple = true)
            }

            item(itemId = Items.ADDRESS) {
                attribute(name = "id", type = BuiltInType.STRING)
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
                                    attribute(attributeName = "contactId")
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
                                    @Suppress("MaxLineLength")
                                    text(text = Constants.TEXT)
                                }
                                column {
                                    section(sectionName = "Legend:")
                                    @Suppress()
                                    text(text = Constants.MULTILINE_TEXT)
                                    attribute(attributeName = "age")
                                    attribute(attributeName = "vegetarian")
                                    //attribute(attributeName = "contactAddress")
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
            uiEntity(entityId = Entities.EMPLOYEE_ADDRESS) {
                views {
                    editor {
                        configureEditorForEntity {
                            tab(tabName = "Address") {
                                column {
                                    attribute(attributeName = "id")
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
