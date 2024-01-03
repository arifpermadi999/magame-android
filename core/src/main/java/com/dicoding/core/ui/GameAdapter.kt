package com.dicoding.core.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.core.databinding.ItemGameBinding


class GameAdapter (private val context: Context,private val listGame: List<com.dicoding.core.domain.models.Game>) : RecyclerView.Adapter<GameAdapter.MyViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = listGame[position]
        holder.bind(data,context)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listGame[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listGame.size

    class MyViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: com.dicoding.core.domain.models.Game, context: Context) {
            binding.txtTitle.text = data.name
            binding.ratingBar.rating = data.rating
            binding.txtRating.text = "${data.rating} (${data.ratingsCount})"
            binding.txtPlatform.text = "Platform : ${data.platform}"
            Glide.with(context).load(data.image).into(binding.imageGame)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: com.dicoding.core.domain.models.Game)
    }
}