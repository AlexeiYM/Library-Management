package com.example.library

class DiskShop : Shop {
    override fun sell(): Disk {
        return Disk(
            "DVD",
            "Чужой 2",
            98233,
            true
        )
    }
}