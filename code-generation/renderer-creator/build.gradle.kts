plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-dependency-repository`
}

dependencies {
    implementation(libs.typicaltemplate.api)
    runtimeOnly(libs.typicaltemplate)
}

tasks.register<JavaExec>("createTypicalTemplateRenderers") {
    val angularClientProjectPath = project(":client").projectDir
    val codeGenerationProjectPath = project(":code-generation:code-generator").projectDir
    val targetDirectoryForTemplateRenderer = codeGenerationProjectPath.resolve("src/typicaltemplate-generated/kotlin")

    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("senegai.codegen.renderercreator.TypicalTemplateRendererCreatorKt")

    args(
        angularClientProjectPath.resolve("src/app").absolutePath, // First argument: Path to the directory within the angular files with tt comments are searched
        targetDirectoryForTemplateRenderer.absolutePath  // Second argument: Path to the base directory where the template renderers are written to
    )
}
