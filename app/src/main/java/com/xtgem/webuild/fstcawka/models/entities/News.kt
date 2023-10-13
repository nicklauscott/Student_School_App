package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.NewsCategory
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class News(
    @PrimaryKey val newsId: UUID,
    val createdDate: LocalDateTime,
    val title: String,
    val subtitle: String,
    val content: String,
    val category: NewsCategory,
    val imageLink: Int
)
