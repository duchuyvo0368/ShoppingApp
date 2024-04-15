package com.voduchuy.nikeshopping.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Rating(
    val count: Int,
    val rate: Float
): Parcelable