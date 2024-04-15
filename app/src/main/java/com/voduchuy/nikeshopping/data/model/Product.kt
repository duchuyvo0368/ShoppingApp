package com.voduchuy.nikeshopping.data.model


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "products")
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    val description: String,
    val category: String,
    @Embedded
    val rating: Rating
) : Parcelable {
    var isFavorite: Boolean = false
}