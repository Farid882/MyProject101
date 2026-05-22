package com.example.myproject101.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject101.databinding.ShopItemEnabledBinding
import com.example.myproject101.domain.ShopItem

class EnabledViewHolder(
    view: View
) :
    RecyclerView.ViewHolder(view) {
    private val binding = ShopItemEnabledBinding.bind(view)

    fun bind(item: ShopItem) {
        binding.apply {
            tvName.text = item.name
            tvCount.text = item.count.toString()
        }
    }
}


