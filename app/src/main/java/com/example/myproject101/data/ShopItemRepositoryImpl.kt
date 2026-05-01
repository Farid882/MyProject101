package com.example.myproject101.data

import androidx.lifecycle.LiveData
import com.example.myproject101.domain.ShopItem
import com.example.myproject101.domain.ShopListRepository

class ShopItemRepositoryImpl: ShopListRepository {
    override fun addShopItem(shopItem: ShopItem) {
        TODO("Not yet implemented")
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        TODO("Not yet implemented")
    }

    override fun editeShopItem(shopItem: ShopItem) {
        TODO("Not yet implemented")
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        TODO("Not yet implemented")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        TODO("Not yet implemented")
    }
}