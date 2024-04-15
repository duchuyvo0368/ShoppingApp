package com.voduchuy.nikeshopping.ui.product

import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.databinding.ActivityProductDetailBinding
import com.voduchuy.nikeshopping.utils.EXTRA_KEY_ID
import com.voduchuy.nikeshopping.utils.ShoppingActivity
import com.voduchuy.nikeshopping.utils.load
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ProductDetailActivity : ShoppingActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private val detailViewModel: ProductDetailViewModel by viewModel {
        parametersOf(
            intent.extras!!.getInt(
                EXTRA_KEY_ID
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvOldPrice.paintFlags = binding.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        setupProduct()
        popBackStack()

    }

    private fun popBackStack() {
        binding.backBtn.setOnClickListener {
            onBackPressed();
        }
    }

    private fun setupProduct() {
        detailViewModel.productDetailLiveData.observe(this) {
            setProductUI(it)
        }

    }

    private fun setProductUI(productDetail: Product) {
        binding.apply {
            tvProductName.text = productDetail.title
            ivProductIv.load(productDetail.image)
            tvProductPrice.text = buildString {
                append("$ ")
                append(productDetail.price)
            }
            setupFavorite(productDetail.isFavorite)
            rbProductStar.rating = productDetail.rating.rate
            tvCount.text = productDetail.rating.count.toString()
            tvDescription.text=productDetail.description
            setupFavorite(productDetail.isFavorite)
            favoriteBtn.setOnClickListener {
                detailViewModel.addProductToFavorite(productDetail)
                Toast.makeText(applicationContext,"${productDetail.isFavorite}",Toast.LENGTH_SHORT).show()
                productDetail.isFavorite=!productDetail.isFavorite
                setupFavorite(productDetail.isFavorite)

            }
            addToCartBtn.setOnClickListener {


            }
        }
    }

    private fun setupFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.favoriteBtn.setImageResource(R.drawable.ic_favorite_fill)
        } else {
            binding.favoriteBtn.setImageResource(R.drawable.ic_favorites)
        }
    }


}