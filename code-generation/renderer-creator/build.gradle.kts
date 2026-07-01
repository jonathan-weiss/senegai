plugins {
    kotlin("jvm")
    `maven-dependency-repository`
}

dependencies {
    implementation(libs.tavnit.api)
    runtimeOnly(libs.tavnit)
}

val targetDirectoryForTemplateRenderer = project(":code-generation:code-generator").projectDir.resolve("src/tavnit-generated/kotlin")

tasks.register<JavaExec>("createTavnitRenderers") {
    val angularClientProjectPath = project(":client").projectDir
    val serverServiceProjectPath = project(":server:service").projectDir
    val serverRestProjectPath = project(":server:rest").projectDir
    val serverPersistenceProjectPath = project(":server:persistence").projectDir
    val serverExampleDataProjectPath = project(":server:example-data").projectDir

    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("senegai.codegen.renderercreator.TavnitRendererCreatorKt")

    args(
        targetDirectoryForTemplateRenderer.absolutePath,
        angularClientProjectPath.resolve("src/app").absolutePath,
        serverRestProjectPath.resolve("src/main/kotlin").absolutePath,
        serverServiceProjectPath.resolve("src/main/kotlin").absolutePath,
        serverPersistenceProjectPath.resolve("src/main/kotlin").absolutePath,
        serverExampleDataProjectPath.resolve("src/main/kotlin").absolutePath,
    )

    dependsOn("cleanRenderers")
}

tasks.register<Delete>("cleanRenderers") {
    delete(targetDirectoryForTemplateRenderer)
}
