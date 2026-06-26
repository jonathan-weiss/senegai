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

dependencies {
    implementation(project(":server:service"))

    // Only spring-context for the @Repository stereotype / DI.
    // No persistence framework on purpose — to be added later.
    implementation("org.springframework:spring-context")
}
