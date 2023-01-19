package com.example.mycornerapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycornerapp.MainActivity.Companion.currentUser
import com.example.mycornerapp.databinding.InterestRowBinding

class GamesApiAdapter (var clickLisstener: ClickLisstener) :
    ListAdapter<GameItem, GamesApiAdapter.ViewHolder>(GamesApiDiffUtil()) {
    class ViewHolder(var binding: InterestRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            InterestRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        holder.binding.apply {

            interestTv.text = game.title
            Log.e("AAA", "onBindViewHolder: ${game.title}", )

            deleteBtn.setOnClickListener {
                Log.d("TAG", "onBindViewHolder: Amhere!!!")
                clickLisstener.addgameData(GameTable(0,game.title,game.platform,game.genre,game.short_description,game.game_url,game.thumbnail,currentUser!!.id))
            }

        }
    }

    interface ClickLisstener {
        fun addgameData(gameItem: GameTable)
    }
}