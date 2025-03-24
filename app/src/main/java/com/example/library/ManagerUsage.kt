package com.example.library

class ManagerUsage(
    private val library: Library,
    private val manager: Manager,
    private val listOfShops: List<Shop>
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
            1 -> {
                val bookShopIndex = listOfShops.indexOfFirst { it is BookShop }
                library.add(manager.buy(listOfShops[bookShopIndex]))
            }
            2 -> {
                val diskShopIndex = listOfShops.indexOfFirst { it is DiskShop }
                library.add(manager.buy(listOfShops[diskShopIndex]))
            }
            3 -> {
                val newspaperShopIndex = listOfShops.indexOfFirst { it is NewspaperShop }
                library.add(manager.buy(listOfShops[newspaperShopIndex]))
            }
        }
        if (shopSelected !in 1..3) println("Нет магазина с таким номером")
        else println("Покупка совершена успешно!")
    }
}
