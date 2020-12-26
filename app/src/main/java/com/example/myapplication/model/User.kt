package com.example.myapplication.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("distance")
    val distance: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("location_type")
    val location_type: String? = null,

    @SerializedName("woeid")
    val woeid: Int? = null,

    @SerializedName("latt_long")
    val latt_long: String? = null,

    ) : Serializable
