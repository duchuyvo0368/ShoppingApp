package com.voduchuy.nikeshopping.data.repo.source.cart

import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.model.Product
import io.reactivex.Single

interface CartDataSource {
    fun getCart(userId:Int):Single<List<Cart>>
    fun getProductId(id: Int): Single<List<Product>>
}