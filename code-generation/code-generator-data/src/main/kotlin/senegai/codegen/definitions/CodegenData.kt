package senegai.codegen.definitions

import senegai.codegen.schema.BuiltInType.NUMBER
import senegai.codegen.schema.BuiltInType.STRING
import senegai.codegen.builders.ItemId
import senegai.codegen.builders.ItemsBuilder

object CodegenData {

    fun collectItemData(itemsBuilder: ItemsBuilder) {
        println("Generating form data with $itemsBuilder")

        val contact = ItemId("Contact")
        itemsBuilder
            .createNewItem(contact) {
                attribute(name = "firstname", type = STRING)
                attribute(name = "nickname", type = STRING)
                attribute(name = "lastname", type = STRING)
                attribute(name = "age", type = NUMBER)
                attribute(name = "address", type = STRING)
                attribute(name = "zipCode", type = STRING)
                attribute(name = "city", type = STRING)
            }

        val employeeItem = ItemId("Employee")
        val addressItem = ItemId("Address")
        itemsBuilder
            .createNewItem(employeeItem) {
                attribute(name = "firstname", type = STRING)
                attribute(name = "nickname", type = STRING)
                attribute(name = "lastname", type = STRING)
                attribute(name = "address", itemId = addressItem) {
                    attribute(name = "street", type = STRING)
                    attribute(name = "postalCode", type = STRING)
                    attribute(name = "town", type = STRING)
                }
            }
    }
}
