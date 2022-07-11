package com.ozge.restaurantz.utils.extensions

fun Int?.orDefaultInteger(): Int = this ?: -1

fun String?.orDefaultErrorMessage() = this ?: "Unknown Error."