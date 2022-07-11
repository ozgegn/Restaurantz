package com.ozge.restaurantz.data.mapper

import com.ozge.restaurantz.data.entity.RestaurantEntity
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.utils.extensions.orDefaultInteger

class RestaurantUIMapper : BaseMapper<RestaurantEntity, RestaurantUIModel> {
    override fun mapToUIModel(responseModel: RestaurantEntity?): RestaurantUIModel {
        return RestaurantUIModel(
            id = responseModel?.id.orDefaultInteger(),
            uid = responseModel?.uid.orEmpty(),
            name = responseModel?.name.orEmpty(),
            type = responseModel?.type.orEmpty(),
            description = responseModel?.description.orEmpty(),
            review = responseModel?.review.orEmpty(),
            logo = responseModel?.logo.orEmpty(),
            phoneNumber = responseModel?.phone_number.orEmpty(),
            address = responseModel?.address.orEmpty(),
            hours = emptyList()
        )
    }
}