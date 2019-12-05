package com.example.android.eshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

const val SPLASH_TIME = 3000L // 3 seconds

class SplashScreen : AppCompatActivity() {

    private lateinit var myHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        myHandler = Handler()
        myHandler.postDelayed({
            goToMainActivity()
        }, SPLASH_TIME)
    }

    private fun goToMainActivity() {
        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }
}
