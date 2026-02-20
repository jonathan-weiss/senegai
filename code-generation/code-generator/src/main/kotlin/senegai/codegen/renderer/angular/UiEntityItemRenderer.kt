package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel
import senegai.codegen.renderer.model.ui.UiItemModel
import senegai.codegen.renderer.model.ui.entityform.UiEntityFormViewItemModel

interface UiEntityItemRenderer {
    fun renderTemplate(model: UiEntityFormViewItemModel): String
    fun filePath(model: UiEntityFormViewItemModel): String
}
