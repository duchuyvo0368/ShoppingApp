package com.voduchuy.nikeshopping.ui.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.voduchuy.nikeshopping.data.model.Banner
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.databinding.FragmentHomeBinding
import com.voduchuy.nikeshopping.ui.common.BannerSliderAdapter
import com.voduchuy.nikeshopping.ui.common.CategoryListAdapter
import com.voduchuy.nikeshopping.ui.common.ProductListAdapter
import com.voduchuy.nikeshopping.ui.product.ProductDetailActivity
import com.voduchuy.nikeshopping.utils.EXTRA_KEY_ID
import com.voduchuy.nikeshopping.utils.ShoppingFragment
import com.voduchuy.nikeshopping.utils.convertDpPixel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class HomeFragment : ShoppingFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private val productListAdapter by lazy { ProductListAdapter(::onclick, ::onFavoriteClick) }
    private val productCategory by lazy { CategoryListAdapter(::onclickCategory) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupAdapter()
        return binding.root
    }

    private fun getListAdapter(listBanner: List<Banner>) {
        val bannerSliderAdapter = BannerSliderAdapter(requireContext(), listBanner)
        binding.bannerSliderViewPager.adapter = bannerSliderAdapter
        val viewPagerHeight = (((binding.bannerSliderViewPager.measuredWidth - convertDpPixel(
            32f, requireContext(),
        )) * 140) / 328).toInt()
        val layoutParams = binding.bannerSliderViewPager.layoutParams
        layoutParams.height = viewPagerHeight
        binding.bannerSliderViewPager.layoutParams = layoutParams
        binding.sliderIndicator.setViewPager2(binding.bannerSliderViewPager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBanners()
        setupCategory()
        setupProgressBar()
    }

    private fun setupCategory() {
        homeViewModel.listCategory.observe(viewLifecycleOwner){
            Timber.i(it.toString())
            productCategory.submitList(it)
        }
        homeViewModel.getCategory()
        binding.rvCategory.adapter=productCategory
        binding.rvCategory.overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun setupProgressBar() {
        homeViewModel.processBarLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }

    }

    private fun setupBanners() {
        homeViewModel.bannersLiveData.observe(viewLifecycleOwner) {
            Timber.i(it.toString())
            getListAdapter(it)
        }
    }

    private fun setupAdapter() {
        homeViewModel.productLiveData.observe(viewLifecycleOwner) {
            Timber.i(it.toString())
            productListAdapter.submitList(it)

        }
        homeViewModel.getProducts()
        binding.latestProductsRv.adapter = productListAdapter
        binding.latestProductsRv.overScrollMode = View.OVER_SCROLL_NEVER



    }

    private fun onFavoriteClick(productId: Product) {
        homeViewModel.addProductToFavorite(productId)
    }

    private fun onclick(i: Int) {
        startActivity((Intent(requireContext(), ProductDetailActivity::class.java)).apply {
            putExtra(EXTRA_KEY_ID, i)
        })
        Toast.makeText(context, "$i", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        Toast.makeText(binding.root.context, "Distroy", Toast.LENGTH_SHORT).show()
        super.onDestroyView()
    }
    private fun onclickCategory(id: Int) {
        Toast.makeText(activity,"Category",Toast.LENGTH_SHORT).show()
    }
}


