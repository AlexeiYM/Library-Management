package com.example.library

class DigitizationRoom<out T : NoDigital>(private val noDigitalItem: T) {
    fun makeDigitization(): Disk = Disk(
        "CD",
        noDigitalItem.name,
        noDigitalItem.id,
        true
    )
}