package com.example.myproject.Model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName


data class DrugsResponse(
        @SerializedName("problems")
        val problems: JsonElement)