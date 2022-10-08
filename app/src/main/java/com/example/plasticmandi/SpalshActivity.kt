package com.example.plasticmandi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.plasticmandi.dashboard.DashboardActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT

        onGetStaredButtonClick()
    }

    private fun onGetStaredButtonClick() {
        val getStarted = findViewById<Button>(R.id.getStarted)
        getStarted.setOnClickListener {
            val intent = Intent(this,DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}