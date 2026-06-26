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

    implementation("org.springframework:spring-context")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
