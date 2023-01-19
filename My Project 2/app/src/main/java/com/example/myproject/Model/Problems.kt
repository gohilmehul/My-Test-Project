package com.example.myproject.Model

import com.google.gson.annotations.SerializedName


data class Problems(
        @SerializedName("name")
        var name: String?="",
        @SerializedName("dose")
        var dose: String?="",
        @SerializedName("strength")
        var strength: String?="")


