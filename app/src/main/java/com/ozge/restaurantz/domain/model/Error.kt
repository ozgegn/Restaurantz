package com.ozge.restaurantz.domain.model

data class Error(
    var code: Int? = null,
    var error: String? = null,
    var message: String? = null
)