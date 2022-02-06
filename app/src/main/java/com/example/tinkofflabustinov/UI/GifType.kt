package com.example.tinkofflabustinov.UI

enum class GifType(val category : String) {
    LATEST("latest"),
    HOT("hot"),
    TOP("top"),
    NONE("");

    companion object {
        private val VALUES = values()
        fun getByValue(value: Int) = VALUES.firstOrNull { it.ordinal == value }
    }
}