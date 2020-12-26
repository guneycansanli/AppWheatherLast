package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.myapplication.R
import com.example.myapplication.adapters.CitiesDetailsAdapter
import com.example.myapplication.model.CityWeather5day
import com.example.myapplication.model.CityDailyWeather
import com.example.myapplication.service.ApiClient
import com.example.myapplication.util.Utility.hideProgressBar
import com.example.myapplication.util.Utility.isInternetAvailable
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class CityDetailsActivity : AppCompatActivity() {

    private var woeid: Int? = null
    private var recyclerDetail: RecyclerView? = null
    private var citiesDetailAdapter: CitiesDetailsAdapter? = null
    private var listUsersDeatils = ArrayList<CityWeather5day>()
    private var listDaily =ArrayList<CityDailyWeather>()
    private var city_id: TextView?=null
    private var minimum_temp: TextView?=null
    private var maximum_temp: TextView?=null
    private var app_date: TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details)

        init()
        getWoe()
        initRecylerView()

        if (isInternetAvailable()) {  //i.net control
            getNearCitiesFromApi()
            getCityDailyFromApi()
        }
    }

    private fun init() {
        city_id = findViewById<TextView>(R.id.cityId)
        minimum_temp = findViewById<TextView>(R.id.minTemp)
        maximum_temp = findViewById<TextView>(R.id.maximumTemp)
        app_date = findViewById<TextView>(R.id.appDate)
    }

    private fun getCityDailyFromApi() {
        val c=Calendar.getInstance()
        val year= c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        Log.d("tarih",year.toString())
        val woeId = woeid.toString()
        val requestCall = ApiClient.apiService.getCityDailyWeather(woeId,year.toString(),month.toString(),day.toString())
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<CityDailyWeather>>{
            override fun onResponse(
                call: Call<ArrayList<CityDailyWeather>>,
                response: Response<ArrayList<CityDailyWeather>>
            ) {
                hideProgressBar()
                val response = response.body()
                if(response.isNullOrEmpty().not()){
                    response?.let {
                        //                     Burda response ile tek bir city geri dönmedi hepsi geri döndü
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<CityDailyWeather>>, t: Throwable) {

            }

        })


    }

    private fun initRecylerView() {
        recyclerDetail = findViewById(R.id.recycler_detail)
        recyclerDetail?.layoutManager = LinearLayoutManager(this@CityDetailsActivity)
        citiesDetailAdapter = CitiesDetailsAdapter(this)
        recyclerDetail?.adapter = citiesDetailAdapter

    }

    private fun getWoe() {

        woeid = intent.getIntExtra("woeid", 0)

    }

    private fun getNearCitiesFromApi() {

        val woeId = woeid.toString()
        val requestCall = ApiClient.apiService.getCityWeeklyWeather(woeId)

        requestCall.enqueue(object : retrofit2.Callback<ArrayList<CityWeather5day>> {


            override fun onFailure(call: Call<ArrayList<CityWeather5day>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<CityWeather5day>>,
                response: Response<ArrayList<CityWeather5day>>
            ) {
                hideProgressBar()
                val response = response.body()
                if (response.isNullOrEmpty().not()) {
                    listUsersDeatils.clear()
                    response?.let { listUsersDeatils.addAll(it) }
                    citiesDetailAdapter?.setData(listUsersDeatils)
                    //burda adaptera gönderdim test ettim ,, adapter da card view e set ledim ama xml de göstermiyor viev click listener ile ilgisi var mı? 
                }
            }
        })


    }

}


