plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation(project(":core"))
}

tasks.register<Exec>("buildNative") {
    workingDir = file("../native")
}

tasks.compileJava {
    options.headerOutputDirectory.set(file("../native/include"))
}