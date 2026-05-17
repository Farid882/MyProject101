package com.example.myproject101.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.myproject101.domain.ShopItem

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(
        oldItem: ShopItem,
        newItem: ShopItem,
    ): Boolean {
        return oldItem.id == newItem.id; // Compare IDs to determine if items have changed
    }

    override fun areContentsTheSame(
        oldItem: ShopItem,
        newItem: ShopItem,
    ): Boolean {
        return oldItem == newItem; // Check equality based on all properties
    }
}