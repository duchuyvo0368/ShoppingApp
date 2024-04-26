package com.voduchuy.nikeshopping.data.repo.cart

import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.model.ProductCart
import com.voduchuy.nikeshopping.data.repo.productdetail.ProductDetailRepository
import com.voduchuy.nikeshopping.data.repo.source.cart.CartDataSource
import io.reactivex.Single

class CartRepositoryImpl(private val cartDataSource: CartDataSource):CartRepository {
    override fun getCart(userId:Int): Single<List<ProductCart>> {
        return cartDataSource.getCart(userId).map { cartList->
            val products = mutableListOf<ProductCart>()
            for (cart in cartList) {
                products.addAll(cart.products)
            }
            products.toList()
        }
    }

    override fun getProductId(id: Int): Single<List<Product>> {
         return cartDataSource.getProductId(id)
    }

}