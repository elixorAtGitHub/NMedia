package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean = true,
    var likes: Int = 997,
    var shares: Int = 998,
    var views: Int = 1230000
)