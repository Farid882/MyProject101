package com.example.myproject101.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject101.data.ShopItemRepositoryImpl
import com.example.myproject101.domain.AddShopItemUseCase
import com.example.myproject101.domain.DeleteShopItemUseCase
import com.example.myproject101.domain.EditeShopItemUseCase
import com.example.myproject101.domain.GetShopItemUseCase
import com.example.myproject101.domain.GetShopListUseCase
import com.example.myproject101.domain.ShopItem

class MainViewModel : ViewModel() {
    private val repository= ShopItemRepositoryImpl
    private val addShopItem= AddShopItemUseCase(repository)
    private val deleteShopItem= DeleteShopItemUseCase(repository)
    private val editeShopItem= EditeShopItemUseCase(repository)
    private val getShopItem= GetShopItemUseCase(repository)
    private val getShopList= GetShopListUseCase(repository)

    private val _shopList= MutableLiveData<List<ShopItem>>()
    // Публичная неизменяемая LiveData для наблюдения извне
    val shopList: LiveData<List<ShopItem>> = _shopList
    init {
        // Загружаем список при создании ViewModel
        loadShopList()
    }
    fun changeEnableState(shopItem: ShopItem){
        val item = shopItem.copy(isEnabled=!shopItem.isEnabled)
        editeShopItem.editeShopItem(item)
        loadShopList()
    }

    private fun loadShopList() {
        getShopList.getShopList().observeForever { list ->
            _shopList.value = list
        }
    }
}