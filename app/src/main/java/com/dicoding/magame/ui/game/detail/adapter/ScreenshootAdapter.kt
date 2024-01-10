package com.dicoding.magame.ui.game.detail.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.core.data.source.remote.response.ScreenshotItem
import com.dicoding.magame.databinding.ItemScreenshotBinding


class ScreenshootAdapter(private val context: Context,private val listScreenshoot: List<ScreenshotItem>) : RecyclerView.Adapter<ScreenshootAdapter.MyViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemScreenshotBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = listScreenshoot[position]
        holder.bind(data,context)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listScreenshoot[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listScreenshoot.size

    class MyViewHolder(private val binding: ItemScreenshotBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ScreenshotItem,context: Context) {
            Glide.with(context).load(data.image).into(binding.image)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ScreenshotItem)
    }
}