package com.example.library

inline fun <reified T> getObjects(sourceList: List<*>): List<T> {
    return sourceList.filterIsInstance<T>()
}