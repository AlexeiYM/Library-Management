package com.example.library

class LibraryUsage(private val library: Library) {
    fun start() {
        var itemsToShow = -1
        while (itemsToShow != 0) {
            println(
                """
                -----------------------------
                0. Выйти в меню
                1. Показать книги
                2. Показать газеты
                3. Показать диски
                Что показать (введите номер):
            """.trimIndent()
            )
            itemsToShow = readlnOrNull()?.toIntOrNull() ?: -1
            if (itemsToShow in 1..3) {

                library.showItems(itemsToShow)

                println("Выберите с чем взаимодействовать (введите номер):")
                val numberOfItem: Int = readlnOrNull()?.toIntOrNull() ?: -1
                if (numberOfItem == 0) continue
                else {
                    try {
                        library.run {
                            when (itemsToShow) {
                                1 -> interactWithItem(getBook(numberOfItem))
                                2 -> interactWithItem(getNewspaper(numberOfItem))
                                3 -> interactWithItem(getDisk(numberOfItem))
                            }
                        }
                    } catch (exception: IllegalStateException) {
                        println("Невозможно выполнить действие: ${exception.message}")
                        continue
                    } catch (exception: IndexOutOfBoundsException) {
                        println("Невозможно выполнить действие: такого номера не существует")
                        continue
                    } catch (exception: IllegalArgumentException) {
                        println("Невозможно выполнить действие: ${exception.message}")
                        continue
                    }
                }

            } else {
                println("Вы ввели некорректное число")
            }
        }
    }
}