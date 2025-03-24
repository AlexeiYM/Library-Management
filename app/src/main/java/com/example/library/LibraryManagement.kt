package com.example.library

fun main() {
    val list = listOf(
        Book("Повести", 285, "Н.В. Гоголь", 13233, "Да"),
        Book("Принц и Нищий", 281, "Марк Твен", 14422, "Нет"),
        Book("Война и мир", 1300, "Л.Н. Толстой", 223333, "Да"),
        Disk("DVD", "Человек-паук", 56674, "Да"),
        Disk("CD", "Звёздные войны I-VI", 56678, "Да"),
        Disk("DVD", "Чужой", 34900, "Нет"),
        Newspaper(53, "Новости Москвы", 78352, "Сентябрь", "Нет"),
        Newspaper(3, "События в мире звёзд", 24233, "Декабрь", "Нет"),
        Newspaper(21, "Главное за неделю", 75409, "Июнь", "Да")
    )

    val library = Library(list)
    val manager = Manager()
    val bookShop = BookShop()
    val newspaperShop = NewspaperShop()
    val diskShop = DiskShop()

    val libraryUsage = LibraryUsage(library)
    val managerUsage = ManagerUsage(library, manager, bookShop, newspaperShop, diskShop)
    val digitizationRoomUsage = DigitizationRoomUsage(library)

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
            3 -> digitizationRoomUsage.start()
        }
    }
}

class Library(list: List<LibraryHoldable>) {

    private val books = getNewList<Book>(list)
    private val newspapers = getNewList<Newspaper>(list)
    private val disks = getNewList<Disk>(list)

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

class Book(
    override val name: String,
    private val pageNumber: Int,
    private val author: String,
    override val id: Int,
    override var available: String
) : LibraryHoldable, NoDigital {

    override fun showSummary() {
        println("$name доступна: $available")
    }

    override fun showDetailedInformation() {
        println("книга: $name($pageNumber стр.) автора: $author с id: $id доступна: $available")
    }

    override fun giveBack() {
        check(available == "Нет") { "книга находится в библиотеке" }
        available = "Да"
        println("Книга $id была возвращена в библиотеку")
    }

    override fun takeHome() {
        check(available == "Да") { "книгу уже взяли" }
        available = "Нет"
        println("Книгу $id взяли домой")
    }

    override fun takeAtHall() {
        check(available == "Да") { "книгу уже взяли" }
        available = "Нет"
        println("Книгу $id взяли в читательный зал")
    }

}

class Newspaper(
    private val release: Int,
    override val name: String,
    override val id: Int,
    private val releaseMonth: String,
    override var available: String
) : LibraryHoldable, NoDigital {

    override fun showSummary() {
        println("$name доступна: $available")
    }

    override fun showDetailedInformation() {
        println("выпуск: $release (месяц: $releaseMonth) $name с id: $id доступен: $available")
    }

    override fun giveBack() {
        check(available == "Нет") { "газета находится в библиотеке" }
        available = "Да"
        println("Газета $id была возвращена в библиотеку")
    }

    override fun takeHome() {
        throw IllegalStateException("газету нельзя взять домой")
    }

    override fun takeAtHall() {
        check(available == "Да") { "газету уже взяли" }
        available = "Нет"
        println("Газету $id взяли в читательный зал")
    }

}

class Disk(
    override val type: String,
    override val name: String,
    override val id: Int,
    override var available: String
) : LibraryHoldable, Digital {

    override fun showSummary() {
        println("$name доступен: $available")
    }

    override fun showDetailedInformation() {
        println("$type $name с id: $id доступен: $available")
    }

    override fun giveBack() {
        check(available == "Нет") { "диск находится в библиотеке" }
        available = "Да"
        println("Диск $id был возвращен в библиотеку")
    }

    override fun takeHome() {
        check(available == "Да") { "диск уже взяли" }
        available = "Нет"
        println("Диск $id взяли домой")
    }

    override fun takeAtHall() {
        throw IllegalStateException("диск нельзя взять в читательный зал")
    }

}

interface LibraryHoldable {
    var available: String
    fun showSummary()
    fun showDetailedInformation()
    fun giveBack()
    fun takeHome()
    fun takeAtHall()
}

interface Digital {
    val type: String
    val name: String
    val id: Int
}

interface NoDigital {
    val name: String
    val id: Int
}