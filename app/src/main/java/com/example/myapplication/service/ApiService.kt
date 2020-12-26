package com.example.myapplication.service


import com.example.myapplication.model.City
import com.example.myapplication.model.CityDailyWeather
import com.example.myapplication.model.CityWeather5day
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // todo: YORUM : GET ile gidlecek apinin base'den sonraki kısmı buraya yazılır. Dinamik olarak değişecek yerle longitude ve lattitude.
    //  Bu değerleri de getNearCities fonksiyonuna parametre olarak iletiyoruz.
    //  Response objesi de City Listesi olacak (Call<ArrayList<City>>)
    @GET("/api/location/search/")
    fun getNearCities(@Query("lattlong", encoded = true) dynamicLocationInfo: String?): Call<ArrayList<City>>

    // todo YORUM: günlük ve haftalık bilgileri aşağıdaki blokları açıp call ederek alabilirsin.
    //  DİKKAT: bir üstteki mettota @Query, alttakilerde @Path kullandık. Why?

    @GET("/api/location/{woeid}/{date_year}/{date_month}/{date_day}/")
    fun getCityDailyWeather(@Path("woeid", encoded = true) woeid: String?,
                            @Path("date_year", encoded = true) dateYear: String?,
                            @Path("date_month", encoded = true) dateMonth: String?,
                            @Path("date_day", encoded = true) dateDay: String?): Call<CityDailyWeather> // return type bu servisin jsonına göre yeni bir model olmalı!!


    @GET("api/location/{woeid}/")
    fun getCityWeeklyWeather(@Path("woeid", encoded = true) woeid: String?): Call<ArrayList<CityWeather5day>>
// return type bu servisin jsonına göre yeni bir model olmalı!!

}

