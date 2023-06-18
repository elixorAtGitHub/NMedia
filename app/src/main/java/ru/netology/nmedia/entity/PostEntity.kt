package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean = true,
    var likes: Int = 997,
    var shares: Int = 998,
    var views: Int = 1230000,
    val video: String? = null
) {
    fun toDto() = Post(id, author, content, published, likedByMe, likes, shares, views, video)

    companion object {
        fun fromDto(post: Post) = with(post) {PostEntity(id, author, content, published, likedByMe, likes, shares, views, video)}
    }
}