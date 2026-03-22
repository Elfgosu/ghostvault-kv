plugins {
    java
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation(project(":jni"))
}

tasks.test {
    useJUnitPlatform()
}