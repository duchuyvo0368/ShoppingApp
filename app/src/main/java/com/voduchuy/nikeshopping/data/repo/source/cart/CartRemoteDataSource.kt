package com.voduchuy.nikeshopping.data.repo.source.cart

import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.services.ApiService
import io.reactivex.Single

class CartRemoteDataSource(private val apiService: ApiService) : CartDataSource {
    override fun getCart(userId: Int): Single<List<Cart>> {
        return apiService.getCart(userId)
    }

    override fun getProductId(id: Int): Single<List<Product>> {
        return apiService.getProductDetail(id).map {
                listOf(it)
            }

    }


}