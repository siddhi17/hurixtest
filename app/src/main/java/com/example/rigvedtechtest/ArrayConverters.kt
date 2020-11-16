package com.example.rigvedtechtest

import androidx.room.TypeConverter

class ArrayConverters {
    @TypeConverter
    fun to(array: List<String>): String {
        return array.joinToString(" ")
    }

    @TypeConverter
    fun from(value: String): List<String> {
        return value.split(" ")
    }
}