import xyz.starrybyte.hytale.assist.tasks.HytaleServerDecompileTask
import xyz.starrybyte.hytale.assist.tasks.RunServerTask
import xyz.starrybyte.hytale.assist.Utils.getServerDir
import xyz.starrybyte.hytale.assist.tasks.CopyJarToModsTask

plugins {
    kotlin("jvm") version "2.2.21"
    idea
}

group = "com.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly(files(getServerDir(project)))
}


kotlin {
    jvmToolchain(23)
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<HytaleServerDecompileTask>("decompile") {
}


tasks.register<RunServerTask>("runServer") {
    dependsOn("copyPluginToModsFolder")
}

tasks.register<CopyJarToModsTask>("copyPluginToModsFolder") {
    dependsOn(tasks.named("jar"))
}
