plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.register<Exec>("buildNative") {
    workingDir = file("../native")
    commandLine("nmake")
}

tasks.named("build") {
    dependsOn("buildNative")
}