package com.github.sky130.code.server

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoSession.ContentDelegate
import org.mozilla.geckoview.GeckoSession.ProgressDelegate
import com.github.sky130.code.server.MainApplication.Companion.runtime
import com.github.sky130.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ProgressDelegate {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater, null, false) }
    val geckoView get() = binding.geckoView
    val session by lazy {
        GeckoSession().apply {
            setContentDelegate(object : ContentDelegate {})
            progressDelegate = this@MainActivity
            open(runtime)
            geckoView.setSession(this)
        }
    }
    val visibility by lazy { binding.geckoView.visibility = View.VISIBLE }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFullscreen()
        launch()
    }

    override fun onPageStop(session: GeckoSession, success: Boolean) {
        if (success) lifecycleScope.launch(Dispatchers.Main) {
            delay(5000L)
            visibility
        }
        else {
            Toast.makeText(this, "Termux未启动CodeServer, 正在重新加载", Toast.LENGTH_SHORT).show()
            launch()
        }

    }

    fun launch() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000L)
            loadCodeServerPage()
        }
    }

    fun loadCodeServerPage() {
        loadUri("http://127.0.0.1:20000")
    }

    fun loadUri(uri: String) {
        session.loadUri(uri)
    }

    fun setFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.insetsController?.let { controller ->
            controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}