package com.example.library

inline fun <reified T> getNewList(sourceList: List<*>): MutableList<T> {
    val newList = mutableListOf<T>()
    sourceList.forEach {
        if (it is T) newList.add(it)
    }
    return newList
}