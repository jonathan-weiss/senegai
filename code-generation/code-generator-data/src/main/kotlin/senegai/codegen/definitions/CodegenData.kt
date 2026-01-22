package senegai.codegen.definitions

import senegai.codegen.builders.RootDsl
import senegai.codegen.schema.BuiltInType.NUMBER
import senegai.codegen.schema.BuiltInType.STRING
import senegai.codegen.schema.EnumId
import senegai.codegen.schema.ItemId

object CodegenData {
    enum class Items(override val itemName: String): ItemId {
        CONTACT(itemName = "Contact"),
        EMPLOYEE(itemName ="Employee"),
        ADDRESS(itemName ="Address"),
        ;
    }

    enum class EnumTypes(override val enumName: String): EnumId {
        GENDER(enumName = "Gender"),
        ;
    }

    fun RootDsl.collectItemData() {
        schema {
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

            item(itemId = Items.EMPLOYEE) {
                attribute(name = "firstname", type = STRING)
                attribute(name = "nickname", type = STRING)
                attribute(name = "lastname", type = STRING)
            }

            item(itemId = Items.ADDRESS) {
                attribute(name = "street", type = STRING)
                attribute(name = "postalCode", type = STRING)
                attribute(name = "town", type = STRING)
            }
        }
    }
}
