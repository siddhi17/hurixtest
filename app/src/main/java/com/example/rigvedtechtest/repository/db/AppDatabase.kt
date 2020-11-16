package com.example.rigvedtechtest

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(entities = [NewsArticles::class], version = 2, exportSchema = false)
@TypeConverters(ArrayConverters::class)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Get DAO's
     */
    abstract fun newsArticlesDao(): NewsArticlesDao
}