plugins {
    id("java")
}

group = "io.github.thatsmusic99"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    compileOnly("com.comphenix.protocol:ProtocolLib:4.7.0")
    compileOnly("org.jetbrains:annotations:24.0.1")
    implementation(project(":RLib-Core"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}