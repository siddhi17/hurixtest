package com.example.HurixTest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.HurixTest.repository.model.news.Doc

@Dao
interface NewsArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<Doc>): List<Long>

    @Query("SELECT * FROM news_table")
    fun getNewsArticles(): LiveData<List<Doc>>

    @Query("DELETE FROM news_table")
    fun deleteAllArticles()
}