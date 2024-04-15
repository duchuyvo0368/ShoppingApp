package com.voduchuy.nikeshopping.data.repo.source.product

import com.voduchuy.nikeshopping.data.model.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDataSource {

    fun getProducts(): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun isProductFavorite(id: Int): Single<Boolean>

    fun addToFavorites(product: Product): Completable

    fun deleteFromFavorite(product: Product): Completable
}