package com.voduchuy.nikeshopping.data.repo.source.productdetail

import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.services.ApiService
import io.reactivex.Single

class ProductDetailRemoteDataSource(private val apiService: ApiService): ProductDetailDataSource {
    override fun getProductDetail(id: Int): Single<Product> {
       return apiService.getProductDetail(id)
    }

}