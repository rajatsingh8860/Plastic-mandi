package com.example.plasticmandi.ui.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.plasticmandi.R
import com.example.plasticmandi.databinding.FragmentEnterOtpBinding
import com.example.plasticmandi.db.UserDatabase
import com.example.plasticmandi.repository.AuthRepository
import com.example.plasticmandi.utils.Resource
import com.example.plasticmandi.viewModelFactory.AuthViewModelFactory
import com.example.plasticmandi.viewmodel.AuthViewModel

class EnterOtp : Fragment(R.layout.fragment_enter_otp) {

    private lateinit var binding: FragmentEnterOtpBinding
    private lateinit var viewModel: AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val authRepository = AuthRepository(UserDatabase(this.requireContext()))
        val viewModelProviderFactory = AuthViewModelFactory(authRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel::class.java)
        binding = FragmentEnterOtpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pinView.requestFocus()

        binding.otpTimer.setOnClickListener {
            startTimer()
        }

        binding.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("before text changed")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 4) {
                    viewModel.login(
                        binding.pinView.text.toString().toInt(),
                        "8860533811"
                        //enterPhoneBinding.phoneNumber.text.toString()
                    )
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                println("after text changed")
            }

        })

        startTimer()

        viewModel.verifyOtpResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {

                        findNavController().navigate(R.id.action_enterOtp_to_dashboard)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("Error", message)
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })



        binding.edit.setOnClickListener {
            findNavController().navigate(R.id.action_enterOtp_to_enterPhone)
        }


    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun startTimer() {
        val timer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val currentTime = millisUntilFinished / 1000

                binding.otpTimer.text =
                    "0" + currentTime / 60 + " : " + if (currentTime % 60 >= 10) currentTime % 60 else "0" + currentTime % 60
            }

            override fun onFinish() {
                binding.otpTimer.text = "Resend"
            }
        }
        timer.start()
    }


}