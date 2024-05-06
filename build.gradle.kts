plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.thatsmusic99"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    publish {
        dependsOn(project("RLib-AddonCore").tasks.publish)
        dependsOn(project("RLib-Cloud").tasks.publish)
        dependsOn(project("RLib-Core").tasks.publish)
        dependsOn(project("RLib-GUI").tasks.publish)
        dependsOn(project("RLib-Locale").tasks.publish)
        dependsOn(project("RLib-Models").tasks.publish)
        dependsOn(project("RLib-Spectating").tasks.publish)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "bs-repo"
            url = uri("https://repo.bsdevelopment.org/releases")
            credentials {
                username = System.getenv("REPO_NAME")
                password = System.getenv("REPO_PASS")
            }
        }
    }
}