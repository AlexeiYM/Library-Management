package com.example.library

class Newspaper(
    private val release: Int,
    override val name: String,
    override val id: Int,
    private val releaseMonth: Months,
    override var available: Boolean
) : LibraryHoldable, NoDigital {

    override fun showSummary() {
        println("$name доступна: ${if(available) "Да" else "Нет"}")
    }

    override fun showDetailedInformation() {
        println("выпуск: $release (месяц: ${releaseMonth.rusName}) $name с id: $id доступен: ${if(available) "Да" else "Нет"}")
    }

    override fun giveBack() {
        check(!available) { "газета находится в библиотеке" }
        available = true
        println("Газета $id была возвращена в библиотеку")
    }

    override fun takeHome() {
        throw IllegalStateException("газету нельзя взять домой")
    }

    override fun takeAtHall() {
        check(available) { "газету уже взяли" }
        available = false
        println("Газету $id взяли в читательный зал")
    }

}
