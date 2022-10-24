package com.example.plasticmandi.ui.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.plasticmandi.R
import com.example.plasticmandi.databinding.ActivityEnterPhoneBinding
import com.example.plasticmandi.db.UserDatabase
import com.example.plasticmandi.model.request.OtpRequest
import com.example.plasticmandi.repository.AuthRepository
import com.example.plasticmandi.utils.Resource
import com.example.plasticmandi.viewModelFactory.AuthViewModelFactory
import com.example.plasticmandi.viewmodel.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.number_not_found_bottomsheet.view.*
import java.util.regex.Pattern

class EnterPhoneActivity : AppCompatActivity() {

    lateinit var binding: ActivityEnterPhoneBinding
    lateinit var viewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authRepository = AuthRepository(UserDatabase(this))
        val viewModelProviderFactory = AuthViewModelFactory(authRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_phone)

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

        binding.requestOtp.setOnClickListener {
            onRequestOtpClicked(this)
        }



        binding.phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.textInputLayout.error = ""
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        viewModel.sendOtpResponse.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {


                        startActivity(
                            Intent(this@EnterPhoneActivity, EnterOtpActivity::class.java)
                        )

                    }

                }
                is Resource.Error -> {
                    hideProgressBar()
                    if (response.code == 422) {
                        val bottomSheetDialog = BottomSheetDialog(this)
                        bottomSheetDialog.setContentView(view)
                        bottomSheetDialog.show()

                    } else {
                        response.message?.let { message ->
                            Log.e("Error", message)
                        }
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })


    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun onRequestOtpClicked(context: Context) {
        binding.requestOtp.setOnClickListener {

            if (isValidMobile(binding.phoneNumber.text.toString())) {
                viewModel.sendOtp(OtpRequest(binding.phoneNumber.text.toString()))
            } else {
                binding.textInputLayout.error = "Phone number is required"
            }
        }
    }


    private fun isValidMobile(phone: CharSequence): Boolean {
        return Pattern.matches("(?:[+0][1-9])?[0-9]{10,12}$", phone)
    }

}