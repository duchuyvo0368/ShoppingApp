package com.voduchuy.nikeshopping.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.MaterialColors
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.data.model.CartItemCount
import com.voduchuy.nikeshopping.databinding.ActivityMainBinding
import com.voduchuy.nikeshopping.utils.ShoppingActivity
import com.voduchuy.nikeshopping.utils.setupWithNavController
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : ShoppingActivity() {
    private lateinit var binding:ActivityMainBinding
    private var currentNAvController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupButtonNavigationBar()
        }
    }

    private fun setupButtonNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.buttonNavigationBar)
        val navGraphIds = listOf(R.navigation.home, R.navigation.cart, R.navigation.profile)
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds,
            supportFragmentManager,
            R.id.nav_host_container,
            intent
        )
        currentNAvController = controller

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupButtonNavigationBar()
    }


    override fun onSupportNavigateUp(): Boolean {
        return currentNAvController?.value?.navigateUp()?:false
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCartItemsCountChangeEvent(cartItemCount: CartItemCount) {
        val badge = binding.buttonNavigationBar.getOrCreateBadge(R.id.cart)
        badge.badgeGravity = BadgeDrawable.BOTTOM_START
        badge.backgroundColor = MaterialColors.getColor(binding.buttonNavigationBar, R.attr.dividerColor)
        badge.number = cartItemCount.count
       // badge.verticalOffset= convertDpToPixel(10f,this).toInt()
        badge.isVisible = cartItemCount.count > 0
    }
    override fun onResume() {
        super.onResume()
        //viewModel.getCartItemsCount()
    }
}