plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-dependency-repository`
}

dependencies {
    implementation(libs.sourceamazing.schema.api)
    implementation(libs.sourceamazing.builder.api)
}
