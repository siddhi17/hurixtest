package com.example.HurixTest

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.HurixTest.repository.model.news.Doc

@Database(entities = [Doc::class], version = 2, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsArticlesDao(): NewsArticlesDao
}