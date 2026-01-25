package com.starrybyte.hytale.template
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.jetbrains.java.decompiler.main.Fernflower
import java.io.File

abstract class  HytaleServerDecompileTask : DefaultTask() {

    init {
        group = "hytale"
        description = "Decompiles Hytale Server"
    }
    @TaskAction
    fun run() {
        val outputDir =project.layout.buildDirectory.dir("/tmp/server_decompiled")
        val fernflower = Fernflower(
            DirectoryResultSaver(outputDir.get().asFile),
            emptyMap(),
            GradleFernflowerLogger()

        )
        fernflower.addSource(getServerPath(project))
        fernflower.decompileContext()
    }
}
