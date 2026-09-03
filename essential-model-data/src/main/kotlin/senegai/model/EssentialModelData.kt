package senegai.model

import senegai.model.builders.RootDsl
import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.ExampleDataCategory
import senegai.model.schema.ItemId

object EssentialModelData {
    fun RootDsl.collectData() {
        collectItemData()
        collectUiData()
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

    private fun RootDsl.collectItemData() {
        schema {
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
                primaryKey(attributeName = "contactId")

                attribute(name = "contactId", type = BuiltInType.UUID)
                attribute(name = "contactSalutation", enumId = EnumTypes.SALUTATION)
                attribute(name = "firstname", type = BuiltInType.STRING, exampleDataCategory = ExampleDataCategory.FIRSTNAME)
                attribute(name = "nickname", type = BuiltInType.STRING, nullable = true, exampleDataCategory = ExampleDataCategory.FIRSTNAME)
                attribute(name = "lastname", type = BuiltInType.STRING, customValidation = true , exampleDataCategory = ExampleDataCategory.LASTNAME)
                attribute(name = "allKnownNicknames", type = BuiltInType.STRING, multiple = true, exampleDataCategory = ExampleDataCategory.FIRSTNAME)
                attribute(name = "age", type = BuiltInType.NUMBER, customValidation = true, exampleDataCategory = ExampleDataCategory.AGE)
                attribute(name = "vegetarian", type = BuiltInType.BOOLEAN)
                attribute(name = "homeAddress", itemId = Items.ADDRESS, nullable = false)
                attribute(name = "contactAddress", itemId = Items.ADDRESS, nullable = true)
                attribute(name = "mandatoryAddresses", itemId = Items.ADDRESS, nullable = false, multiple = true)
                attribute(name = "otherAddresses", itemId = Items.ADDRESS, nullable = true, multiple = true)
                attribute(name = "allKnownPinNumbers", type = BuiltInType.NUMBER, nullable = true, multiple = true)
                attribute(name = "allContactTypes", enumId = EnumTypes.CONTACT_TYPE, nullable = true, multiple = true)
                reference(name = "myReferenceToAddress", itemId = Items.ADDRESS, nullable = false, multiple = false)
                reference(name = "myReferenceToAddressNullable", itemId = Items.ADDRESS, nullable = true, multiple = false)
                reference(name = "myReferencesToAddresses", itemId = Items.ADDRESS, nullable = false, multiple = true)
                reference(name = "myReferencesToAddressesNullable", itemId = Items.ADDRESS, nullable = true, multiple = true)
            }

            item(itemId = Items.ADDRESS) {
                primaryKey(attributeName = "id")

                attribute(name = "id", type = BuiltInType.UUID)
                attribute(name = "street", type = BuiltInType.STRING, exampleDataCategory = ExampleDataCategory.STREET)
                attribute(name = "postalCode", type = BuiltInType.STRING, exampleDataCategory = ExampleDataCategory.POSTCODE)
                attribute(name = "town", type = BuiltInType.STRING, exampleDataCategory = ExampleDataCategory.CITY)
                attribute(name = "country", itemId = Items.COUNTRY)
            }

            item(itemId = Items.COUNTRY) {
                attribute(name = "countryIsoCode", type = BuiltInType.STRING, exampleDataCategory = ExampleDataCategory.COUNTRY_ISO)
                attribute(name = "countryName", type = BuiltInType.STRING, exampleDataCategory = ExampleDataCategory.COUNTRY_NAME)
            }
        }
    }

    private fun RootDsl.collectUiData() {
        schema {
            uiEntity(uiEntityName = "Contact", rootItemId = Items.CONTACT) {
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
                                    attribute(attributeName = "contactAddress")
                                    attribute(attributeName = "mandatoryAddresses")
                                    attribute(attributeName = "otherAddresses")
                                    attribute(attributeName = "allKnownPinNumbers")
                                    attribute(attributeName = "allContactTypes")
                                }
                            }
                            tab(tabName = "References") {
                                column {
                                    section(sectionName = "References to other items")
                                    attribute(attributeName = "myReferenceToAddress")
                                    attribute(attributeName = "myReferenceToAddressNullable")
                                    attribute(attributeName = "myReferencesToAddresses")
                                    attribute(attributeName = "myReferencesToAddressesNullable")
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
            uiEntity(uiEntityName = "Address", rootItemId = Items.ADDRESS) {
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
