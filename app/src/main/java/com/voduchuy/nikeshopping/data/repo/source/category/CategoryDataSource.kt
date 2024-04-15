package com.voduchuy.nikeshopping.data.repo.source.category

import com.voduchuy.nikeshopping.data.model.Category
import io.reactivex.Single

interface CategoryDataSource {
    fun getCategory():Single<List<Category>>
}