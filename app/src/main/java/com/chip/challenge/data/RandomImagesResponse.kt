package com.chip.challenge.data

import com.google.gson.annotations.SerializedName

data class RandomImagesResponse(
    @field:SerializedName("message") val message: List<String>,
    @field:SerializedName("status") val status: String
)
