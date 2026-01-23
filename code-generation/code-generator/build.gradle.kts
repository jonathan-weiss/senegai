plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-dependency-repository`
}

dependencies {
    implementation(project(":code-generation:code-generator-data"))
    implementation(libs.sourceamazing.schema.api)
    runtimeOnly(libs.sourceamazing.schema)
    implementation(libs.sourceamazing.builder.api)
    runtimeOnly(libs.sourceamazing.builder)
    runtimeOnly(libs.kotlin.reflect)
    implementation(libs.templatetools)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

val directoryForGeneratedTemplateRenderer = "src/typicaltemplate-generated/kotlin"
val pathToAngularProject = project(":client").projectDir
val directoryForAngularGeneratedSource = pathToAngularProject.resolve("src/app-generated")

kotlin {
    sourceSets["main"].kotlin.srcDir(directoryForGeneratedTemplateRenderer)
}

tasks.register<JavaExec>("codegen") {
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("senegai.codegen.CodeGenExecutorKt")

    args(
        directoryForAngularGeneratedSource,
    )
}
