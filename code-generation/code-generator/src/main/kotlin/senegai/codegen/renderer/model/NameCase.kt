package senegai.codegen.renderer.model

import org.codeblessing.templatetools.CaseUtil

data class NameCase(val name: String) {
    val lowercaseDashCase: String = CaseUtil.camelToDashCase(name)
    val decapitalizedCamelCase: String = CaseUtil.decapitalize(name)
    val capitalizedCamelCase: String = CaseUtil.capitalize(name)

}
