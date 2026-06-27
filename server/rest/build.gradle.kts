import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
    `maven-dependency-repository`
}

dependencyManagement {
    imports {
        mavenBom(SpringBootPlugin.BOM_COORDINATES)
    }
}

val directoryForGeneratedSource = "src/generated/kotlin"

kotlin {
    sourceSets["main"].kotlin.srcDir(directoryForGeneratedSource)
}

dependencies {
    implementation(project(":server:service"))

    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // Required so Jackson can (de)serialize the Kotlin data class WTOs from request bodies.
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.3")
}
