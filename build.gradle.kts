import com.starrybyte.hytale.template.HytaleServerDecompileTask
import com.starrybyte.hytale.template.RunServerTask
import com.starrybyte.hytale.template.getServerPath

plugins {
    kotlin("jvm") version "2.2.21"
    idea
}

group = "org.exampl"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    val decompiledDir = layout.buildDirectory.dir("tmp/server_decompiled").get().asFile
    compileOnly(files(getServerPath(project)))
    compileOnly(files(decompiledDir))
    println(decompiledDir.absolutePath)
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
