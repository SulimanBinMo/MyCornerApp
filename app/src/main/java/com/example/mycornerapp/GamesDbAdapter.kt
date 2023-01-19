package com.example.mycornerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycornerapp.databinding.PhotosRowBinding

class GamesDbAdapter (val clickListener: ClickListener): ListAdapter<GameTable, GamesDbAdapter.ViewHolder>(GamesDbDiffUtil()) {

    class ViewHolder(var binding: PhotosRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PhotosRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        holder.binding.apply {
            titleTv.text=game.title
            Glide.with(root.context).load(game.image_url).into(imageView)
            deleteBtn.setOnClickListener{
                clickListener.delete(game)
            }
        }

    }
    interface ClickListener {
        fun delete(gameTable: GameTable)
    }
}