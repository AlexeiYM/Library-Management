package com.example.library

fun main() {
    val list = listOf(
        Book("Повести", 285, "Н.В. Гоголь", 13233, "Да"),
        Book("Принц и Нищий", 281, "Марк Твен", 14422, "Нет"),
        Book("Война и мир", 1300, "Л.Н. Толстой", 223333, "Да"),
        Disk("DVD", "Человек-паук", 56674, "Да"),
        Disk("CD", "Звёздные войны I-VI", 56678, "Да"),
        Disk("DVD", "Чужой", 34900, "Нет"),
        Newspaper(53, "Новости Москвы", 78352, "Нет"),
        Newspaper(3, "События в мире звёзд", 24233, "Нет"),
        Newspaper(21, "Главное за неделю", 75409, "Да")
    )

    val library = Library(list)

    while (true) {
        println(
            """
                -----------------------------
                1. Показать книги
                2. Показать газеты
                3. Показать диски
                Что показать (введите номер):
            """.trimIndent()
        )
        val itemsToShow: Int = readlnOrNull()?.toIntOrNull() ?: -1
        if (itemsToShow in 0..3) {

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
                } catch (exception: IndexOutOfBoundsException){
                    println("Невозможно выполнить действие: такого номера не существует")
                    continue
                } catch (exception: IllegalArgumentException){
                    println("Невозможно выполнить действие: ${exception.message}")
                    continue
                }
            }

        } else {
            println("Вы ввели некорректное число")
        }

    }
}

class Library(list: List<LibraryHoldable>) {

    private val books = mutableListOf<Book>()
    private val newspapers = mutableListOf<Newspaper>()
    private val disks = mutableListOf<Disk>()

    init {
        list.forEach {
            when (it) {
                is Book -> books.add(it)
                is Newspaper -> newspapers.add(it)
                is Disk -> disks.add(it)
            }
        }
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
        require(action in 0..4) {"нет действия с таким номером"}
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
        println("0. Выйти в меню")
        when (number) {
            1 -> showBooks()
            2 -> showNewspapers()
            3 -> showDisks()
        }
    }

    fun getBook(number: Int): LibraryHoldable {
        return books[number - 1]
    }

    fun getNewspaper(number: Int): LibraryHoldable {
        return newspapers[number - 1]
    }

    fun getDisk(number: Int): LibraryHoldable {
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

}

class Book(
    private val name: String,
    private val pageNumber: Int,
    private val author: String,
    private val id: Int,
    private var available: String
) : LibraryHoldable {

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
    private val name: String,
    private val id: Int,
    private var available: String
) : LibraryHoldable {

    override fun showSummary() {
        println("$name доступна: $available")
    }

    override fun showDetailedInformation() {
        println("выпуск: $release газеты $name с id: $id достуен: $available")
    }

    override fun giveBack() {
        check(available == "Нет") { "газета находится в библиотеке" }
        available = "Да"
        println("Газета $id была возвращена в библиотеку")
    }

    override fun takeHome() {
        error { "Газету нельзя взять домой" }
    }

    override fun takeAtHall() {
        check(available == "Да") { "газету уже взяли" }
        available = "Нет"
        println("Газету $id взяли в читательный зал")
    }

}

class Disk(
    private val type: String,
    private val name: String,
    private val id: Int,
    private var available: String
) : LibraryHoldable {

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
        error { "Диск нельзя взять в читательный зал" }
    }

}

interface LibraryHoldable {
    fun showSummary()
    fun showDetailedInformation()
    fun giveBack()
    fun takeHome()
    fun takeAtHall()
}