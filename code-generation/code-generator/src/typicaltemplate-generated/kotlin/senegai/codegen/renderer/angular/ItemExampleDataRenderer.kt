/*
 * This file is generated using typical-template.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiItemModel

/**
 * Generate the content for the template `ItemExampleDataRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `opus-magnum-example-data.ts`
 * - path: `opus-magnum/opus-magnum-example-data.ts`
 */
object ItemExampleDataRenderer {

    fun renderTemplate(model: UiItemModel): String {
        return """
          |        {
          |            ${ model.attributes.joinToString("") { attribute ->  """            ${ if(attribute.isBuildInTypeOf(senegai.codegen.schema.BuiltInType.STRING)) { """            ${attribute.attributeName.camelCase}: 'example',
                  |            
          """ } else { """
          """ } }            ${ if(attribute.isBuildInTypeOf(senegai.codegen.schema.BuiltInType.BOOLEAN)) { """            campusBivalens: false,
                  |            
          """ } else { """
          """ } }                        
          """ } }        }
          |        
        """.trimMargin(marginPrefix = "|")
    }

    fun filePath(model: UiItemModel): String {
      return "opus-magnum/opus-magnum-example-data.ts"
    }
}