package com.voduchuy.nikeshopping.data.repo.product

import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.repo.source.product.ProductDataSource
import com.voduchuy.nikeshopping.data.repo.source.product.ProductLocalDataSource
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepositoryImpl(val remoteDataSource: ProductDataSource, val localDataSource: ProductLocalDataSource):
    ProductRepository {
    override fun getProducts(): Single<List<Product>> =
        localDataSource.getFavoriteProducts().flatMap { favoriteProduct->
            remoteDataSource.getProducts().doOnSuccess { it ->
                val favoriteProductIds=favoriteProduct.map { it.id }
                it.forEach{product->
                    if (favoriteProductIds.contains(product.id)){
                        product.isFavorite=true
                    }
                }
            }
        }
    override fun getFavoriteProducts(): Single<List<Product>> {
        return localDataSource.getProducts()
    }

    override fun addFavoriteProducts(product: Product): Completable {
        return localDataSource.addToFavorites(product)
    }

    override fun deleteFavoriteProduct(product: Product): Completable {
        return localDataSource.deleteFromFavorite(product)
    }
}