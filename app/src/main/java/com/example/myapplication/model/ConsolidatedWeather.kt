package com.example.myapplication.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ConsolidatedWeather(
    @SerializedName("air_pressure")
    val airPressure: Double?=null,
    @SerializedName("applicable_date")
    val applicableDate: String?=null,
    @SerializedName("created")
    val created: String?=null,
    @SerializedName("humidity")
    val humidity: Int?=null,
    @SerializedName("id")
    val id: Long?=null,
    @SerializedName("maxTemp")
    val maxTemp: Double?=null,
    @SerializedName("min_temp")
    val minTemp: Double?=null,
    @SerializedName("predictability")
    val predictability: Int?=null,
    @SerializedName("the_temp")
    val theTemp: Double?=null,
    @SerializedName("visibility")
    val visibility: Double?=null,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String?=null,
    @SerializedName("weather_state_name")
    val weatherStateName: String?=null,
    @SerializedName("wind_direction")
    val windDirection: Double?=null,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String?=null,
    @SerializedName("wind_speed")
    val windSpeed: Double?=null
) : Serializable