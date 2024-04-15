package com.voduchuy.nikeshopping.data.repo.productdetail

import com.voduchuy.nikeshopping.data.model.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDetailRepository {
    fun getProductDetail(id:Int):Single<Product>

    fun getFavoriteProducts():Single<List<Product>>

    fun getFavorite(id:Int):Single<Boolean>

    fun addFavoriteProducts(product: Product): Completable

    fun deleteFavoriteProduct(product: Product): Completable
}