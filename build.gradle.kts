plugins {
    id("java")
    kotlin("jvm") version "1.9.10"
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.cmdfw"
version = "1.0-SNAPSHOT"
val jdaV = "5.0.0-beta.15"
val lavaplayerVersion = "2.0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api("net.dv8tion:JDA:$jdaV")
    api("io.github.cdimascio:dotenv-java:3.0.0")
    api("dev.arbjerg:lavaplayer:$lavaplayerVersion")
    testImplementation(kotlin("test"))
}

kotlin {
    //jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}