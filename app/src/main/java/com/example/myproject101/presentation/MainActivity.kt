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
    private val shopListAdapter : ShopListAdapter by lazy{ShopListAdapter()}
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel._shopList.observe(this){
            Log.d("MainActivity",it.toString())
        }
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerViewSetup()  // ← добавьте эту строку
        viewModel.shopList.observe(this) {
            Log.d("FootBall", it.toString())
            shopListAdapter.submitList(it)  // ← добавьте эту строку
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun recyclerViewSetup(){
        with(binding.myRecycleView) {
            adapter = shopListAdapter
            setHasFixedSize(true) // для улучшения производительности
        }
        shopListAdapter.onsetClickListener={
            Log.i( "onClick", it.name)
        }
        shopListAdapter.setOnlongClickListener={
            viewModel.changeEnableState(it);
        }
    }
}