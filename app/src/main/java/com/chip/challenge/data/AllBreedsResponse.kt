package com.chip.challenge.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a content search response from Dog API.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://dog.ceo/dog-api/documentation/).
 */
data class AllBreedsResponse(
    @field:SerializedName("message") val message: Map<String, List<String>>,
    @field:SerializedName("status") val status: String
)
