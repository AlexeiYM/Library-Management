package com.example.library

class Disk(
    override val type: String,
    override val name: String,
    override val id: Int,
    override var available: Boolean
) : LibraryHoldable, Digital {

    override fun showSummary() {
        println("$name доступен: ${if(available) "Да" else "Нет"}")
    }

    override fun showDetailedInformation() {
        println("$type $name с id: $id доступен: ${if(available) "Да" else "Нет"}")
    }

    override fun giveBack() {
        check(!available) { "диск находится в библиотеке" }
        available = true
        println("Диск $id был возвращен в библиотеку")
    }

    override fun takeHome() {
        check(available) { "диск уже взяли" }
        available = false
        println("Диск $id взяли домой")
    }

    override fun takeAtHall() {
        throw IllegalStateException("диск нельзя взять в читательный зал")
    }

}