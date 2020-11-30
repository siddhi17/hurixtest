package com.example.HurixTest.repository.model.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.HurixTest.GenreConverter

@Entity(tableName = "news_table")
data class Doc(
        @PrimaryKey var id: String = "",
        var journal: String? = null,
        var eissn: String? = null,
        var publication_date: String? = null,
        var article_type: String? = null,
        @TypeConverters(GenreConverter::class) var abstract: List<String>? = null,
        var title_display: String? = null,
        var score: Double? = null
)