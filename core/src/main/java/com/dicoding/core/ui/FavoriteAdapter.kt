package com.dicoding.core.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.core.databinding.ItemFavoriteBinding
import com.dicoding.core.domain.models.Favorite


class FavoriteAdapter (private val context: Context,private val listFavorite: List<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = listFavorite[position]
        holder.bind(data,context)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFavorite[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listFavorite.size

    class MyViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Favorite,context: Context) {
            //set a item in here
            binding.txtTitle.text = data.name
            binding.txtRating.text = data.ratingsCount.toString()
            binding.ratingBar.rating = data.rating!!.toFloat()
            binding.txtPlatform.text = data.platform
            Glide.with(context).load(data.image).into(binding.imageGame)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Favorite)
    }
}