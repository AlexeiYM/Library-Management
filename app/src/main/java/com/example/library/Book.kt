package com.example.library

class Book(
    override val name: String,
    private val pageNumber: Int,
    private val author: String,
    override val id: Int,
    override var available: Boolean
) : LibraryHoldable, NoDigital {

    override fun showSummary() {
        println("$name доступна: ${if(available) "Да" else "Нет"}3")
    }

    override fun showDetailedInformation() {
        println("книга: $name($pageNumber стр.) автора: $author с id: $id доступна: ${if(available) "Да" else "Нет"}")
    }

    override fun giveBack() {
        check(!available) { "книга находится в библиотеке" }
        available = true
        println("Книга $id была возвращена в библиотеку")
    }

    override fun takeHome() {
        check(available) { "книгу уже взяли" }
        available = false
        println("Книгу $id взяли домой")
    }

    override fun takeAtHall() {
        check(available) { "книгу уже взяли" }
        available = false
        println("Книгу $id взяли в читательный зал")
    }

}