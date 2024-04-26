package com.voduchuy.nikeshopping.data.repo.cart

import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.model.ProductCart
import io.reactivex.Single

interface CartRepository {
    fun getCart(userId:Int):Single<List<ProductCart>>
    fun getProductId(id:Int):Single<List<Product>>
}