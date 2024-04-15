package com.voduchuy.nikeshopping.data.repo.source.banner

import com.voduchuy.nikeshopping.data.model.Banner
import io.reactivex.Single

interface BannerDataSource {
    fun getBannerDataSource():Single<List<Banner>>
}