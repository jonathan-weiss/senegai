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

val directoryForGeneratedTemplateRenderer = "src/tavnit-generated/kotlin"
val directoryForManualTemplateRenderer = "src/tavnit-manual/kotlin"

val angularClientProjectPath = project(":client").projectDir
val serverRestProjectPath = project(":server:rest").projectDir
val serverServiceProjectPath = project(":server:service").projectDir
val serverPersistenceProjectPath = project(":server:persistence").projectDir
val serverExampleDataProjectPath = project(":server:example-data").projectDir


val directoryForAngularGeneratedSource = angularClientProjectPath.resolve("src/app-generated")
val directoryForRestGeneratedSource = serverRestProjectPath.resolve("src/generated/kotlin")
val directoryForServiceGeneratedSource = serverServiceProjectPath.resolve("src/generated/kotlin")
val directoryForPersistenceGeneratedSource = serverPersistenceProjectPath.resolve("src/generated/kotlin")
val directoryForExampleDataGeneratedSource = serverExampleDataProjectPath.resolve("src/generated/kotlin")

kotlin {
    sourceSets["main"].kotlin.srcDir(directoryForGeneratedTemplateRenderer)
    sourceSets["main"].kotlin.srcDir(directoryForManualTemplateRenderer)
}

tasks.register<JavaExec>("codegen") {
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("senegai.codegen.CodeGenExecutorKt")

    args(
        directoryForAngularGeneratedSource,
        directoryForRestGeneratedSource,
        directoryForServiceGeneratedSource,
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
