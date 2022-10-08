package com.example.plasticmandi.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.plasticmandi.AllOffers
import com.example.plasticmandi.MyOffers
import com.example.plasticmandi.OrderHistory
import com.example.plasticmandi.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val allOffers = AllOffers()
        val myOffers = MyOffers()
        val orderHistory = OrderHistory()

        setCurrentFragment(allOffers)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.allOffers -> setCurrentFragment(allOffers)
                R.id.myOffers -> setCurrentFragment(myOffers)
                R.id.orderHistory -> setCurrentFragment(orderHistory)
                R.id.chat -> setCurrentFragment(allOffers)
                else -> {}

            }
            true

        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }

}