package com.ozge.restaurantz.data.entity

import com.google.gson.annotations.SerializedName
import com.ozge.restaurantz.data.mapper.BaseMapper
import kotlinx.serialization.json.JsonObject

@kotlinx.serialization.Serializable
data class RestaurantEntity(
    val id: Int?,
    val uid: String?,
    val name: String?,
    val type: String?,
    val description: String?,
    val review: String?,
    val logo: String?,
    val phone_number: String?,
    val address: String?
): ResponseModel