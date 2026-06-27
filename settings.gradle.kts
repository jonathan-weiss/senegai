rootProject.name = "senegai"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val kotlinVersion = version("kotlin", "2.3.21")

            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef(kotlinVersion)
            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef(kotlinVersion)
            plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef(kotlinVersion)
            plugin("kotlin-spring", "org.jetbrains.kotlin.plugin.spring").versionRef(kotlinVersion)

            val springBootVersion = version("springBoot", "4.1.0")
            plugin("spring-boot", "org.springframework.boot").versionRef(springBootVersion)
            plugin("spring-dependency-management", "io.spring.dependency-management").version("1.1.7")

            val typicalTemplateVersion = version("typicalTemplate", "0.0.17")
            library("typicaltemplate-api", "org.codeblessing.typicaltemplate", "typical-template-api").versionRef(typicalTemplateVersion)
            library("typicaltemplate", "org.codeblessing.typicaltemplate", "typical-template").versionRef(typicalTemplateVersion)

            val sourceAmazingVersion = version("sourceAmazing", "4.0.0")
            library(
                "sourceamazing-schema-api",
                "org.codeblessing.sourceamazing",
                "sourceamazing-schema-api",
            ).versionRef(sourceAmazingVersion)
            library("sourceamazing-schema", "org.codeblessing.sourceamazing", "sourceamazing-schema").versionRef(sourceAmazingVersion)
            library(
                "sourceamazing-builder-api",
                "org.codeblessing.sourceamazing",
                "sourceamazing-builder-api",
            ).versionRef(sourceAmazingVersion)
            library("sourceamazing-builder", "org.codeblessing.sourceamazing", "sourceamazing-builder").versionRef(sourceAmazingVersion)
            library(
                "sourceamazing-xmlschema-api",
                "org.codeblessing.sourceamazing",
                "sourceamazing-xml-schema-api",
            ).versionRef(sourceAmazingVersion)
            library(
                "sourceamazing-xmlschema",
                "org.codeblessing.sourceamazing",
                "sourceamazing-xml-schema",
            ).versionRef(sourceAmazingVersion)
        }
    }
}

include("client")
include("server:app")
include("server:service")
include("server:example-data")
include("server:rest")
include("server:persistence")
include("code-generation:renderer-creator")
include("code-generation:code-generator")
include("code-generation:code-generator-data")
