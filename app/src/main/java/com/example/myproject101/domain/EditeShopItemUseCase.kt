package com.example.myproject101.domain

class EditeShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editeShopItem(shopItem: ShopItem) {
        shopListRepository.editeShopItem(shopItem)
    }
}