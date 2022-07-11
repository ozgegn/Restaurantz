package com.ozge.restaurantz.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.serialization.Serializable
@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val id: Int?,
    val uid: String?,
    val name: String?,
    val type: String?,
    val description: String?,
    val review: String?,
    val logo: String?,
    val phone_number: String?,
    val address: String?,
    var page: Int? = 1
) : ResponseModel