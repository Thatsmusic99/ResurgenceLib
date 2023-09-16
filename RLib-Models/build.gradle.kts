plugins {
    id("java")
}

group = "io.github.thatsmusic99"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("mysql:mysql-connector-java:8.0.33")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}