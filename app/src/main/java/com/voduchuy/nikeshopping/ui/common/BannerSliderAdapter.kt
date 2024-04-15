package com.voduchuy.nikeshopping.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.voduchuy.nikeshopping.R
import com.voduchuy.nikeshopping.data.model.Banner
import com.voduchuy.nikeshopping.utils.load

class BannerSliderAdapter(private val context: Context, private val imageUrls: List<Banner>) : RecyclerView.Adapter<BannerSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.load(imageUrls[position].image)
    }

    override fun getItemCount(): Int = imageUrls.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.slider)
    }
}