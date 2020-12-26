package com.example.myapplication.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Parent(
    @SerializedName("latt_long")
    val lattLong: String?=null,
    @SerializedName("location_type")
    val locationType: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("woeid")
    val woeid: Int?=null
) : Serializable