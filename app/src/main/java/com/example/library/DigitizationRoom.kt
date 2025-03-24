package com.example.library

class DigitizationRoom<out T : NoDigital>(private val noDigitalItem: T) {
    fun makeDigitization(): Disk = Disk(
        "CD",
        noDigitalItem.name,
        noDigitalItem.id,
        "Да"
    )
}

class DigitizationRoomUsage(val library: Library) {
    fun start() {
        println("-----------------------------")
        val lastBook = library.showNoDigit()
        println("Введите номер который нужно оцифровать:")
        val number: Int = readlnOrNull()?.toIntOrNull() ?: -1
        try {
            if (number > lastBook) {
                val newspaper = library.getNewspaper(number - lastBook)
                val digitizationRoom = DigitizationRoom(newspaper)
                library.add(digitizationRoom.makeDigitization())
            } else {
                val book = library.getBook(number)
                val digitizationRoom = DigitizationRoom(book)
                library.add(digitizationRoom.makeDigitization())
            }
            println("Оцифровка была выполнена успешно!")
        } catch (exception: IndexOutOfBoundsException) {
            println("Невозможно выполнить действие: такого номера не существует")
        }
    }
}