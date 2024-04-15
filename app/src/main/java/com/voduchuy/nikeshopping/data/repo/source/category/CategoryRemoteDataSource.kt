package com.voduchuy.nikeshopping.data.repo.source.category

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.data.model.Category
import io.reactivex.Single

class CategoryRemoteDataSource(private val context: Context) : CategoryDataSource {
    override fun getCategory(): Single<List<Category>> {
        return Single.create { emitter ->
            try {
                val inputStream = context.resources.openRawResource(R.raw.category)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                val json = String(buffer)

                val category: List<Category> =
                    Gson().fromJson(json, object : TypeToken<List<Category>>() {}.type)
                emitter.onSuccess(category)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}