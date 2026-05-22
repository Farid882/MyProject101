package com.example.myproject101.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myproject101.domain.ShopItem
import com.example.myproject101.domain.ShopListRepository
import kotlin.random.Random

object ShopItemRepositoryImpl : ShopListRepository {

    //private val shopList = sortedSetOf(compareBy<ShopItem> { it.id })
    private val shopList = sortedSetOf(compareBy(ShopItem::id))
    var autoIncrementId = 0
    private val shopListLd = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0..3000) {
            var item = ShopItem(name = "Name ", i, isEnabled = Random.nextBoolean() )
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateLD()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateLD()
    }

    override fun editeShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        deleteShopItem(oldElement)
        addShopItem(shopItem)
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