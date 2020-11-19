package com.chip.challenge.data

import com.google.gson.annotations.SerializedName

data class BreedRandomImagesResponse(
    @field:SerializedName("message") val message: Map<String, List<String>>,
    @field:SerializedName("status") val status: String
)
