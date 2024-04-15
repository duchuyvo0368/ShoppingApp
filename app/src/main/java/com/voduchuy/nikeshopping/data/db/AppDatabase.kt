package com.voduchuy.nikeshopping.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.data.repo.source.product.ProductLocalDataSource


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract  fun productDao(): ProductLocalDataSource
}