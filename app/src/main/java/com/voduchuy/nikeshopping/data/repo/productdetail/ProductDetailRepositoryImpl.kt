package com.voduchuy.nikeshopping.data.repo.productdetail

import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.repo.source.productdetail.ProductDetailDataSource
import com.voduchuy.nikeshopping.data.repo.source.product.ProductLocalDataSource
import io.reactivex.Completable
import io.reactivex.Single

class ProductDetailRepositoryImpl(private val productDetailRemoteDataSource: ProductDetailDataSource, private val productDetailLocalDataSource: ProductLocalDataSource) :
    ProductDetailRepository {
    override fun getProductDetail(id: Int): Single<Product> {
       return productDetailLocalDataSource.getFavoriteProducts().flatMap { favoriteProduct->
            productDetailRemoteDataSource.getProductDetail(id).doOnSuccess { it ->
                val favoriteProductIds=favoriteProduct.map { it.id }
                favoriteProductIds.forEach { localProduct ->
                    if (localProduct==it.id ) {
                        it.isFavorite = true
                    }
                }
            }
        }

    }

    override fun getFavoriteProducts(): Single<List<Product>> {
        return productDetailLocalDataSource.getFavoriteProducts()
    }

    override fun getFavorite(id:Int): Single<Boolean> {
        return productDetailLocalDataSource.isProductFavorite(id)
    }

    override fun addFavoriteProducts(product: Product): Completable {
        return productDetailLocalDataSource.addToFavorites(product)
    }

    override fun deleteFavoriteProduct(product: Product): Completable {
        return  productDetailLocalDataSource.deleteFromFavorite(product)
    }


}