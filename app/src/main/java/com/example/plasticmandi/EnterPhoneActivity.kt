package com.example.plasticmandi

import android.app.Notification.Action
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import com.airbnb.lottie.LottieAnimationView
import com.example.plasticmandi.`interface`.AuthInterface
import com.example.plasticmandi.request.OtpRequest
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_enter_phone.*
import kotlinx.android.synthetic.main.number_not_found_bottomsheet.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class EnterPhoneActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_phone)
        onRequestOtpClicked(this);
        val requestOtp = findViewById<Button>(R.id.request_otp)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.number_not_found_bottomsheet, null, false)
        view.setBackgroundResource(android.R.color.transparent)


        val animation = view.findViewById<LottieAnimationView>(R.id.callAnimation)
        animation.playAnimation()

        view.getInTouch.setOnClickListener {
            println("button clicked")
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:8860533811")
            startActivity(intent)
        }



        phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textInputLayout.error = ""
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


    }


    private fun onRequestOtpClicked(context: Context) {
        val requestOtp = findViewById<Button>(R.id.request_otp)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)


        requestOtp.setOnClickListener {

            if (isValidMobile(phoneNumber.text)) {
                sendOtp(context)
            } else {
                textInputLayout.error = "Phone number is required"
            }
        }
    }


    private fun sendOtp(context: Context) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val retrofit = RetrofitClient.getRetroInstance()
        val apiInterface = retrofit.create(AuthInterface::class.java)
        progressBar.visibility = View.VISIBLE

        val view = layoutInflater.inflate(
            R.layout.number_not_found_bottomsheet,
            null,
            false
        )


        GlobalScope.launch {
            try {
                val response = apiInterface.sendOtp(OtpRequest(phoneNumber.text.toString()))
                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE
                }
                if (response.isSuccessful) {
                    if (response.code() == 422) {
                        withContext(Dispatchers.Main) {
                            val bottomSheetDialog = BottomSheetDialog(context)
                            bottomSheetDialog.setContentView(view)
                            bottomSheetDialog.show()
                        }
                    } else {

                        startActivity(
                            Intent(this@EnterPhoneActivity, EnterOtpActivity::class.java)
                        )
                    }

                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }

    }

    private fun isValidMobile(phone: CharSequence): Boolean {
        return Pattern.matches("(?:[+0][1-9])?[0-9]{10,12}$", phone)
    }

}