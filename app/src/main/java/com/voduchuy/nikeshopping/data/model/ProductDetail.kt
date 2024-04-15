package com.voduchuy.nikeshopping.data.model

data class ProductDetail(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
){
    var isFavorite:Boolean=false
}