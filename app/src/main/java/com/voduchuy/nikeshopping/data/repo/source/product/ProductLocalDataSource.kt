package com.voduchuy.nikeshopping.data.repo.source.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.voduchuy.nikeshopping.data.model.Product
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ProductLocalDataSource : ProductDataSource {
    override fun getProducts(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    @Query("SELECT * FROM products")
    override fun getFavoriteProducts(): Single<List<Product>>

    @Query("SELECT isFavorite FROM products WHERE id = :id")
    override fun isProductFavorite(id: Int): Single<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addToFavorites(product: Product): Completable

    @Delete
    override fun deleteFromFavorite(product: Product): Completable
}