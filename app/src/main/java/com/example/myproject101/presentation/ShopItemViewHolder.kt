package com.example.myproject101.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject101.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tv_name = view.findViewById<TextView>(R.id.tv_name)
    val edName = view.findViewById<TextInputEditText>(R.id.ed_name)
    val edCount = view.findViewById<TextInputEditText>(R.id.ed_count)
    val tv_count = view.findViewById<TextView>(R.id.tv_count)
    val tilName = view.findViewById<TextInputLayout>(R.id.til_name)
    val tilCount = view.findViewById<TextInputLayout>(R.id.til_count)
}

