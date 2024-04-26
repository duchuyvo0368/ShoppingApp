package com.voduchuy.nikeshopping.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.databinding.FragmentCartBinding
import com.voduchuy.nikeshopping.ui.common.CartListAdapter
import com.voduchuy.nikeshopping.utils.ShoppingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class CartFragment : ShoppingFragment() {
    private val cartListAdapter: CartListAdapter by lazy { CartListAdapter() }
    private val cartViewModel: CartViewModel by viewModel()
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        cartViewModel.productList.observe(viewLifecycleOwner){
            cartListAdapter.submitList(it)
            binding.rvCart.adapter = cartListAdapter
        }
        cartViewModel.listProduct()
    }


}