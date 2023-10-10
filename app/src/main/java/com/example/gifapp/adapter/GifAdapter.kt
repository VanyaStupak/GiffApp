package com.example.gifapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gifapp.activity.FullScreenGifActivity
import com.example.gifapp.data.GifItem
import com.example.gifapp.databinding.ItemGifBinding


class GifAdapter : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {
    private var gifList = emptyList<GifItem>()

    inner class GifViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: GifItem) {
            Glide.with(binding.root.context)
                .load(gif.images.original.url)
                .into(binding.gifImageView)
            binding.title.text = gif.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(gifList[position])

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, FullScreenGifActivity::class.java)

            intent.putExtra("gifUrl", gifList[position].images.original.url)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<GifItem>) {
        gifList = newList
        notifyDataSetChanged()
    }
}


