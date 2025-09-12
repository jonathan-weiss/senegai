package senegai.codegen.manual

import senegai.codegen.manual.schema.ItemAttributeBuiltInType
import senegai.codegen.manual.schema.ItemAttributeCardinality
import senegai.codegen.manual.schema.ItemAttributeCardinality.EXACTLY_ONE
import senegai.codegen.manual.schema.ItemAttributeData
import senegai.codegen.manual.schema.ItemAttributeType
import senegai.codegen.manual.schema.ItemData
import senegai.codegen.manual.schema.ItemsData

object ManualCodegenData {

    fun collectCodegenData(): ItemsData {
        val contact = ItemData(
            itemName = "Contact",
            attributes = listOf(
                ItemAttributeData(
                    attributeName = "firstname",
                    cardinality = EXACTLY_ONE,
                    type = ItemAttributeBuiltInType.STRING,
                ),
                ItemAttributeData(
                    attributeName = "nickname",
                    cardinality = EXACTLY_ONE,
                    type = ItemAttributeBuiltInType.STRING,
                ),
                ItemAttributeData(
                    attributeName = "lastname",
                    cardinality = EXACTLY_ONE,
                    type = ItemAttributeBuiltInType.STRING,
                ),
            )
        )
        return ItemsData(listOf(contact))
    }
}
