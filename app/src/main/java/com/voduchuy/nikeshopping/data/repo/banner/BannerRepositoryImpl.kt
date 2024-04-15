package com.voduchuy.nikeshopping.data.repo.banner

import com.voduchuy.nikeshopping.data.model.Banner
import com.voduchuy.nikeshopping.data.repo.source.banner.BannerDataSource
import io.reactivex.Single

class BannerRepositoryImpl(private val bannerRepository: BannerDataSource) : BannerRepository {
    override fun getBanner(): Single<List<Banner>> {
        return bannerRepository.getBannerDataSource()
    }
}