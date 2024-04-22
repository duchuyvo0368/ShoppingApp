package com.voduchuy.nikeshopping.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.repo.cart.CartRepository
import com.voduchuy.nikeshopping.utils.ShoppingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CartViewModel(private val cartRepository: CartRepository) : ShoppingViewModel() {
    private val _cartList = MutableLiveData<List<Cart>>()
    val cartList: LiveData<List<Cart>> = _cartList


    fun getCartList() {
        cartRepository.getCart(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}