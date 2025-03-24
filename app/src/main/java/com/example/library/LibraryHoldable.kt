package com.example.library

interface LibraryHoldable {
    var available: Boolean
    fun showSummary()
    fun showDetailedInformation()
    fun giveBack()
    fun takeHome()
    fun takeAtHall()
}