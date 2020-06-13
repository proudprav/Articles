package com.example.articles.model

data class ArticleResponse(

    val id: Int,
    val createdAt: String,
    val content: String,
    val comments: Int,
    val likes: Int,
    val media: List<Media>,
    val user: List<User>
)

data class Media(

    val id: Int,
    val blogId: Int,
    val createdAt: String,
    val image: String,
    val title: String,
    val url: String
)

data class User(
    val id: Int,
    val blogId: Int,
    val createdAt: String,
    val name: String,
    val avatar: String,
    val lastName: String,
    val city: String,
    val designation: String,
    val about: String
)