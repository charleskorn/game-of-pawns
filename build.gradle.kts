import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    val spekVersion = "2.0.0"

    // Override the version of kotlin-reflect used by Spek.
    testImplementation(kotlin("reflect", "1.3.21"))
    testImplementation(group = "org.spekframework.spek2", name = "spek-dsl-jvm", version = spekVersion)
    testImplementation(group = "ch.tutteli.atrium", name = "atrium-cc-en_GB-robstoll", version = "0.8.0-alpha")

    testRuntimeOnly(group = "org.spekframework.spek2", name = "spek-runner-junit5", version = spekVersion)
}

application {
    mainClassName = "com.charleskorn.shokunin.gameofpawns.AppKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }

    testLogging {
        events("failed")
        events("skipped")
        events("standard_out")
        events("standard_error")

        showExceptions = true
        showStackTraces = true
        showCauses = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}
