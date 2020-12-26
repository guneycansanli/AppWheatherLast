package com.example.myapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CityDailyWeather(
    @SerializedName("air_pressure")                  //
    val airPressure: Int? = null,
    @SerializedName("applicable_date")  //
    val applicableDate: String? = null,
    @SerializedName("created")  //
    val created: String? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("max_temp")   //okey
    val maxTemp: Double? = null,
    @SerializedName("min_temp")   //okey
    val minTemp: Double? = null,
    @SerializedName("predictability")
    val predictability: Int? = null,
    @SerializedName("the_temp")
    val theTemp: Double? = null,
    @SerializedName("visibility")
    val visibility: Double? = null,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String? = null,
    @SerializedName("weather_state_name")
    val weatherStateName: String? = null,
    @SerializedName("wind_direction")
    val windDirection: Int? = null,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String? = null,
    @SerializedName("wind_speed")
    val windSpeed: Int? = null
): Serializable

/*
 "id": 366945,
    "weather_state_name": "Light Rain",
    "weather_state_abbr": "lr",
    "wind_direction_compass": "N",
    "created": "2013-04-27T22:52:57.403100Z",
    "applicable_date": "2013-04-27",
    "min_temp": 3.07,
    "max_temp": 10.01,
    "the_temp": null,
    "wind_speed": 9.85,
    "wind_direction": 358,
    "air_pressure": null,
    "humidity": 74,
    "visibility": 9.997862483098704,
    "predictability": 75

 */