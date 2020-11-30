package com.example.rigvedtechtest

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

/**
 * News Article Model an entity that represents a table and holds schema of News articles
 *
 * By default, Room uses the class name as the database table name. If you want the table to have a different name, set the tableName
 * property of the @Entity annotation, as shown in the following code snippet:
 * @Entity(tableName = "news_table")
 */
@Entity(tableName = "news_table")
data class NewsArticles(
        @PrimaryKey var id: String = "",
        @SerializedName("article_type") var title: String? = null,
        @TypeConverters(ArrayConverters::class)
        //    @SerializedName("abstract") var description: List<String>,
        @SerializedName("publication_date") var publishedAt: String? = null
)