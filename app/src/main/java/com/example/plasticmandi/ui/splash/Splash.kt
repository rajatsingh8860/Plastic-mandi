package com.example.plasticmandi.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.plasticmandi.R
import com.example.plasticmandi.databinding.FragmentSplashBinding
import com.example.plasticmandi.db.UserDatabase
import com.example.plasticmandi.repository.SplashRepository
import com.example.plasticmandi.viewModelFactory.SplashViewModelFactory
import com.example.plasticmandi.viewmodel.SplashViewModel


class Splash : Fragment(R.layout.fragment_splash) {

    lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        val splashRepository = SplashRepository(UserDatabase(this.requireContext()))
        val viewModelProviderFactory = SplashViewModelFactory(splashRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SplashViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                findNavController().navigate(R.id.action_splash_to_dashboard)
            } else {
                binding.getStarted.visibility = View.VISIBLE
            }
        })

        binding.getStarted.setOnClickListener {
            findNavController().navigate(R.id.action_splash_to_enterPhone)
        }
    }
}