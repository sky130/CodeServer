package com.github.sky130.code.server

import android.content.Intent
import com.github.sky130.code.server.MainApplication.Companion.context

object Termux {
    const val RUN_CODE_PERMISSION = "com.termux.permission.RUN_COMMAND"

    fun launchCodeServer(){
        context.startService(Intent {
            setClassName("com.termux", "com.termux.app.RunCommandService")
            setAction("com.termux.RUN_COMMAND")
            putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/usr/bin/bash")
            putExtra("com.termux.RUN_COMMAND_ARGUMENTS", arrayOf<String>("code-server"))
            putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/home")
            putExtra("com.termux.RUN_COMMAND_BACKGROUND", true)
            putExtra("com.termux.RUN_COMMAND_SESSION_ACTION", "0")
        })
    }

    private fun Intent(block: Intent.() -> Unit) = Intent().apply(block)
}