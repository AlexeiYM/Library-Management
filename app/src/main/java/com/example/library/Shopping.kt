package com.example.library

interface Shop {
    fun sell(): LibraryHoldable
}

class BookShop : Shop {
    override fun sell(): Book {
        return Book(
            "Компьютерные сети",
            1007,
            "В.Олифер, Н.Олифер",
            19600,
            "Да"
        )
    }
}

class NewspaperShop : Shop {
    override fun sell(): Newspaper {
        return Newspaper(
            23,
            "Главное за месяц",
            23021,
            "Июнь",
            "Да"
        )
    }
}

class DiskShop : Shop {
    override fun sell(): Disk {
        return Disk(
            "DVD",
            "Чужой 2",
            98233,
            "Да"
        )
    }
}

class Manager {
    fun <T : Shop> buy(shop: T): LibraryHoldable {
        return shop.sell()
    }
}

class ManagerUsage(
    private val library: Library,
    private val manager: Manager,
    private val bookShop: BookShop,
    private val newspaperShop: NewspaperShop,
    private val diskShop: DiskShop
) {
    fun start() {
        println(
            """
            -----------------------------
            Где надо совершить покупку?
            1. Магазин книг
            2. Магазин дисков
            3. Газетный ларек
        """.trimIndent()
        )
        val shopSelected: Int = readlnOrNull()?.toIntOrNull() ?: -1
        when (shopSelected) {
            1 -> library.add(manager.buy(bookShop))
            2 -> library.add(manager.buy(diskShop))
            3 -> library.add(manager.buy(newspaperShop))
        }
        if (shopSelected !in 1..3) println("Нет магазина с таким номером")
        else println("Покупка совершена успешно!")
    }
}