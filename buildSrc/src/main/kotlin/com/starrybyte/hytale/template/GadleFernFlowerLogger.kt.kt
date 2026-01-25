package com.starrybyte.hytale.template

import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger

class GradleFernflowerLogger : IFernflowerLogger() {

    override fun writeMessage(message: String, severity: Severity) {
        when (severity) {
            Severity.ERROR -> println("VINEFLOWER ERROR: $message")
            Severity.WARN  -> println("VINEFLOWER WARN:  $message")
            else           -> println("VINEFLOWER INFO:  $message")
        }
    }

    override fun writeMessage(
        message: String?,
        severity: Severity?,
        t: Throwable?
    ) {
        when (severity) {
            Severity.ERROR -> println("VINEFLOWER ERROR: $message")
            Severity.WARN  -> println("VINEFLOWER WARN:  $message")
            else           -> println("VINEFLOWER INFO:  $message")
        }
    }

    override fun writeMessage(message: String, t: Throwable) {
        println("VINEFLOWER ERROR: $message")
        t.printStackTrace()
    }
}