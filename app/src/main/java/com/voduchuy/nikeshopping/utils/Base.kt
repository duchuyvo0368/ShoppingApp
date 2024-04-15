package com.voduchuy.nikeshopping.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.ui.auth.AuthActivity
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.IllegalStateException

interface ShoppingView {
    val rootView: CoordinatorLayout?
    val viewContext: Context?

    fun setProgressIndicator(mustShow: Boolean) {
        rootView?.let {
            viewContext?.let { context ->
                var loadingView = it.findViewById<View>(R.id.loadingView)
                if (loadingView == null && mustShow) {
                    loadingView =
                        LayoutInflater.from(context).inflate(R.layout.view_loading, it, false)
                    it.addView(loadingView)
                }
                loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE

            }
        }
    }

    fun showEmptyState(layoutResId: Int): View? {
        rootView?.let {
            viewContext?.let { context ->
                var emptyState = it.findViewById<View>(R.id.emptyStateRootView)
                if (emptyState == null) {
                    emptyState = LayoutInflater.from(context).inflate(layoutResId, it, false)
                    it.addView(emptyState)
                }
                emptyState.visibility = View.VISIBLE
                return emptyState
            }
        }
        return null
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showError(shoppingException: ShoppingException) {
        viewContext?.let {
            when (shoppingException.type) {
                ShoppingException.Type.SIMPLE -> showSnackBar(
                    shoppingException.serverMessage
                        ?: it.getString((shoppingException.userFriendlyMessage))
                )

                ShoppingException.Type.AUTH -> {
                    it.startActivity(Intent(it, AuthActivity::class.java))
                    Toast.makeText(it, shoppingException.serverMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        rootView?.let { Snackbar.make(it, message, duration).show() }
    }
}

abstract class ShoppingActivity : AppCompatActivity(), ShoppingView {
    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if (viewGroup !is CoordinatorLayout) {
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout) {
                        return it
                    }
                }
                throw IllegalStateException("RootView must be instance of CoordinatorLayout")
            } else {
                return viewGroup
            }
        }
    override val viewContext: Context?
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}

abstract class ShoppingFragment : Fragment(), ShoppingView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout

    override val viewContext: Context?
        get() = context

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)

    }
}

abstract class ShoppingViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val processBarLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

