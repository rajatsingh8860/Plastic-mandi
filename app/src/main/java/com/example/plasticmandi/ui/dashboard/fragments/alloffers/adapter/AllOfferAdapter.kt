package com.example.plasticmandi.ui.dashboard.fragments.alloffers.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plasticmandi.R
import com.example.plasticmandi.model.response.AllOffers.AllOffers

class AllOfferAdapter(private val context: Context) :
    RecyclerView.Adapter<AllOfferAdapter.AllOfferViewHolder>() {

    val allOffers = ArrayList<AllOffers>()

    class AllOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val offerId: TextView = itemView.findViewById(R.id.offer_id)
        val companyName: TextView = itemView.findViewById<TextView>(R.id.company_name)
        val plasticName: TextView = itemView.findViewById<TextView>(R.id.plastic_name)
        val price: TextView = itemView.findViewById<TextView>(R.id.price)
        val mfi: TextView = itemView.findViewById<TextView>(R.id.mfi)
        val grade: TextView = itemView.findViewById<TextView>(R.id.grade)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOfferViewHolder {
        return AllOfferViewHolder(
            LayoutInflater.from(context).inflate(R.layout.buy_now_tile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllOfferViewHolder, position: Int) {
        val offer = allOffers[position]
        holder.offerId.text = offer.referenceId
        holder.companyName.text = offer.productDetails.company.name
        holder.plasticName.text = offer.productDetails.plastic.name
        holder.price.text = "Rs. ${offer.price.toString()}"
        holder.mfi.text = offer.productDetails.mfi.toString()
        holder.grade.text = offer.productDetails.grade
    }

    override fun getItemCount(): Int {
        return allOffers.size
    }

    fun updateList(newList : List<AllOffers>){
        allOffers.clear()
        allOffers.addAll(newList)
        notifyDataSetChanged()
    }

}