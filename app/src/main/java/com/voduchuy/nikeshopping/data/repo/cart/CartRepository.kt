package com.voduchuy.nikeshopping.data.repo.cart

import com.voduchuy.nikeshopping.data.model.Cart
import io.reactivex.Single

interface CartRepository {
    fun getCart(id:Int):Single<List<Cart>>
}