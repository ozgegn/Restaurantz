package com.ozge.restaurantz.domain.model

import androidx.annotation.DrawableRes

interface UIModel

data class PromptModel(
    val errorCode: String?,
    val message: String?,
    @DrawableRes val icon: Int? = null
)

data class UIDetail<T : UIModel>(
    val data: T?,
    val promptModel: PromptModel? = null
)