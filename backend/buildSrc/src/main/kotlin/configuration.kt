object Versions {
    const val kotlin = "1.3.20"
    const val exposed = "0.11.2"
    const val postgresql = "42.2.5"
    const val junit = "5.3.2"
    const val jfrog = "4.9.0"
    const val ktor = "1.1.1"
    const val logback = "1.2.1"
    const val slf4j = "1.7.25"
}

object Config {
    const val artifactoryUrl = "http://artifactory/artifactory/gradle"
}

object Libs {
    const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    const val exposed = "org.jetbrains.exposed:spring-transaction:${Versions.exposed}"

    const val postgresql = "org.postgresql:postgresql:${Versions.postgresql}"
    const val junit_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val jfrog_plugin = "org.jfrog.buildinfo:build-info-extractor-gradle:${Versions.jfrog}"

    const val ktor_logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val ktor_netty =  "io.ktor:ktor-server-netty:${Versions.ktor}"
    const val ktor_server_core = "io.ktor:ktor-server-core:${Versions.ktor}"
    const val ktor_auth = "io.ktor:ktor-auth:${Versions.ktor}"
    const val ktor_auth_jwt = "io.ktor:ktor-auth-jwt:${Versions.ktor}"
    const val ktor_locations = "io.ktor:ktor-locations:${Versions.ktor}"
    const val ktor_test = "io.ktor:ktor-server-tests:${Versions.ktor}"
    const val ktor_gson = "io.ktor:ktor-gson:${Versions.ktor}"

    const val slf4j_api = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val slf4j_simple = "org.slf4j:slf4j-simple:${Versions.slf4j}"

}
