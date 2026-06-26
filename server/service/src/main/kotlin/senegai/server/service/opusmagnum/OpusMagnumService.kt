/* @tt{{{

    @move-comment-backward
    @template-renderer [
        templateRendererClassName="EntityServiceRenderer"
        templateRendererPackageName="senegai.codegen.renderer.be"
        templateRendererInterfaceName="BeEntityRenderer"
        templateRendererInterfacePackageName="senegai.codegen.renderer.be"
    ] [
        modelClassName="BeEntityModel"
        modelPackageName="senegai.codegen.renderer.model.be"
        modelName="model"
    ]

    @replace-value-by-expression
        [ searchValue="OpusMagnum" replaceByExpression="model.entityName.pascalCase" ]
        [ searchValue="opusMagnum" replaceByExpression="model.entityName.camelCase" ]
        [ searchValue="SilvaOptionum" replaceByExpression="model.entityRootItem.itemName.pascalCase" ]
        [ searchValue="silvaOptionum" replaceByExpression="model.entityRootItem.itemName.camelCase" ]
        [ searchValue="opusmagnum" replaceByExpression="model.entityName.lowerCase" ]

    @replace-value-by-expression
        [ searchValue="indexUnicus" replaceByExpression="model.idAttribute.attributeName.camelCase" ]

    @modify-provided-filename-by-replacements

}}}@ */
package senegai.server.service.opusmagnum

import org.springframework.stereotype.Service
import senegai.server.service.bo.SilvaOptionumBO
import java.util.UUID

/**
 * Business service of the OpusMagnum business context. It is called by the REST layer
 * with [SilvaOptionumBO] business objects (never WTOs) and delegates persistence to the
 * [OpusMagnumRepository] port.
 *
 * Every operation works on the whole [SilvaOptionumBO] aggregate as a single unit.
 */
@Service
class OpusMagnumService(
    private val opusMagnumRepository: OpusMagnumRepository,
) {

    fun getSilvaOptionumList(): List<SilvaOptionumBO> = opusMagnumRepository.findAll()

    fun getSilvaOptionumById(indexUnicus: String): SilvaOptionumBO? =
        opusMagnumRepository.findById(indexUnicus)

    fun createSilvaOptionum(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO {
        val toCreate = silvaOptionum.copy(indexUnicus = UUID.randomUUID().toString())
        return opusMagnumRepository.save(toCreate)
    }

    fun updateSilvaOptionum(silvaOptionum: SilvaOptionumBO): SilvaOptionumBO =
        opusMagnumRepository.save(silvaOptionum)

    fun deleteSilvaOptionum(indexUnicus: String) = opusMagnumRepository.deleteById(indexUnicus)
}
