package com.voduchuy.nikeshopping.data.repo.cart

import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.repo.source.cart.CartDataSource
import io.reactivex.Single

class CartRepositoryImpl(private val cartDataSource: CartDataSource):CartRepository {
    override fun getCart(id:Int): Single<List<Cart>> {
        return cartDataSource.getCart(id)
    }

}