import com.starrybyte.hytale.template.tasks.HytaleServerDecompileTask
import com.starrybyte.hytale.template.tasks.RunServerTask
import com.starrybyte.hytale.template.Utils.getServerDir
import com.starrybyte.hytale.template.tasks.CopyJarToModsTask

plugins {
    kotlin("jvm") version "2.2.21"
    idea
}

group = "org.example"
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
