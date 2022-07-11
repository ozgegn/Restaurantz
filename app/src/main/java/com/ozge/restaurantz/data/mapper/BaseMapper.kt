package com.ozge.restaurantz.data.mapper

import com.ozge.restaurantz.data.entity.ResponseModel
import com.ozge.restaurantz.domain.model.UIModel

interface BaseMapper <R: ResponseModel, U: UIModel> {
    fun mapToUIModel(responseModel: R?): U
}