package com.voduchuy.nikeshopping.data.repo.source.productdetail

import com.voduchuy.nikeshopping.data.model.Product
import io.reactivex.Single

interface ProductDetailDataSource {
    fun getProductDetail(id:Int):Single<Product>
}