package com.voduchuy.nikeshopping.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.databinding.ItemCartBinding

class CartListAdapter:ListAdapter<(Cart),CartListAdapter.ViewHolder>(CartDiffCallback()) {
    class ViewHolder(private val binding:ItemCartBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(cart: Cart){
            binding.tvCartName.text=cart.products.title
            binding.tvCartDescribe.text=cart.date.toString()
            binding.tvCartPrice.text=cart.products.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ItemCartBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class CartDiffCallback :DiffUtil.ItemCallback<Cart>(){
    override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.id==newItem.id
    }

}
