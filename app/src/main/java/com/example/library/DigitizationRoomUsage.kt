package com.example.library

fun Library.digitizationRoomUsage() {
    println("-----------------------------")
    val lastBook = showNoDigit()
    println("Введите номер который нужно оцифровать:")
    val number: Int = readlnOrNull()?.toIntOrNull() ?: -1
    try {
        if (number > lastBook) {
            val newspaper = getNewspaper(number - lastBook)
            add(DigitizationRoom(newspaper).makeDigitization())
        } else {
            val book = getBook(number)
            add(DigitizationRoom(book).makeDigitization())
        }
        println("Оцифровка была выполнена успешно!")
    } catch (exception: IndexOutOfBoundsException) {
        println("Невозможно выполнить действие: такого номера не существует")
    }
}