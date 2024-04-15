package com.voduchuy.nikeshopping.data.repo.product

import com.voduchuy.nikeshopping.data.model.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {

    fun getProducts():Single<List<Product>>

    fun getFavoriteProducts():Single<List<Product>>

    fun addFavoriteProducts(product: Product):Completable

    fun deleteFavoriteProduct(product: Product):Completable
}