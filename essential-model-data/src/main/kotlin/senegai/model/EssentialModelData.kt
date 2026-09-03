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
                enumValue(name = "Single")
                enumValue(name = "Married")
                enumValue(name = "Divorced")
                enumValue(name = "Widowed")
            }

            enumType(enumId = EnumTypes.CONTACT_TYPE) {
                enumValue(name = "Email")
                enumValue(name = "Phone")
                enumValue(name = "Sms")
                enumValue(name = "Post")
            }

            item(itemId = Items.CONTACT) {
                attribute(name = "ContactId").primaryKey()
                attribute(name = "ContactSalutation").enumType(enumId = EnumTypes.SALUTATION)
                attribute(name = "Firstname").string(exampleDataCategory = ExampleDataCategory.FIRSTNAME)
                attribute(name = "Nickname").string(exampleDataCategory = ExampleDataCategory.FIRSTNAME).options(nullable = true)
                attribute(name = "Lastname").string(exampleDataCategory = ExampleDataCategory.LASTNAME).options(customValidation = true)
                attribute(name = "AllKnownNicknames").string(exampleDataCategory = ExampleDataCategory.FIRSTNAME).options(multiple = true)
                attribute(name = "Age").number(exampleDataCategory = ExampleDataCategory.AGE).options(customValidation = true)
                attribute(name = "Vegetarian").boolean()
                attribute(name = "HomeAddress").nestedItem(itemId = Items.ADDRESS).options(nullable = false)
                attribute(name = "ContactAddress").nestedItem(itemId = Items.ADDRESS).options(nullable = true)
                attribute(name = "MandatoryAddresses").nestedItem(itemId = Items.ADDRESS).options(nullable = false, multiple = true)
                attribute(name = "OtherAddresses").nestedItem(itemId = Items.ADDRESS).options(nullable = true, multiple = true)
                attribute(name = "AllKnownPinNumbers").number().options(nullable = true, multiple = true)
                attribute(name = "AllContactTypes").enumType(enumId = EnumTypes.CONTACT_TYPE).options(nullable = true, multiple = true)
                attribute(name = "MyReferenceToAddress").reference(itemId = Items.ADDRESS).options(nullable = false, multiple = false)
                attribute(name = "MyReferenceToAddressNullable").reference(itemId = Items.ADDRESS).options(nullable = true, multiple = false)
                attribute(name = "MyReferencesToAddresses").reference(itemId = Items.ADDRESS).options(nullable = false, multiple = true)
                attribute(name = "MyReferencesToAddressesNullable").reference(itemId = Items.ADDRESS).options(nullable = true, multiple = true)
            }

            item(itemId = Items.ADDRESS) {
                attribute(name = "Id").primaryKey()
                attribute(name = "Street").string(exampleDataCategory = ExampleDataCategory.STREET)
                attribute(name = "PostalCode").string(exampleDataCategory = ExampleDataCategory.POSTCODE)
                attribute(name = "Town").string(exampleDataCategory = ExampleDataCategory.CITY)
                attribute(name = "Country").nestedItem(itemId = Items.COUNTRY)
            }

            item(itemId = Items.COUNTRY) {
                attribute(name = "CountryIsoCode").string(exampleDataCategory = ExampleDataCategory.COUNTRY_ISO)
                attribute(name = "CountryName").string(exampleDataCategory = ExampleDataCategory.COUNTRY_NAME)
            }
        }
    }

    private fun RootDsl.collectUiData() {
        schema {
            uiItem(itemId = Items.CONTACT) {
                displayAttributes {
                    attribute(attributeName = "Firstname")
                    attribute(attributeName = "Lastname")
                }
            }
            uiItem(itemId = Items.ADDRESS) {
                displayAttributes {
                    attribute(attributeName = "Street")
                    attribute(attributeName = "Town")
                }
                configureEditorForNestedItemDefault {
                    column {
                        attribute(attributeName = "Id")
                        attribute(attributeName = "Street")
                        attribute(attributeName = "PostalCode")
                        attribute(attributeName = "Town")
                        attribute(attributeName = "Country")
                    }
                }
            }
            uiItem(itemId = Items.COUNTRY) {
                displayAttributes {
                    attribute(attributeName = "CountryName")
                }
                configureEditorForNestedItemDefault {
                    column {
                        attribute(attributeName = "CountryIsoCode")
                        attribute(attributeName = "CountryName")
                    }
                }
            }

            uiEntity(uiEntityName = "Contact", rootItemId = Items.CONTACT) {
                views {
                    searchResult {
                        attribute(attributeName = "ContactId")
                        attribute(attributeName = "ContactSalutation")
                        attribute(attributeName = "Firstname")
                        attribute(attributeName = "Nickname")
                        attribute(attributeName = "AllKnownNicknames")
                        attribute(attributeName = "Lastname")
                        attribute(attributeName = "Age")
                        attribute(attributeName = "Vegetarian")
                        attribute(attributeName = "AllKnownPinNumbers")
                        attribute(attributeName = "AllContactTypes")
                    }
                    editor {
                        configureEditorForMainItem {
                            tab(tabTranslationKey = "tab.common") {
                                column {
                                    attribute(attributeName = "ContactId")
                                    attribute(attributeName = "ContactSalutation")
                                    section(sectionTranslationKey = "section.names")
                                    text(textTranslationKey = "text.loremIpsum")
                                    attribute(attributeName = "Firstname")
                                    text(textTranslationKey = "text.firstThingsFirst")
                                    attribute(attributeName = "Nickname")
                                    attribute(attributeName = "AllKnownNicknames")
                                    text(textTranslationKey = "text.nickname")
                                    attribute(attributeName = "Lastname")
                                    text(textTranslationKey = "text.lastname")
                                    section(sectionTranslationKey = "section.addresses")
                                    attribute(attributeName = "HomeAddress")
                                    attribute(attributeName = "ContactAddress")
                                    attribute(attributeName = "MandatoryAddresses")
                                    attribute(attributeName = "OtherAddresses")
                                    attribute(attributeName = "AllKnownPinNumbers")
                                    attribute(attributeName = "AllContactTypes")
                                    attribute(attributeName = "Age")
                                    attribute(attributeName = "Vegetarian")
                                }
                            }
                            tab(tabTranslationKey = "tab.references") {
                                column {
                                    section(sectionTranslationKey = "section.referencesToOtherItems")
                                    attribute(attributeName = "MyReferenceToAddress")
                                    attribute(attributeName = "MyReferenceToAddressNullable")
                                    attribute(attributeName = "MyReferencesToAddresses")
                                    attribute(attributeName = "MyReferencesToAddressesNullable")
                                }
                            }
                            tab(tabTranslationKey = "tab.miscellaneous") {
                                column {
                                    section(sectionTranslationKey = "section.misc")
                                    text(textTranslationKey = "text.longText")
                                }
                                column {
                                    section(sectionTranslationKey = "section.legend")
                                    text(textTranslationKey = "text.multilineText")
                                    attribute(attributeName = "Age")
                                    attribute(attributeName = "Vegetarian")
                                }
                            }
                        }
                    }
                }
            }
            uiEntity(uiEntityName = "Address", rootItemId = Items.ADDRESS) {
                views {
                    searchResult {
                        attribute(attributeName = "Street")
                        attribute(attributeName = "PostalCode")
                        attribute(attributeName = "Town")
                    }
                    editor {
                        configureEditorForMainItem {
                            tab(tabTranslationKey = "tab.address") {
                                column {
                                    attribute(attributeName = "Id")
                                    section(sectionTranslationKey = "section.address")
                                    attribute(attributeName = "Street")
                                    attribute(attributeName = "PostalCode")
                                    attribute(attributeName = "Town")
                                    attribute(attributeName = "Country")
                                }
                            }
                        }
                        configureEditorForNestedItem(itemId = Items.ADDRESS) {
                            column {
                                attribute(attributeName = "Id")
                                attribute(attributeName = "Street")
                                attribute(attributeName = "PostalCode")
                                attribute(attributeName = "Town")
                                attribute(attributeName = "Country")
                            }
                        }
                    }
                }
            }
        }
    }
}
