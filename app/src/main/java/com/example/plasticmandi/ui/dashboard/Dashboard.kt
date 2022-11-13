package com.example.plasticmandi.ui.dashboard

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.plasticmandi.R
import com.example.plasticmandi.databinding.FragmentDashboardBinding
import com.example.plasticmandi.ui.dashboard.fragments.alloffers.AllOffersView
import com.example.plasticmandi.ui.dashboard.fragments.myoffers.MyOffers
import com.example.plasticmandi.ui.dashboard.fragments.orderhistory.OrderHistory

class Dashboard : Fragment(R.layout.fragment_dashboard) {

    lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allOffers = AllOffersView()
        val myOffers = MyOffers()
        val orderHistory = OrderHistory()

   //     setCurrentFragment(allOffers)

//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.allOffers -> setCurrentFragment(allOffers)
//                R.id.myOffers -> setCurrentFragment(myOffers)
//                R.id.orderHistory -> setCurrentFragment(orderHistory)
//                R.id.chat -> setCurrentFragment(allOffers)
//                else -> {}
//            }
//            true
//        }

        binding.profile.setOnClickListener {
            val popupMenu = PopupMenu(this.requireContext(), binding.profile, Gravity.END, 0, R.style.MyPopupMenu)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.profile -> {
                        findNavController().navigate(R.id.action_dashboard_to_profile)
                    }
                    R.id.logout -> Toast.makeText(this.requireContext(), "Logout", Toast.LENGTH_SHORT).show()

                }
                true

            }
        }
    }

//    private fun setCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.frameLayout, fragment)
//            commit()
//        }
//    }

}