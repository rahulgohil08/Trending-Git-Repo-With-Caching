package com.theworld.androidtemplatewithnavcomponents.data.remote


import com.google.gson.annotations.SerializedName

data class Trending(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<TrendingItem>,
    @SerializedName("msg")
    val msg: String
)