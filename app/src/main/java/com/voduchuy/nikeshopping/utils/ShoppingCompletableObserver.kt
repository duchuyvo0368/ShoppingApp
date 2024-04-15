package com.voduchuy.nikeshopping.utils

import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

abstract class ShoppingCompletableObserver(val compositeDisposable: CompositeDisposable) :
    CompletableObserver {
    override fun onSubscribe(d: Disposable) {
       compositeDisposable.add(d)
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable) {
        EventBus.getDefault().post(ShoppingExceptionMapper)
        Timber.e(e)
    }

}