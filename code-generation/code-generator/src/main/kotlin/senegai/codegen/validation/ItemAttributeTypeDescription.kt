package senegai.codegen.validation

import senegai.model.schema.BuiltInType
import senegai.model.schema.EnumId
import senegai.model.schema.ItemAttributeType
import senegai.model.schema.ItemId

/** How the type of an item attribute is named in a validation message. */
internal val ItemAttributeType.description: String
    get() = when (this) {
        is BuiltInType -> "the built-in type $name"
        is EnumId -> "the enum type '$enumName'"
        is ItemId -> "the item '$itemName'"
    }
