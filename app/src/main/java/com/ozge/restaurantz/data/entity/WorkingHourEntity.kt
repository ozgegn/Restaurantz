package com.ozge.restaurantz.data.entity

import com.google.gson.annotations.SerializedName

data class WorkingHourEntity(
    @SerializedName("opens_at") val opensAt: String?,
    @SerializedName("closes_at") val closesAt: String?,
    @SerializedName("is_closed") val isClosed: Boolean?
): ResponseModel
