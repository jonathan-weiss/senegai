package senegai.codegen.definitions

import senegai.codegen.builders.SchemaBuilder
import senegai.codegen.schema.BuiltInType.NUMBER
import senegai.codegen.schema.BuiltInType.STRING
import senegai.codegen.schema.ItemId

object CodegenData {

    fun collectItemData(schemaBuilder: SchemaBuilder) {
        println("Generating form data with $schemaBuilder")

        val contact = ItemId("Contact")
        schemaBuilder
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
        schemaBuilder
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
