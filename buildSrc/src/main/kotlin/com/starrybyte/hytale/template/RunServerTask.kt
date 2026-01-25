package com.starrybyte.hytale.template
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.JavaExec
import java.io.File

abstract class RunServerTask : JavaExec() {

    init {
        group = "hytale"
        description = "Runs the Hytale server"
        standardInput = System.`in`
    }

    @TaskAction
    override fun exec() {
        val modsPath = getModsPath(project)

        val jarFile = getServerPath(project)
        // Run exactly like: java -jar hytale-server.jar
        workingDir = jarFile.parentFile
        classpath = project.files(jarFile)
        mainClass.set("-jar")
        args(jarFile.absolutePath)
        args("--assets", "../Assets.zip")  // <-- here
        // JVM args from gradle.properties
        val jvmArgsString = project.findProperty("serverJvmArgs")?.toString()
            ?: "-Xms1G -Xmx4G -Dfile.encoding=UTF-8"
        jvmArgsString
            .split("\\s+".toRegex())
            .let { jvmArgs(it) }

        super.exec()
    }
    // Detect Hytale installation directory automatically (cross-platform)

}
