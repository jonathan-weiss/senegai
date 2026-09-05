package senegai.codegen.renderer

import senegai.codegen.renderer.db.DbItemRenderer
import senegai.codegen.renderer.model.db.DbModel
import senegai.codegen.renderer.model.db.DbTableModel
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

object DatabaseRendering {

    fun renderDatabaseFiles(pathToGeneratedDatabaseMigrationScripts: Path, dbModel: DbModel) {
        val worker = RenderingWorker(
            pathToGeneratedDatabaseMigrationScripts = pathToGeneratedDatabaseMigrationScripts,
        )
        worker.renderDatabaseFiles(dbModel)

    }

    private data class RenderingWorker(
        val pathToGeneratedDatabaseMigrationScripts: Path,

        ) {

        fun renderDatabaseFiles(dbModel: DbModel) {
            dbModel.tables.forEach { dbTableModel ->
                renderItemWithPrimaryKey(dbTableModel)
            }
        }

        private fun renderItemWithPrimaryKey(dbTableModel: DbTableModel) {
            val itemRenderers: List<Pair<DbItemRenderer, Path>> = listOf(
                // TODO add all DB renderers for SQL script here
            )

            renderAll(itemRenderers, dbTableModel)
        }

        private fun renderAll(renderers: List<Pair<DbItemRenderer, Path>>, dbTableModel: DbTableModel) {
            renderers.forEach { (renderer, basePath) ->
                writeFile(
                    filePath = basePath.resolve(renderer.filePath(dbTableModel)),
                    content = renderer.renderTemplate(dbTableModel),
                )
            }
        }

        private fun writeFile(filePath: Path, content: String) {
            require(!filePath.isDirectory()) { "$filePath is a directory" }
            // createDirectories() is not reliably a no-op for an existing directory on every
            // file system, and several files share one directory, so check first.
            val parent = filePath.parent
            if (!parent.isDirectory()) {
                parent.createDirectories()
            }
            filePath.writeText(content)
        }
    }
}
