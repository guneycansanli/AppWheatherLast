package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.service.ApiClient
import com.example.myapplication.R
import com.example.myapplication.adapters.CitiesNearAdapter
import com.example.myapplication.model.City
import com.example.myapplication.util.Utility.hideProgressBar
import com.example.myapplication.util.Utility.isInternetAvailable
import com.example.myapplication.util.Utility.showProgressBar

class NearCitiesActivity : AppCompatActivity(), CitiesNearAdapter.RecyclerClickListener {

    private var recycler_main: RecyclerView? = null
    private var listUsers = ArrayList<City>()
    private var citiesNearAdapter: CitiesNearAdapter? = null
    private var longitudeValue: String? = null
    private var latitudeValue: String? = null
    private var dummyLatitude = 36.96
    private var dummyLongitude = -122.02

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_near_cities)

        title = "Yakın Şehirler"
        initRecyclerView()
        getLongLat()

        if (isInternetAvailable()) {  //i.net control
            getNearCitiesFromApi()

        }
    }



    private fun initRecyclerView() {
        recycler_main = findViewById(R.id.recycler_main)
        recycler_main?.layoutManager = LinearLayoutManager(this@NearCitiesActivity)
        citiesNearAdapter = CitiesNearAdapter(this, this)
        recycler_main?.adapter = citiesNearAdapter
    }

    private fun getLongLat() {
        // get the Extras from last page(main)
        latitudeValue = intent.getStringExtra("latitude")
        longitudeValue = intent.getStringExtra("longitude")

        // dmmy
        if (latitudeValue == null) latitudeValue = dummyLatitude.toString()
        if (longitudeValue == null) longitudeValue = dummyLongitude.toString()

    }

    private fun getNearCitiesFromApi() {
        // apii
        showProgressBar()

        // todo: YORUM: getNearCities metoduna cihazdan alınan konum bilgileri verilir.
        val locationInfo = "$latitudeValue,$longitudeValue" // endpointteki dinamik alan!!
        ApiClient.apiService.getNearCities(locationInfo)
            .enqueue(object : retrofit2.Callback<ArrayList<City>> {
                override fun onFailure(call: retrofit2.Call<ArrayList<City>>, t: Throwable) {
                    hideProgressBar()
                    Log.e("error", t.localizedMessage)
                }

                override fun onResponse(
                    call: retrofit2.Call<ArrayList<City>>,
                    response: retrofit2.Response<ArrayList<City>>
                ) {
                    hideProgressBar()
                    val usersResponse = response.body()
                    if (usersResponse.isNullOrEmpty().not()) {
                        listUsers.clear()
                        usersResponse?.let { listUsers.addAll(it) }
                        citiesNearAdapter?.setData(listUsers)
                    }
                }
            })
    }

    // todo: YORUM: CitiesAdapter içinde interface tanımladık. Liste elemanı tıklandığında burası tetiklensin diye.
    //  buradan city Woeid si bir sonraki detay aktivitesine taşınmalı. Woeid ile servis çağrıları yapılacak.
    override fun onListItemClick(city: City?) {
        //

        Toast.makeText(this, city?.title, Toast.LENGTH_SHORT).show()
        val intent1 = Intent(this, CityDetailsActivity::class.java)
        intent1.putExtra("woeid", city?.woeid)
        Log.d("woid", city?.woeid.toString())
        startActivity(intent1)


    }
}