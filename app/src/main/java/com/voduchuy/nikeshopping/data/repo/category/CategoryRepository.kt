package com.voduchuy.nikeshopping.data.repo.category

import com.voduchuy.nikeshopping.data.model.Category
import io.reactivex.Single

interface CategoryRepository {
     fun getCategory(): Single<List<Category>>
}