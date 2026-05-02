package com.example.myproject101.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myproject101.domain.ShopItem
import com.example.myproject101.domain.ShopListRepository

object ShopItemRepositoryImpl : ShopListRepository {
    private val shopList = mutableSetOf<ShopItem>()
    var autoIncrementId = 0
    private val shopListLd = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0..1000) {
            val item = ShopItem(name = "Солфетки", i, isEnabled = true)
            shopList.add(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editeShopItem(shopItem: ShopItem) {
        val item = getShopItem(shopItem.id)
        val shopItem = item.copy(isEnabled = !shopItem.isEnabled)
        shopList.add(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId } ?: throw Exception("this id,not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        updateLD()
        return shopListLd
    }

    fun updateLD() {
        shopListLd.value = shopList.toList()
    }
}