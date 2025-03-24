package com.example.library

class Library(list: List<LibraryHoldable>) {

    private val books = mutableListOf<Book>()
    private val newspapers = mutableListOf<Newspaper>()
    private val disks = mutableListOf<Disk>()

    init{
        books.addAll(getObjects<Book>(list))
        newspapers.addAll(getObjects<Newspaper>(list))
        disks.addAll(getObjects<Disk>(list))
    }

    fun interactWithItem(item: LibraryHoldable) {
        println(
            """
            0. Выйти
            1. Взять домой
            2. Читать в читательном зале
            3. Показать подробную информацию
            4. Вернуть
            Что вы хотите сделать (введите номер):""".trimIndent()
        )
        val action: Int = readlnOrNull()?.toIntOrNull() ?: -1
        require(action in 0..4) { "нет действия с таким номером" }
        item.run {
            when (action) {
                0 -> return
                1 -> takeHome()
                2 -> takeAtHall()
                3 -> showDetailedInformation()
                4 -> giveBack()
            }
        }
    }

    fun showItems(number: Int) {
        println("0. Выйти")
        when (number) {
            1 -> showBooks()
            2 -> showNewspapers()
            3 -> showDisks()
        }
    }

    fun getBook(number: Int): Book {
        return books[number - 1]
    }

    fun getNewspaper(number: Int): Newspaper {
        return newspapers[number - 1]
    }

    fun getDisk(number: Int): Disk {
        return disks[number - 1]
    }

    private fun showBooks() {
        for (i in books.indices) {
            print("${i + 1}. ")
            books[i].showSummary()
        }
    }

    private fun showNewspapers() {
        for (i in newspapers.indices) {
            print("${i + 1}. ")
            newspapers[i].showSummary()
        }
    }

    private fun showDisks() {
        for (i in disks.indices) {
            print("${i + 1}. ")
            disks[i].showSummary()
        }
    }

    fun showNoDigit(): Int { // выводит все книги и газеты подряд; возвращает номер последней книги
        var lastIndexOfBooks = 0
        for (i in books.indices) {
            print("${i + 1}. ")
            books[i].showSummary()
            lastIndexOfBooks = i + 1
        }
        for (i in newspapers.indices) {
            print("${i + lastIndexOfBooks + 1}. ")
            newspapers[i].showSummary()
        }
        return lastIndexOfBooks
    }

    fun add(item: LibraryHoldable) {
        when (item) {
            is Book -> books.add(item)
            is Newspaper -> newspapers.add(item)
            is Disk -> disks.add(item)
        }
    }

}