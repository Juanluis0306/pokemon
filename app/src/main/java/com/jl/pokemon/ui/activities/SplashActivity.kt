package com.jl.pokemon.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import com.jl.pokemon.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController

            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        timerSplash()
    }

    private fun timerSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (validateSesion()) {
                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                this@SplashActivity.startActivity(mainIntent)
                finish()
            } else {
                val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
                this@SplashActivity.startActivity(mainIntent)
                finish()
            }
        }, 3000)
    }

    private fun validateSesion(): Boolean {
        val prefs = getSharedPreferences(getString(R.string.save_session_data), MODE_PRIVATE)
        return prefs.getBoolean(getString(R.string.save_session), false)
    }
}