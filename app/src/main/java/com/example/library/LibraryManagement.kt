package com.example.library

import com.example.library.Months.*

fun main() {
    val list = listOf(
        Book("Повести", 285, "Н.В. Гоголь", 13233, true),
        Book("Принц и Нищий", 281, "Марк Твен", 14422, false),
        Book("Война и мир", 1300, "Л.Н. Толстой", 223333, true),
        Disk("DVD", "Человек-паук", 56674, true),
        Disk("CD", "Звёздные войны I-VI", 56678, true),
        Disk("DVD", "Чужой", 34900, false),
        Newspaper(53, "Новости Москвы", 78352, SEPTEMBER, false),
        Newspaper(3, "События в мире звёзд", 24233, DECEMBER, false),
        Newspaper(21, "Главное за неделю", 75409, JUNE, true)
    )

    val library = Library(list)
    val manager = Manager()
    val bookShop = BookShop()
    val newspaperShop = NewspaperShop()
    val diskShop = DiskShop()

    val shopList = listOf(bookShop, newspaperShop, diskShop)
    val libraryUsage = LibraryUsage(library)
    val managerUsage = ManagerUsage(library, manager, shopList)

    while (true) {
        println(
            """
            -----------------------------
            1. Управление менеджером
            2. Библиотека
            3. Оцифровать газету/книгу
            """.trimIndent()
        )
        val action: Int = readlnOrNull()?.toIntOrNull() ?: -1
        when (action) {
            1 -> managerUsage.start()
            2 -> libraryUsage.start()
            3 -> library.digitizationRoomUsage()
        }
    }
}