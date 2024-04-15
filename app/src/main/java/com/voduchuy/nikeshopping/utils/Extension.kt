package com.voduchuy.nikeshopping.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.ui.view.ShoppingImageView
import java.net.URL


const val EXTRA_KEY_ID = "id"

fun ImageView.load(url: String) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .placeholder(R.drawable.error_product)
        .into(this)
}

fun convertDpPixel(dp: Float, context: Context?): Float {
    return if (context != null) {
        val resource = context.resources
        val metrics = resource.displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    } else {
        val metris = Resources.getSystem().displayMetrics
        dp * (metris.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

