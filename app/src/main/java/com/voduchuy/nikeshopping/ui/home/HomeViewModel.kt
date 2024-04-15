package com.voduchuy.nikeshopping.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voduchuy.nikeshopping.data.model.Banner
import com.voduchuy.nikeshopping.data.model.Category
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.repo.banner.BannerRepository
import com.voduchuy.nikeshopping.data.repo.category.CategoryRepository
import com.voduchuy.nikeshopping.data.repo.product.ProductRepository
import com.voduchuy.nikeshopping.utils.ShoppingCompletableObserver
import com.voduchuy.nikeshopping.utils.ShoppingSingleObserver
import com.voduchuy.nikeshopping.utils.ShoppingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val productRepository: ProductRepository,
    private val bannerRepository: BannerRepository,
    private val categoryRepository: CategoryRepository
) : ShoppingViewModel() {
    private val _productLiveData = MutableLiveData<List<Product>>()
    val productLiveData: LiveData<List<Product>> = _productLiveData
    val bannersLiveData = MutableLiveData<List<Banner>>()
    private val _listCategory=MutableLiveData<List<Category>>()
    val listCategory:LiveData<List<Category>> =_listCategory
    init {
        bannerRepository.getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ShoppingSingleObserver<List<Banner>>(compositeDisposable) {
                override fun onSuccess(t: List<Banner>) {
                    bannersLiveData.value = t
                }
            })
    }

    fun getProducts() {
        processBarLiveData.value = true
        productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { processBarLiveData.value = false }
            .subscribe(object : ShoppingSingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    _productLiveData.value = t
                }
            })
    }

    fun addProductToFavorite(product: Product) {
        if (product.isFavorite) {
            productRepository.deleteFavoriteProduct(product)
                .subscribeOn(Schedulers.io())
                .subscribe(object : ShoppingCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        product.isFavorite = false
                    }
                })
        } else {
            productRepository.addFavoriteProducts(product)
                .subscribeOn(Schedulers.io())
                .subscribe(object : ShoppingCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        product.isFavorite = true
                    }
                })
        }
    }

    fun getCategory() {
        categoryRepository.getCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ShoppingSingleObserver<List<Category>>(compositeDisposable){
                override fun onSuccess(t: List<Category>) {
                    _listCategory.value=t
                }
            })
    }
}