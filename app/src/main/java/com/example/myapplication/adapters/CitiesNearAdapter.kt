package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.City

class CitiesNearAdapter(private val context: Context, val listener: RecyclerClickListener?) :
    RecyclerView.Adapter<CitiesNearAdapter.MyViewHolder>() {

    interface RecyclerClickListener{
        fun onListItemClick(city: City?)
    }

    private var dataList = ArrayList<City>()

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.user_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(data: ArrayList<City>) {
        this.dataList = data
        notifyDataSetChanged()
    }

    inner class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var cardView: CardView? = null
        var distance: TextView? = null
        var title: TextView? = null
        var username: TextView? = null
        var woeid: TextView? = null
        var city: City? = null

        init {
            cardView = view.findViewById(R.id.cardView)
            distance = view.findViewById(R.id.txt_user_name)
            title = view.findViewById(R.id.txt_user_info1)
            username = view.findViewById(R.id.txt_user_info2)
            woeid = view.findViewById(R.id.txt_user_address)

            cardView?.setOnClickListener {
                listener?.onListItemClick(city)
            }
        }

        fun bind(position: Int) {
            city = dataList.get(position)

            distance?.text = city?.distance.toString()
            title?.text = city?.title
            username?.text = city?.location_type
            distance?.text = city?.distance.toString()

        }
    }

}
