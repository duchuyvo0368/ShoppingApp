package com.voduchuy.nikeshopping.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voduchuy.nikeshopping.data.model.Cart
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.databinding.ItemCartBinding
import com.voduchuy.nikeshopping.utils.load


class CartListAdapter : ListAdapter<(Product), CartListAdapter.ViewHolder>(CartDiffCallback()) {
    class ViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.tvCartName.text = product.title
           // binding.tvCartDescribe.text = product.description
            binding.ivCart.load(product.image)
            binding.tvCartPrice.text = product.price.toString()
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class CartDiffCallback :DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id==newItem.id
    }

}
