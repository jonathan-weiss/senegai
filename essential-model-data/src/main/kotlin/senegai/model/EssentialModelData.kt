package senegai.model

import senegai.model.builders.RootDsl
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
                attribute(name = "contactId").primaryKey()
                attribute(name = "contactSalutation").enumType(enumId = EnumTypes.SALUTATION)
                attribute(name = "firstname").string(exampleDataCategory = ExampleDataCategory.FIRSTNAME)
                attribute(name = "nickname").string(exampleDataCategory = ExampleDataCategory.FIRSTNAME).options(nullable = true)
                attribute(name = "lastname").string(exampleDataCategory = ExampleDataCategory.LASTNAME).options(customValidation = true)
                attribute(name = "allKnownNicknames").string(exampleDataCategory = ExampleDataCategory.FIRSTNAME).options(multiple = true)
                attribute(name = "age").number(exampleDataCategory = ExampleDataCategory.AGE).options(customValidation = true)
                attribute(name = "vegetarian").boolean()
                attribute(name = "homeAddress").nestedItem(itemId = Items.ADDRESS).options(nullable = false)
                attribute(name = "contactAddress").nestedItem(itemId = Items.ADDRESS).options(nullable = true)
                attribute(name = "mandatoryAddresses").nestedItem(itemId = Items.ADDRESS).options(nullable = false, multiple = true)
                attribute(name = "otherAddresses").nestedItem(itemId = Items.ADDRESS).options(nullable = true, multiple = true)
                attribute(name = "allKnownPinNumbers").number().options(nullable = true, multiple = true)
                attribute(name = "allContactTypes").enumType(enumId = EnumTypes.CONTACT_TYPE).options(nullable = true, multiple = true)
                attribute(name = "myReferenceToAddress").reference(itemId = Items.ADDRESS).options(nullable = false, multiple = false)
                attribute(name = "myReferenceToAddressNullable").reference(itemId = Items.ADDRESS).options(nullable = true, multiple = false)
                attribute(name = "myReferencesToAddresses").reference(itemId = Items.ADDRESS).options(nullable = false, multiple = true)
                attribute(name = "myReferencesToAddressesNullable").reference(itemId = Items.ADDRESS).options(nullable = true, multiple = true)
            }

            item(itemId = Items.ADDRESS) {
                attribute(name = "id").primaryKey()
                attribute(name = "street").string(exampleDataCategory = ExampleDataCategory.STREET)
                attribute(name = "postalCode").string(exampleDataCategory = ExampleDataCategory.POSTCODE)
                attribute(name = "town").string(exampleDataCategory = ExampleDataCategory.CITY)
                attribute(name = "country").nestedItem(itemId = Items.COUNTRY)
            }

            item(itemId = Items.COUNTRY) {
                attribute(name = "countryIsoCode").string(exampleDataCategory = ExampleDataCategory.COUNTRY_ISO)
                attribute(name = "countryName").string(exampleDataCategory = ExampleDataCategory.COUNTRY_NAME)
            }
        }
    }

    private fun RootDsl.collectUiData() {
        schema {
            uiEntity(uiEntityName = "Contact", rootItemId = Items.CONTACT) {
                views {
                    editor {
                        configureEditorForMainItem {
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
                        configureEditorForNestedItem(itemId = Items.ADDRESS) {
                            column {
                                attribute(attributeName = "id")
                                attribute(attributeName = "street")
                                attribute(attributeName = "postalCode")
                                attribute(attributeName = "town")
                                attribute(attributeName = "country")
                            }
                        }
                        configureEditorForNestedItem(itemId = Items.COUNTRY) {
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
                        configureEditorForMainItem {
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
                        configureEditorForNestedItem(itemId = Items.ADDRESS) {
                            column {
                                attribute(attributeName = "id")
                                attribute(attributeName = "street")
                                attribute(attributeName = "postalCode")
                                attribute(attributeName = "town")
                                attribute(attributeName = "country")
                            }
                        }
                        configureEditorForNestedItem(itemId = Items.COUNTRY) {
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
