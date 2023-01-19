package com.example.mycornerapp

import androidx.recyclerview.widget.DiffUtil

class GamesDbDiffUtil() : DiffUtil.ItemCallback<GameTable>() {
    override fun areItemsTheSame(oldItem: GameTable, newItem: GameTable): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameTable, newItem: GameTable): Boolean {
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