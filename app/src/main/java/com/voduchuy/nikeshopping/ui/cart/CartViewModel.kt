package com.voduchuy.nikeshopping.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.model.ProductCart
import com.voduchuy.nikeshopping.data.repo.cart.CartRepository
import com.voduchuy.nikeshopping.data.repo.productdetail.ProductDetailRepository
import com.voduchuy.nikeshopping.utils.ShoppingSingleObserver
import com.voduchuy.nikeshopping.utils.ShoppingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.notify

class CartViewModel(
    private val cartRepository: CartRepository,
) : ShoppingViewModel() {

    private val listProduct = mutableListOf<Product>()
    private val _productList = MutableLiveData<List<Product>>()
    val productList:LiveData<List<Product>> = _productList

    init {

        cartRepository.getCart(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { processBarLiveData.value = false }
            .subscribe(object : ShoppingSingleObserver<List<ProductCart>>(compositeDisposable) {
                override fun onSuccess(t: List<ProductCart>) {
                    t.forEach {
                        getProductId(it.productId)
                    }

                }
            })


    }

    fun listProduct() {
        _productList.value = listProduct
    }

    fun getProductId(id: Int) {
        cartRepository.getProductId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                ShoppingSingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    listProduct.addAll(t)

                }
            })

    }


}