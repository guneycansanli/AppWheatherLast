package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CityWeather5day

class CitiesDetailsAdapter(private val context: Context) :
    RecyclerView.Adapter<CitiesDetailsAdapter.MyViewHolder>() {


    private var dataListDetail = ArrayList<CityWeather5day>()

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater1 = LayoutInflater.from(context)
        val view: View = inflater1.inflate(R.layout.user_row_daily, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int = dataListDetail.size


    fun setData(data: ArrayList<CityWeather5day>) {
        this.dataListDetail = data
        notifyDataSetChanged()

    }

    inner class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var cardView: CardView? = null
        var weathername: TextView? = null
        var weatherimage: TextView? = null
        var date: TextView? = null
        var maxTemp: TextView? = null
        var minTemp: TextView? = null
        var image: TextView? = null

        var detailCity: CityWeather5day? = null

        init {
            cardView = view.findViewById(R.id.cardViewDaily)
            weathername = view.findViewById(R.id.weatherName)
            weatherimage = view.findViewById(R.id.weatherImage)
            date = view.findViewById(R.id.date)
            maxTemp = view.findViewById(R.id.maximumTemp)
            minTemp = view.findViewById(R.id.minTemp)
            image = view.findViewById(R.id.weatherImageSrc)


        }

        fun bind(position: Int) {

            detailCity = dataListDetail.get(position)
            weathername?.text = detailCity?.title.toString()
            weatherimage?.text = detailCity?.sources.toString()
            date?.text = detailCity?.time.toString()
            maxTemp?.text = detailCity?.sunRise.toString()
            minTemp?.text = detailCity?.sunSet.toString()

        }
    }

    open class RecyclerClickListener {


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

}