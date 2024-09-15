package com.example.newsapp.data.local.typeConverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapp.domain.model.Source

@ProvidedTypeConverter
class ArticleTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(string: String): Source {
        val (id, name) = string.split(",")
        return Source(
            id = id,
            name = name
        )
    }
}