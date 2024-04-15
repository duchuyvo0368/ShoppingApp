package com.voduchuy.nikeshopping.utils

import androidx.annotation.StringRes


class ShoppingException(val type:Type ,@StringRes val userFriendlyMessage:Int=0,
    val serverMessage:String?=null
): Throwable() {
    enum class Type{
        SIMPLE,AUTH
    }
}