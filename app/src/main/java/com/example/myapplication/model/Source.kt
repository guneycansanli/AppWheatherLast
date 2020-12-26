package com.example.myapplication.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Source(
    @SerializedName("crawl_rate")
    val crawlRate: Int?=null,
    @SerializedName("slug")
    val slug: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("url")
    val url: String?=null
) : Serializable