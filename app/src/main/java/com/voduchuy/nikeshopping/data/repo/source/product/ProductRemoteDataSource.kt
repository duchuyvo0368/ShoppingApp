package com.voduchuy.nikeshopping.data.repo.source.product

import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.services.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class ProductRemoteDataSource(val apiService: ApiService): ProductDataSource {
    override fun getProducts(): Single<List<Product>> {
        return apiService.getProducts()
    }


    override fun getFavoriteProducts(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun isProductFavorite(id: Int): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(product: Product): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteFromFavorite(product: Product): Completable {
        TODO("Not yet implemented")
    }
}