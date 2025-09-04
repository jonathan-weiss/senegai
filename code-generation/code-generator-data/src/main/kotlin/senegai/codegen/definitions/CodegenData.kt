package senegai.codegen.definitions

import senegai.codegen.schema.CodegenSchema.BuiltinType.STRING
import senegai.codegen.schema.ItemId
import senegai.codegen.schema.ItemsBuilder

object CodegenData {

    fun collectItemData(itemsBuilder: ItemsBuilder) {
        println("Generating form data with $itemsBuilder")

        val personItem = ItemId("Person")
        itemsBuilder
            .createNewItem(personItem) {
                attribute(attributeName = "firstname", type = STRING)
                attribute(attributeName = "nickname", type = STRING)
                attribute(attributeName = "lastname", type = STRING)
            }

        itemsBuilder
            .createNewItem("Employee") {
                attribute(attributeName = "firstname", type = STRING)
                attribute(attributeName = "nickname", type = STRING)
                attribute(attributeName = "lastname", type = STRING)
            }
    }
}
