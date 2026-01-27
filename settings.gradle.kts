rootProject.name = "senegai"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val kotlinVersion = version("kotlin", "2.2.0")

            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef(kotlinVersion)
            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef(kotlinVersion)
            plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef(kotlinVersion)

            val typicalTemplateVersion = version("typicalTemplate", "0.0.1")
            library("typicaltemplate-api", "org.codeblessing.typicaltemplate", "typical-template-api").versionRef(typicalTemplateVersion)
            library("typicaltemplate", "org.codeblessing.typicaltemplate", "typical-template").versionRef(typicalTemplateVersion)

            val templateToolsVersion = version("templateTools", "1.0.0-rc1")
            library("templatetools", "org.codeblessing.templatetools", "template-tools").versionRef(templateToolsVersion)

            val sourceAmazingVersion = version("sourceAmazing", "4.0.0")
            library("sourceamazing-schema-api", "org.codeblessing.sourceamazing", "sourceamazing-schema-api").versionRef(sourceAmazingVersion)
            library("sourceamazing-schema", "org.codeblessing.sourceamazing", "sourceamazing-schema").versionRef(sourceAmazingVersion)
            library("sourceamazing-builder-api", "org.codeblessing.sourceamazing", "sourceamazing-builder-api").versionRef(sourceAmazingVersion)
            library("sourceamazing-builder", "org.codeblessing.sourceamazing", "sourceamazing-builder").versionRef(sourceAmazingVersion)
            library("sourceamazing-xmlschema-api", "org.codeblessing.sourceamazing", "sourceamazing-xml-schema-api").versionRef(sourceAmazingVersion)
            library("sourceamazing-xmlschema", "org.codeblessing.sourceamazing", "sourceamazing-xml-schema").versionRef(sourceAmazingVersion)

        }
    }
}

include("client")
include("code-generation:renderer-creator")
include("code-generation:code-generator")
include("code-generation:code-generator-data")
