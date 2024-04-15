package com.voduchuy.nikeshopping.utils

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

abstract class ShoppingSingleObserver<T>(val compositeDisposable:CompositeDisposable):SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        EventBus.getDefault().post(ShoppingExceptionMapper.map(e))
        Timber.e(e)
    }

    override fun onSuccess(t: T & Any) {
        TODO("Not yet implemented")
    }

}