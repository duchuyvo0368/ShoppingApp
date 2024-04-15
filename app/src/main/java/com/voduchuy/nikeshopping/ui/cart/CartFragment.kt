package com.voduchuy.nikeshopping.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.databinding.FragmentCartBinding
import com.voduchuy.nikeshopping.utils.ShoppingFragment


class CartFragment : ShoppingFragment() {

    private lateinit var binding:FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCartBinding.inflate(inflater,container,false)
        return binding.root

    }


}