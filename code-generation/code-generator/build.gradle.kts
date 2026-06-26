plugins {
    kotlin("jvm")
    `maven-dependency-repository`
}

dependencies {
    implementation(project(":code-generation:code-generator-data"))
    implementation(libs.sourceamazing.schema.api)
    runtimeOnly(libs.sourceamazing.schema)
    implementation(libs.sourceamazing.builder.api)
    runtimeOnly(libs.sourceamazing.builder)
    runtimeOnly(libs.kotlin.reflect)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

val directoryForGeneratedTemplateRenderer = "src/typicaltemplate-generated/kotlin"
val directoryForManualTemplateRenderer = "src/typicaltemplate-manual/kotlin"

val directoryForAngularGeneratedSource = project(":client").projectDir.resolve("src/app-generated")
val directoryForExampleDataGeneratedSource = project(":server:example-data").projectDir.resolve("src/generated/kotlin")
val directoryForPersistenceGeneratedSource = project(":server:persistence").projectDir.resolve("src/generated/kotlin")
val directoryForRestGeneratedSource = project(":server:rest").projectDir.resolve("src/generated/kotlin")
val directoryForServiceGeneratedSource = project(":server:service").projectDir.resolve("src/generated/kotlin")

kotlin {
    sourceSets["main"].kotlin.srcDir(directoryForGeneratedTemplateRenderer)
    sourceSets["main"].kotlin.srcDir(directoryForManualTemplateRenderer)
}

tasks.register<JavaExec>("codegen") {
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("senegai.codegen.CodeGenExecutorKt")

    args(
        directoryForAngularGeneratedSource,
        directoryForServiceGeneratedSource,
        directoryForRestGeneratedSource,
        directoryForPersistenceGeneratedSource,
        directoryForExampleDataGeneratedSource,
    )

    dependsOn("cleanCodegen")
}

tasks.register<Delete>("cleanCodegen") {
    delete(
        directoryForAngularGeneratedSource,
        directoryForExampleDataGeneratedSource,
        directoryForPersistenceGeneratedSource,
        directoryForRestGeneratedSource,
        directoryForServiceGeneratedSource,
    )
}
