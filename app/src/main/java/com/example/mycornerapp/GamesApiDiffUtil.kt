package com.example.mycornerapp

import androidx.recyclerview.widget.DiffUtil

class GamesApiDiffUtil () : DiffUtil.ItemCallback<GameItem>() {
    override fun areItemsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
        return when {
            oldItem.id == newItem.id -> true
            oldItem.platform == newItem.platform -> true
            oldItem.title == newItem.title -> true
            oldItem.genre == newItem.genre -> true
            oldItem.short_description == newItem.short_description -> true
            oldItem.game_url == newItem.game_url -> true
            else -> {
                false
            }
        }
    }
}