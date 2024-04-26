package com.voduchuy.nikeshopping

import android.app.Application
import androidx.room.Room
import com.facebook.drawee.backends.pipeline.Fresco
import com.voduchuy.nikeshopping.data.db.AppDatabase
import com.voduchuy.nikeshopping.data.repo.banner.BannerRepository
import com.voduchuy.nikeshopping.data.repo.banner.BannerRepositoryImpl
import com.voduchuy.nikeshopping.data.repo.cart.CartRepository
import com.voduchuy.nikeshopping.data.repo.cart.CartRepositoryImpl
import com.voduchuy.nikeshopping.data.repo.category.CategoryRepository
import com.voduchuy.nikeshopping.data.repo.category.CategoryRepositoryImpl
import com.voduchuy.nikeshopping.data.repo.productdetail.ProductDetailRepository
import com.voduchuy.nikeshopping.data.repo.productdetail.ProductDetailRepositoryImpl
import com.voduchuy.nikeshopping.data.repo.product.ProductRepository
import com.voduchuy.nikeshopping.data.repo.product.ProductRepositoryImpl
import com.voduchuy.nikeshopping.data.repo.source.banner.BannerRemoteDataSource
import com.voduchuy.nikeshopping.data.repo.source.cart.CartRemoteDataSource
import com.voduchuy.nikeshopping.data.repo.source.category.CategoryRemoteDataSource
import com.voduchuy.nikeshopping.data.repo.source.productdetail.ProductDetailRemoteDataSource
import com.voduchuy.nikeshopping.data.repo.source.product.ProductRemoteDataSource
import com.voduchuy.nikeshopping.data.services.createApiServiceInstance
import com.voduchuy.nikeshopping.ui.cart.CartViewModel
import com.voduchuy.nikeshopping.ui.home.HomeViewModel
import com.voduchuy.nikeshopping.ui.product.ProductDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant((Timber.DebugTree()))
        Fresco.initialize(this)
        val myModule = module {
            single { createApiServiceInstance() }
            single { Room.databaseBuilder(this@App, AppDatabase::class.java, "db_app").build() }
            factory<ProductRepository> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    get<AppDatabase>().productDao()
                )
            }
            factory<ProductDetailRepository> {
                ProductDetailRepositoryImpl(
                    ProductDetailRemoteDataSource(get()), get<AppDatabase>().productDao()
                )
            }
            factory<BannerRepository> { BannerRepositoryImpl(BannerRemoteDataSource(androidContext())) }
            factory<CategoryRepository> {
                CategoryRepositoryImpl(
                    CategoryRemoteDataSource(
                        androidContext()
                    )
                )
            }
            factory<CartRepository> { CartRepositoryImpl(CartRemoteDataSource(get())) }
            viewModel { HomeViewModel(get(), get(), get()) }
            viewModel { (productId: Int) -> ProductDetailViewModel(productId, get()) }
            viewModel { CartViewModel(get()) }

        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

    }
}