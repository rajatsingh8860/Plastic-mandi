package com.example.plasticmandi

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.example.plasticmandi.`interface`.AuthInterface
import com.example.plasticmandi.dashboard.DashboardActivity
import com.example.plasticmandi.request.LoginRequest
import kotlinx.android.synthetic.main.activity_enter_otp.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnterOtpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_otp)

        val pinView = findViewById<PinView>(R.id.pinView)
        val otpTimer = findViewById<TextView>(R.id.otpTimer)

        pinView.requestFocus()

        otpTimer.setOnClickListener {
            startTimer()
        }
        pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("before text changed")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 4) {
                    login()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                println("after text changed")
            }

        })

        startTimer()


    }

    private fun startTimer() {
        val otpTimer = findViewById<TextView>(R.id.otpTimer)
        val timer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val currentTime = millisUntilFinished / 1000

                otpTimer.text =
                    "0" + currentTime / 60 + " : " + if (currentTime % 60 >= 10) currentTime % 60 else "0" + currentTime % 60
            }

            override fun onFinish() {
                otpTimer.text = "Resend"
            }
        }
        timer.start()
    }

    fun onEditClick(view: View) {
        val intent = Intent(this, EnterPhoneActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
    }

    fun login() {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val retrofit = RetrofitClient.getRetroInstance()
        val apiInterface = retrofit.create(AuthInterface::class.java)
        progressBar.visibility = View.VISIBLE
        GlobalScope.launch {
            try {
                val response =
                    apiInterface.login(LoginRequest("8860533811", pinView.text.toString().toInt()))
                if (response.isSuccessful) {
                    Log.e("Success", "Success")

                    startActivity(
                        Intent(this@EnterOtpActivity, DashboardActivity::class.java)
                    )


                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
            }
        }
    }
}