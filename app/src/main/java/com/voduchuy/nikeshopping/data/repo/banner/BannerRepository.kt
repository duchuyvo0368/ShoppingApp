package com.voduchuy.nikeshopping.data.repo.banner

import com.voduchuy.nikeshopping.data.model.Banner
import io.reactivex.Single

interface BannerRepository {
    fun getBanner():Single<List<Banner>>
}