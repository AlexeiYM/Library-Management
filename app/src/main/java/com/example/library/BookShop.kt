package com.example.library

class BookShop : Shop {
    override fun sell(): Book {
        return Book(
            "Компьютерные сети",
            1007,
            "В.Олифер, Н.Олифер",
            19600,
            true
        )
    }
}