package com.voduchuy.nikeshopping.ui.product

import androidx.lifecycle.MutableLiveData
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.repo.productdetail.ProductDetailRepository
import com.voduchuy.nikeshopping.utils.ShoppingCompletableObserver
import com.voduchuy.nikeshopping.utils.ShoppingSingleObserver
import com.voduchuy.nikeshopping.utils.ShoppingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(
    private val productId: Int,
    private val productDetailRepository: ProductDetailRepository
) : ShoppingViewModel() {
    val productDetailLiveData = MutableLiveData<Product>()

    init {
        processBarLiveData.value = true
        productDetailRepository.getProductDetail(productId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { processBarLiveData.value = false }
            .subscribe(object : ShoppingSingleObserver<Product>(compositeDisposable) {
                override fun onSuccess(t: Product) {
                    productDetailLiveData.value = t
                }
            })

    }

    fun addProductToFavorite(product: Product) {
        if (product.isFavorite) {
            productDetailRepository.deleteFavoriteProduct(product)
                .subscribeOn(Schedulers.io())
                .subscribe(object : ShoppingCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        product.isFavorite = false
                    }
                })
        } else {
            productDetailRepository.addFavoriteProducts(product)
                .subscribeOn(Schedulers.io())
                .subscribe(object : ShoppingCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        product.isFavorite = true
                    }
                })
        }
    }
}