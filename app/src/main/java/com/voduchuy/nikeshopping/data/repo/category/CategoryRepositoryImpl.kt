package com.voduchuy.nikeshopping.data.repo.category

import com.voduchuy.nikeshopping.data.model.Category
import com.voduchuy.nikeshopping.data.repo.source.category.CategoryDataSource
import io.reactivex.Single

class CategoryRepositoryImpl(private val categoryDataSource: CategoryDataSource):CategoryRepository {
    override fun getCategory(): Single<List<Category>> {
        return categoryDataSource.getCategory()
    }
}