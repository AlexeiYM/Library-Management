package com.example.library

class Manager {
    fun <T : Shop> buy(shop: T): LibraryHoldable {
        return shop.sell()
    }
}