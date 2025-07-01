package com.github.sky130.code.server

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import org.mozilla.geckoview.GeckoRuntime

@SuppressLint("StaticFieldLeak")
class MainApplication : Application() {
    companion object {
        private lateinit var _context: Context
        val context get() = _context
        val runtime by lazy { GeckoRuntime.create(context) }
        val isPermissionGranted
            get() =
                ContextCompat.checkSelfPermission(
                    context,
                    Termux.RUN_CODE_PERMISSION
                ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate() {
        super.onCreate()
        _context = this.applicationContext
        if (isPermissionGranted)
            Termux.launchCodeServer()
    }

}