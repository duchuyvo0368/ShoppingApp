package com.voduchuy.nikeshopping.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voduchuy.nikeshopping.data.model.Category
import com.voduchuy.nikeshopping.databinding.ItemCategoryBinding
import com.voduchuy.nikeshopping.utils.load

class CategoryListAdapter(private val onclick: (categoryId: Int) -> Unit?) : ListAdapter<(Category), CategoryListAdapter.ViewHolder>(CategoryDiffCallback()) {


    class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, onclick: ((categoryId: Int) -> Unit?)) {
            with(binding) {
                ivCategory.load(category.image)
                tvCategory.text=category.name
                itemCategory.setOnClickListener {
                    onclick.invoke(category.id)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onclick)
    }
}

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

}

