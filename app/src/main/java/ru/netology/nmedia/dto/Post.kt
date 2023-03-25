package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean = false,
    var likes: Int = 7856,
    var shares: Int = 1199999,
    var views: Int = 12757
)