package com.voduchuy.nikeshopping.data.repo.source.banner

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.data.model.Banner
import io.reactivex.Single
import android.content.Context


class BannerRemoteDataSource(private val context: Context) : BannerDataSource {
    override fun getBannerDataSource(): Single<List<Banner>> {
        return Single.create { emitter ->
            try {
                val inputStream = context.resources.openRawResource(R.raw.banner)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                val json = String(buffer)

                val banners: List<Banner> = Gson().fromJson(json, object : TypeToken<List<Banner>>() {}.type)
                emitter.onSuccess(banners)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}