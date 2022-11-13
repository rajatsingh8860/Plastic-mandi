package com.example.plasticmandi.ui.dashboard.fragments.alloffers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.PopupMenu.OnDismissListener
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plasticmandi.R
import com.example.plasticmandi.model.response.AllOffers.AllOffers
import com.example.plasticmandi.repository.AllOfferRepository
import com.example.plasticmandi.ui.dashboard.fragments.alloffers.adapter.AllOfferAdapter
import com.example.plasticmandi.utils.Resource
import com.example.plasticmandi.viewModelFactory.AllOffersViewModelFactory
import com.example.plasticmandi.viewmodel.AllOffersViewModel
import kotlinx.android.synthetic.main.fragment_all_offers.view.*


class AllOffersView : Fragment(R.layout.fragment_all_offers) {

    lateinit var viewModel: AllOffersViewModel
//    lateinit var binding: FragmentAllOffersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allOffersRepository = AllOfferRepository()
        val viewModelProviderFactory = AllOffersViewModelFactory(allOffersRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(AllOffersViewModel::class.java)
        val plastic = resources.getStringArray(R.array.plastics)

//        binding = DataBindingUtil.setContentView(this,R.layout.fragment_all_offers
        val arrayAdapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, plastic)
        view.autoCompleteTextView.setAdapter(arrayAdapter)
        view.autoCompleteTextView.setDropDownBackgroundDrawable(resources.getDrawable(R.drawable.dropdown_border))

//        view.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
//
//        }

        view.autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if(!view.autoCompleteTextView.isPopupShowing){
//                    view.autoCompleteTextView.background = resources.getDrawable(R.drawable.textfield_background_without_dropdown)
//                }
//                else{
//                    view.autoCompleteTextView.background = resources.getDrawable(R.drawable.textfield_background)
//                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })



        viewModel.allOffersResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    setUpRecyclerView(response.data?.data)
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

    }


    private fun hideProgressBar() {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        progressBar?.visibility = View.GONE
    }

    private fun showProgressBar() {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        progressBar?.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(allOffer: List<AllOffers>?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this.context)
        val adapter = AllOfferAdapter(this.requireContext())
        recyclerView?.adapter = adapter
        adapter.updateList(allOffer!!)
    }

}