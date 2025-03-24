package com.example.library

import com.example.library.Months.*

class NewspaperShop : Shop {
    override fun sell(): Newspaper {
        return Newspaper(
            23,
            "Главное за месяц",
            23021,
            JUNE,
            true
        )
    }
}