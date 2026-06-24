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
        COUNTRY(itemName = "Country"),
    }

    enum class EnumTypes(
        override val enumName: String,
    ) : EnumId {
        SALUTATION(enumName = "Gender"),
        MARITAL_STATUS(enumName = "MaritalStatus"),
        CONTACT_TYPE(enumName = "ContactType"),
    }

    private fun RootDsl.collectEntityData() {
        schema {
            entity(entityId = Entities.EMPLOYEE, entityRootItemId = Items.CONTACT)
            entity(entityId = Entities.EMPLOYEE_ADDRESS, entityRootItemId = Items.ADDRESS)

            enumType(enumId = EnumTypes.SALUTATION) {
                enumValue(name = "Mr")
                enumValue(name = "Ms")
                enumValue(name = "Mrs")
            }

            enumType(enumId = EnumTypes.MARITAL_STATUS) {
                enumValue(name = "single")
                enumValue(name = "married")
                enumValue(name = "divorced")
                enumValue(name = "widowed")
            }

            enumType(enumId = EnumTypes.CONTACT_TYPE) {
                enumValue(name = "email")
                enumValue(name = "phone")
                enumValue(name = "sms")
                enumValue(name = "post")
            }

            item(itemId = Items.CONTACT) {
                attribute(name = "contactId", type = BuiltInType.STRING)
                attribute(name = "contactSalutation", enumId = EnumTypes.SALUTATION)
                attribute(name = "firstname", type = BuiltInType.STRING)
                attribute(name = "nickname", type = BuiltInType.STRING, nullable = true)
                attribute(name = "lastname", type = BuiltInType.STRING)
                attribute(name = "allKnownNicknames", type = BuiltInType.STRING, multiple = true)
                attribute(name = "age", type = BuiltInType.NUMBER)
                attribute(name = "vegetarian", type = BuiltInType.BOOLEAN)
                attribute(name = "homeAddress", itemId = Items.ADDRESS, nullable = false)
                attribute(name = "contactAddress", itemId = Items.ADDRESS, nullable = true)
                attribute(name = "mandatoryAddresses", itemId = Items.ADDRESS, nullable = false, multiple = true)
                attribute(name = "otherAddresses", itemId = Items.ADDRESS, nullable = true, multiple = true)
                attribute(name = "allKnownPinNumbers", type = BuiltInType.NUMBER, nullable = true, multiple = true)
            }

            item(itemId = Items.ADDRESS) {
                attribute(name = "id", type = BuiltInType.STRING)
                attribute(name = "street", type = BuiltInType.STRING)
                attribute(name = "postalCode", type = BuiltInType.STRING)
                attribute(name = "town", type = BuiltInType.STRING)
                attribute(name = "country", itemId = Items.COUNTRY)
            }

            item(itemId = Items.COUNTRY) {
                attribute(name = "countryIsoCode", type = BuiltInType.STRING)
                attribute(name = "countryName", type = BuiltInType.STRING)
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
                                    attribute(attributeName = "contactSalutation")
                                    section(sectionName = "Names")
                                    text(text = "Lorem ipsum dolor sit amet")
                                    attribute(attributeName = "firstname")
                                    text(text = "First things first")
                                    attribute(attributeName = "nickname")
                                    attribute(attributeName = "allKnownNicknames")
                                    text(text = "Nick, you have a nickname")
                                    attribute(attributeName = "lastname")
                                    text(text = "The lastname, the lastname")
                                    section(sectionName = "Addresses")
                                    attribute(attributeName = "homeAddress")
                                    attribute(attributeName = "mandatoryAddresses")
                                    attribute(attributeName = "otherAddresses")
                                    attribute(attributeName = "contactAddress")
                                    attribute(attributeName = "allKnownPinNumbers")
                                }
                            }
                            tab(tabName = "Miscellaneous") {
                                column {
                                    section(sectionName = "Misc")
                                    text(text = Constants.TEXT)
                                }
                                column {
                                    section(sectionName = "Legend:")
                                    text(text = Constants.MULTILINE_TEXT)
                                    attribute(attributeName = "age")
                                    attribute(attributeName = "vegetarian")
                                }
                            }
                        }
                        configureNestedEntityItem(itemId = Items.ADDRESS) {
                            column {
                                attribute(attributeName = "id")
                                attribute(attributeName = "street")
                                attribute(attributeName = "postalCode")
                                attribute(attributeName = "town")
                                attribute(attributeName = "country")
                            }
                        }
                        configureNestedEntityItem(itemId = Items.COUNTRY) {
                            column {
                                attribute(attributeName = "countryIsoCode")
                                attribute(attributeName = "countryName")
                            }
                        }
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
                                    attribute(attributeName = "country")
                                }
                            }
                        }
                        configureNestedEntityItem(itemId = Items.ADDRESS) {
                            column {
                                attribute(attributeName = "id")
                                attribute(attributeName = "street")
                                attribute(attributeName = "postalCode")
                                attribute(attributeName = "town")
                                attribute(attributeName = "country")
                            }
                        }
                        configureNestedEntityItem(itemId = Items.COUNTRY) {
                            column {
                                attribute(attributeName = "countryIsoCode")
                                attribute(attributeName = "countryName")
                            }
                        }
                    }
                }
            }
        }
    }
}
