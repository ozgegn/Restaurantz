package com.ozge.restaurantz.data.entity

import com.google.gson.annotations.SerializedName
import com.ozge.restaurantz.data.mapper.BaseMapper
import kotlinx.serialization.json.JsonObject

data class RestaurantEntity(
    val id: Int?,
    val uid: String?,
    val name: String?,
    val type: String?,
    val description: String?,
    val review: String?,
    val logo: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    val address: String?,
    val hours: JsonObject
): ResponseModel