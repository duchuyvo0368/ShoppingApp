package com.voduchuy.nikeshopping.data.repo.source.cart

import com.voduchuy.nikeshopping.data.model.Cart
import io.reactivex.Single

interface CartDataSource {
    fun getCart(id:Int):Single<List<Cart>>
}