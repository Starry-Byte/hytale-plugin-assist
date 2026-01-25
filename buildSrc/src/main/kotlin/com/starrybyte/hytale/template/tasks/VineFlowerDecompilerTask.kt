package com.starrybyte.hytale.template.tasks
import com.starrybyte.hytale.template.Utils.getServerDir
import com.starrybyte.hytale.template.decompiler.DirectoryResultSaver
import com.starrybyte.hytale.template.decompiler.logger.GradleFernflowerLogger
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.jetbrains.java.decompiler.main.Fernflower

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
        fernflower.addSource(getServerDir(project))
        fernflower.decompileContext()
    }
}
