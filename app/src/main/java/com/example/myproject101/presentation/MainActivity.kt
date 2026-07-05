package com.example.myproject101.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myproject101.R
import com.example.myproject101.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val shopListAdapter: ShopListAdapter by lazy { ShopListAdapter() }
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerViewSetup()
        addItemClick()
        viewModel.shopList.observe(this) {
            Log.d("FootBall", it.toString())
            shopListAdapter.submitList(it)  // ← добавьте эту строку
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.save_button)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun recyclerViewSetup() {
        with(binding.myRecycleView) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        shopListAdapter.onShopItemClickListener = {
            Log.i("onClick", "edit mode")
            val intent = ShopItemActivity.newIntentEditItem(this,it.id)
            startActivity(intent)
        }
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it);
        }
    }

    fun addItemClick() {
        binding.btnAddShopItem.setOnClickListener {
            Log.d("onClick","add mode")
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }
}