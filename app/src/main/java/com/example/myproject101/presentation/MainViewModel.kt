package com.example.myproject101.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject101.data.ShopItemRepositoryImpl
import com.example.myproject101.domain.DeleteShopItemUseCase
import com.example.myproject101.domain.EditeShopItemUseCase
import com.example.myproject101.domain.GetShopListUseCase
import com.example.myproject101.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopItemRepositoryImpl
    private val getShopList = GetShopListUseCase(repository)
    private val editeShopItemUseCase = EditeShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    private val _shopList: LiveData<List<ShopItem>>
        get() = shopList

    val shopList = MutableLiveData<List<ShopItem>>()

}