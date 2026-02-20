package senegai.codegen.renderer.model

import org.codeblessing.templatetools.CaseUtil

data class NameCase(private val name: String) {
    val kebabCase: String = CaseUtil.camelToDashCase(name)
    val camelCase: String = CaseUtil.decapitalize(name)
    val pascalCase: String = CaseUtil.capitalize(name)
    val screamingSnakeCase: String = CaseUtil.camelToSnakeCase(name).uppercase() // TODO implement constants style

    fun isEqual(other: String): Boolean {
        return other == name
    }

}
