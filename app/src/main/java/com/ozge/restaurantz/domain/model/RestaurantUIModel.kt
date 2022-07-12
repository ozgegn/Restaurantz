package com.ozge.restaurantz.domain.model

data class RestaurantUIModel(
    val id: Int,
    val uid: String,
    val name: String,
    val type: String,
    val description: String,
    val review: String,
    val logo: String,
    val phoneNumber: String,
    val address: String,
    val hours: List<WorkingHours> = emptyList()
): UIModel

data class WorkingHours(
    val opensAt: String?,
    val closesAt: String?,
    val isClosed: Boolean?
): UIModel