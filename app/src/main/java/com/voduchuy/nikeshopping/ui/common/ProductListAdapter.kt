package com.voduchuy.nikeshopping.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.data.model.Product
import com.voduchuy.nikeshopping.databinding.ItemProductBinding
import com.voduchuy.nikeshopping.utils.load


class ProductListAdapter(private val onclick:(productId:Int)->Unit?,private val onFavoriteClick: (product: Product) -> Unit?) : ListAdapter<(Product), ProductListAdapter.ViewHolder>(ProductDiffCallback()) {


    class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, onclick:((productId:Int)->Unit?), onFavoriteClick: (product: Product) -> Unit?){
            with(binding) {
                tvProductTitle.text = product.title
                tvProductPrice.text = buildString {
                    append(product.price.toString())
                    append(" $")
                }
                rbProductStar.rating=product.rating.rate
                ivProductImage.load(product.image)
                itemProduct.setOnClickListener {
                    onclick.invoke(product.id)
                }
                updateFavoriteButton(product.isFavorite)
                btnFavorite.setOnClickListener {
                    onFavoriteClick.invoke(product)
                    product.isFavorite = !product.isFavorite
                    updateFavoriteButton(product.isFavorite)
                }


            }
        }
        private fun updateFavoriteButton(isFavorite: Boolean) {
            if (isFavorite)
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_fill)
            else
                binding.btnFavorite.setImageResource(R.drawable.ic_favorites)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemProductBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),onclick,onFavoriteClick)
    }


}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
       return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id==newItem.id
    }

}
